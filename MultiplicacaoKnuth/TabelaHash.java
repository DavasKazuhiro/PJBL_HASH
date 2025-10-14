public abstract class TabelaHash {
    protected int tamanho;
    protected int colisoes;

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.colisoes = 0;
    }

    public abstract int hash(int chave);

    public abstract void inserir(int chave);

    public abstract boolean buscar(int chave);

    public int getColisoes() {
        return colisoes;
    }

    // Retorna o tamanho da tabela
    public int getTamanho() {
        return tamanho;
    }
}

