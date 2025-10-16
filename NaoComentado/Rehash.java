public class Rehash extends TabelaHash {
    private int[] chaves;
    private boolean[] ocupados;
    private int capacidade;
    private static final double FATOR_DE_CARGA = 0.75;

    public Rehash(int tamanhoInicial) {
        super(tamanhoInicial, "Hash Duplo");
        this.chaves = new int[tamanhoInicial];
        this.ocupados = new boolean[tamanhoInicial];
        this.capacidade = 0;
    }

    @Override
    public int hash(int chave) {
        if (chave < 0) chave = -chave;
        return chave % tamanho;
    }

    private int hash2(int chave) {
        if (chave < 0) chave = -chave;
        int h2 = (chave % (tamanho - 1)) + 1;
        if (h2 >= tamanho) h2 = tamanho - 1;
        return h2;
    }

    private int hashDuplo(int chave, int tentativa) {
        long h1 = hash(chave);
        long h2 = hash2(chave);
        long pos = (h1 + tentativa * h2) % tamanho;
        if (pos < 0) pos += tamanho;
        return (int) pos;
    }

    private void resize() {
        int[] chavesAntigas = chaves;
        boolean[] ocupadosAntigos = ocupados;

        this.tamanho *= 2;
        this.chaves = new int[this.tamanho];
        this.ocupados = new boolean[this.tamanho];
        this.capacidade = 0;

        for (int i = 0; i < chavesAntigas.length; i++) {
            if (ocupadosAntigos[i]) inserirDireto(chavesAntigas[i]);
        }
    }

    private void inserirElemento(int chave) {
        if (((double) (capacidade + 1)) / this.tamanho > FATOR_DE_CARGA) {
            resize();
        }

        int tentativa = 0;
        int pos = hashDuplo(chave, tentativa);
        int passos = 0;

        while (ocupados[pos]) {
            if (chaves[pos] == chave) return;
            tentativa++;
            pos = hashDuplo(chave, tentativa);
            passos++;
            if (tentativa > tamanho) return;
        }

        chaves[pos] = chave;
        ocupados[pos] = true;
        capacidade++;
        colisoes += passos;
    }

    private void inserirDireto(int chave) {
        int tentativa = 0;
        int pos = hashDuplo(chave, tentativa);

        while (ocupados[pos]) {
            if (chaves[pos] == chave) return;
            tentativa++;
            pos = hashDuplo(chave, tentativa);
            if (tentativa > tamanho) return;
        }

        chaves[pos] = chave;
        ocupados[pos] = true;
        capacidade++;
    }

    @Override
    public void inserir(int chave) {
        inserirElemento(chave);
    }

    @Override
    public boolean buscar(int chave) {
        int tentativa = 0;
        int pos = hashDuplo(chave, tentativa);
        int inicio = pos;

        while (ocupados[pos]) {
            if (chaves[pos] == chave) return true;
            tentativa++;
            pos = hashDuplo(chave, tentativa);
            if (tentativa > tamanho || pos == inicio) break;
        }
        return false;
    }

    @Override
    public int[] medirGaps() {
        int maior = 0;
        int menor = tamanho;
        long soma = 0;
        int qtd = 0;

        int i = 0;
        while (i < tamanho) {
            if (!ocupados[i]) {
                int contador = 0;
                while (i < tamanho && !ocupados[i]) {
                    contador++;
                    i++;
                }
                if (contador > 0) {
                    soma += contador;
                    qtd++;
                    if (contador > maior) maior = contador;
                    if (contador < menor) menor = contador;
                }
            } else i++;
        }

        if (qtd == 0) return new int[]{0, 0, 0};
        int media = (int) (soma / qtd);
        if (menor == tamanho) menor = 0;
        return new int[]{maior, menor, media};
    }
}
