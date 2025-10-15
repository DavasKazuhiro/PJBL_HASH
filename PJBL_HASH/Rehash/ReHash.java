public class ReHash extends TabelaHash {
    private ListaEncadeada[] tabela;
    private int capacidade;
    private static final double FATOR_DE_CARGA = 0.75; // Quando atingir essa porcentagem, ele duplica o tamanho

    public ReHash(int tamanhoInicial) {
        super(tamanhoInicial, "ReHashing com Divisão");
        this.tabela = new ListaEncadeada[tamanhoInicial];
        for (int i = 0; i < tamanhoInicial; i++) {
            this.tabela[i] = new ListaEncadeada();
        }
        this.capacidade = 0;
    }

    private void rehash() {
        ListaEncadeada[] tabelaAntiga = this.tabela;

        this.tamanho *= 2;
        this.tabela = new ListaEncadeada[this.tamanho];
        for (int i = 0; i < this.tamanho; i++) {
            this.tabela[i] = new ListaEncadeada();
        }

        // Reinserir todos os elementos da tabela antiga na nova.
        for (ListaEncadeada lista : tabelaAntiga) {
            Node atual = lista.getPrimeiro();
            while (atual != null) {
                // Chama o método de inserção interno
                inserirElemento(atual.getValor());
                atual = atual.getProximo();
            }
        }
    }

    // Método privado para inserir o elemento sem ver o fator de carga
    private void inserirElemento(int chave) {
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

        lista.setTamanhoLista(lista.getTamanhoLista() + 1);
    }

    @Override
    public int hash(int chave) {
        if (chave < 0) chave = -chave;
        return chave % this.tamanho; // temos que escolher a fução que vamos fazer aq no rehash 
    }

    @Override
    public void inserir(int chave) {
        if (((double) capacidade / this.tamanho) >= FATOR_DE_CARGA) {
            rehash();
        }

        inserirElemento(chave);

        this.capacidade++;
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
                terceiro = segundo;
                segundo = primeiro;
                primeiro = tamanho;
            } else if (tamanho > segundo) {
                terceiro = segundo;
                segundo = tamanho;
            } else if (tamanho > terceiro) {
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

        if (contador > 0) {
            qtdGaps++;
            soma += contador;
            if (contador > maior) maior = contador;
            if (contador < menor) menor = contador;
        }

        int media = 0;
        if(qtdGaps > 0){
            media = soma / qtdGaps;
        }

        if (menor == tamanho) menor = 0;

        return new int[]{maior, menor, media};
    }
    
    public void imprimir(){
        for(ListaEncadeada lista: tabela){
            lista.imprimir();
        }
    }
}