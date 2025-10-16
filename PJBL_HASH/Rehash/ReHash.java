public class ReHash extends TabelaHash {
    private int[] chaves;
    private boolean[] ocupados;
    private int capacidade;
    private static final double FATOR_DE_CARGA = 0.75; // Quando atingir essa porcentagem, ele duplica o tamanho

    public ReHash(int tamanhoInicial) {
        super(tamanhoInicial, "ReHashing (linear / endereçamento aberto)");
        this.chaves = new int[tamanhoInicial];
        this.ocupados = new boolean[tamanhoInicial];
        this.capacidade = 0;
    }

    private void resize() {
        int[] chavesAnteriores = chaves;
        boolean[] ocupadosAntigos = ocupados;

        this.tamanho *= 2;
        this.chaves = new int[this.tamanho];
        this.ocupados = new boolean[this.tamanho];
        this.capacidade = 0; 

        for (int i = 0; i < chavesAnteriores.length; i++) {
            if (ocupadosAntigos[i]) inserirDireto(chavesAnteriores[i]);
        }
    }

    private void inserirElemento(int chave) {
        if (((double) (capacidade + 1)) / this.tamanho > FATOR_DE_CARGA) {
            resize();
        }

        int pos = hash(chave);
        int passos = 0;

        while (ocupados[pos]) {
            if (chaves[pos] == chave) {
                return;
            }
            pos = (pos + 1) % this.tamanho;
            passos++; 
        }

        chaves[pos] = chave;
        ocupados[pos] = true;
        capacidade++;
        colisoes += passos;
    }

    private void inserirDireto(int chave) {
        int pos = hash(chave);
        while (ocupados[pos]) {
            if (chaves[pos] == chave) return;  
            pos = (pos + 1) % this.tamanho;
        }
        chaves[pos] = chave;
        ocupados[pos] = true;
        capacidade++;
    }

    @Override
    public int hash(int chave) {
        if (chave < 0) chave = -chave;
        return chave % this.tamanho; 
    }

    @Override
    public void inserir(int chave) {
        inserirElemento(chave);
    }

    @Override
    public boolean buscar(int chave) {
        int pos = hash(chave);
        int start = pos;

        while (ocupados[pos]) {
            if (chaves[pos] == chave) return true;
            pos = (pos + 1) % this.tamanho;
            if (pos == start) break;  
        }
        return false;
    }

    
    public int[] medirGaps() {
        if (tamanho == 0) return new int[]{0,0,0};
        int maior=0, menor=Integer.MAX_VALUE; long soma=0; int qtd=0;
    
        int i=0;
        while (i < tamanho) {
            if (!ocupados[i]) {
                int len=0;
                while (i < tamanho && !ocupados[i]) { len++; i++; }
                if (len>0) { soma+=len; qtd++; if (len>maior) maior=len; if (len<menor) menor=len; }
            } else i++;
        }
    
        int prefix=0; for (int k=0; k<tamanho && !ocupados[k]; k++) prefix++;
        int suffix=0; for (int k=tamanho-1; k>=0 && !ocupados[k]; k--) suffix++;
        if (prefix>0 && suffix>0) {
            int wrap = prefix + suffix;
            if (wrap > maior) maior = wrap;
            if (wrap < menor) menor = wrap;
        }
    
        if (qtd==0) return new int[]{0,0,0};
        int media = (int)(soma / qtd);
        if (menor == Integer.MAX_VALUE) menor = 0;
        return new int[]{maior, menor, media};
    }
}