public class ListaEncadeada {
    private Node primeiro;
    private int tamanhoLista;

    public ListaEncadeada(){
        this.primeiro = null;
        this.tamanhoLista = 0;
    }

    public boolean buscar(int valor){
        Node atual = primeiro;

        while(atual != null){
            if(atual.getValor() == valor){
                return true;
            }
            atual = atual.getProximo();
        }

        return false;
    }

    public void imprimir(){
        Node atual = primeiro;
        while(atual != null){
            System.out.print(atual.getValor() + " -> ");
            atual = atual.getProximo();
        }
        System.out.println();
    }

    public int getTamanhoLista(){return tamanhoLista;}
    public void setTamanhoLista(int tamanho) {this.tamanhoLista = tamanho;}

    public Node getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(Node primeiro) {
        this.primeiro = primeiro;
    }
}
