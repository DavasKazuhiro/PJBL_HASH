
public class MultiplicacaoKnuth extends TabelaHash{
    private ListaEncadeada[] tabela;
    private int tamanho;
    private final double A = 0.6180339887;
    private int colisoes;

    public MultiplicacaoKnuth(int tamanho) {
        super(tamanho);
        this.colisoes = 0;
        tabela = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada();
        }
    }

    public int hash(int chave) {
        double val = chave * A;

        int parteInteira = (int) val;  // truncamento automático
        double parteFracionaria = val - parteInteira;

        int indice = (int) (tamanho * parteFracionaria);

        return indice;
    }

    public void inserir(int chave) {
        int posicao = hash(chave);

        ListaEncadeada lista = tabela[posicao];

        if(lista.getPrimeiro() == null){
            lista.setPrimeiro(new Node(chave));
        }
        else{
            Node atual = lista.getPrimeiro();
            Node anterior = null;
            Node novo = new Node(chave);
            colisoes ++;

            while(atual.getProximo() != null && atual.getValor() < chave){
                anterior = atual;
                atual = atual.getProximo();
                colisoes ++;
            }

            if(anterior == null){
                lista.setPrimeiro(novo);
                novo.setProximo(atual);
            }
            else{
                anterior.setProximo(novo);
                novo.setProximo(atual);
            }
        }
    }

    public boolean buscar(int chave) {
        int pos = hash(chave);
        return tabela[pos].buscar(chave);
    }

    public int getColisoes(){return colisoes;}
}
