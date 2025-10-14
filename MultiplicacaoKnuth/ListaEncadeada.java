public class ListaEncadeada {
    private Node primeiro;

    public ListaEncadeada(){
        this.primeiro = null;
    }

    public void inserirOrdenado(int valor){
        if(primeiro == null){
            primeiro = new Node(valor);
        }
        else{
            Node atual = primeiro;
            Node anterior = null;
            Node novo = new Node(valor);

            while(atual.getProximo() != null && atual.getValor() < valor){
                anterior = atual;
                atual = atual.getProximo();
            }

            if(anterior == null){
                primeiro = novo;
                novo.setProximo(atual);
            }
            else{
                anterior.setProximo(novo);
                novo.setProximo(atual);
            }
        }
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

    public Node getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(Node primeiro) {
        this.primeiro = primeiro;
    }
}
