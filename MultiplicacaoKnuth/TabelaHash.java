
public abstract class TabelaHash {
    String tipo;
    protected int tamanho;
    protected long colisoes;
    protected long inicioInsersao;
    protected long fimInsersao;
    protected long inicioBusca;
    protected long fimBusca;

    public TabelaHash(int tamanho, String tipo) {
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.colisoes = 0;
    }

    public abstract int hash(int chave);

    public abstract void inserir(int chave);

    public abstract boolean buscar(int chave);

    public long getColisoes() {
        return colisoes;
    }

    public int getTamanho() {
        return tamanho;
    }

    public String getTipo() {return tipo;}

    // TEMPO DE INSERSAO
    public void setInicioInsersao(long inicio) {this.inicioInsersao = inicio;}
    public void setFimInsersao(long fim) {this.fimInsersao = fim;}
    public long getTempoInsersao() {return fimInsersao - inicioInsersao;}

    // TEMPO DE BUSCA
    public void setInicioBusca(long inicio) {this.inicioBusca = inicio;}
    public void setFimBusca(long fim) {this.fimBusca = fim;}
    public long getTempoBusca() {return fimBusca - inicioBusca;}


}

