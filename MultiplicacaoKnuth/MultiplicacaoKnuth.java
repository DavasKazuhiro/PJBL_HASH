public class MultiplicacaoKnuth extends TabelaHash {
    private ListaEncadeada[] tabela;
    private final double A = 0.707; // número irracional simples (~√2 / 2)

    public MultiplicacaoKnuth(int tamanho) {
        super(tamanho, "Multiplicação Knuth");
        tabela = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada();
        }
    }

    @Override
    public int hash(int chave) {
        if (chave < 0) chave = -chave;
        double val = chave * A;
        double parteFracionaria = val - (int) val;
        int indice = (int) (tamanho * parteFracionaria);
        return indice;
    }

    @Override
    public void inserir(int chave) {
        int posicao = hash(chave);
        ListaEncadeada lista = tabela[posicao];
        Node novo = new Node(chave);

        Node atual = lista.getPrimeiro();
        Node anterior = null;

        while (atual != null && atual.getValor() < chave) {
            anterior = atual;
            atual = atual.getProximo();
            colisoes++;
        }

        if (anterior == null) {
            // Inserir no início
            novo.setProximo(lista.getPrimeiro());
            lista.setPrimeiro(novo);
        } else {
            // Inserir no meio ou fim
            novo.setProximo(atual);
            anterior.setProximo(novo);
        }

        tabela[posicao].setTamanhoLista(tabela[posicao].getTamanhoLista() + 1);
    }

    @Override
    public boolean buscar(int chave) {
        int pos = hash(chave);
        return tabela[pos].buscar(chave);
    }

    // MEDICAO DOS TAMANHOS DAS LISTAS
    public int[] medirListas() {
        int primeiro = 0;
        int segundo = 0;
        int terceiro = 0;

        for (ListaEncadeada lista : tabela) {
            int tamanho = lista.getTamanhoLista();

            if (tamanho > primeiro) {
                // novo maior
                terceiro = segundo;
                segundo = primeiro;
                primeiro = tamanho;
            } else if (tamanho > segundo) {
                // novo segundo maior
                terceiro = segundo;
                segundo = tamanho;
            } else if (tamanho > terceiro) {
                // novo terceiro maior
                terceiro = tamanho;
            }
        }

        return new int[] {primeiro, segundo, terceiro};
    }

    // MEDICAO DOS GAPS
    public int[] medirGaps() {
        int maior = 0;
        int menor = tamanho;
        int soma = 0;
        int qtdGaps = 0;
        int contador = 0;

        for (ListaEncadeada lista : tabela) {
            if (lista.getPrimeiro() == null) {
                contador++;
            } else if (contador > 0) {
                qtdGaps++;
                soma += contador;
                if (contador > maior){
                    maior = contador;
                }
                if (contador < menor){
                    menor = contador;
                }
                contador = 0;
            }

        }

        // Caso a tabela termine com um gap no final
        if (contador > 0) {
            qtdGaps++;
            soma += contador;
            if (contador > maior) maior = contador;
            if (contador < menor) menor = contador;
        }

        // Evita divisão por zero se não houver gaps
        int media = 0;
        if(qtdGaps > 0){
            media = soma / qtdGaps;
        }

        if (menor == tamanho) menor = 0;

        return new int[]{maior, menor, media};
    }

}
