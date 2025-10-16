public class RestoDivisao extends TabelaHash {
    private ListaEncadeada[] tabela;

    public RestoDivisao(int tamanho) {
        super(tamanho, "Primo Grande");
        tabela = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada();
        }
    }


    @Override
    public int hash(int chave) {
        if (chave < 0) chave = -chave;

        int primoGrande = 104729;
        long hash = (long) chave * primoGrande;
        return (int) (hash % tamanho);
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

            novo.setProximo(lista.getPrimeiro());
            lista.setPrimeiro(novo);
        } else {

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


    @Override
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

        return new int[]{primeiro, segundo, terceiro};
    }


    @Override
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
                if (contador > maior) maior = contador;
                if (contador < menor) menor = contador;
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
        if (qtdGaps > 0) media = soma / qtdGaps;
        if (menor == tamanho) menor = 0;

        return new int[]{maior, menor, media};
    }


    public void imprimir() {
        for (ListaEncadeada lista : tabela) {
            lista.imprimir();
        }
    }
}
