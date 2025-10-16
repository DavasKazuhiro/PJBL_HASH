public class Resultado {
    private String tipoTabela;
    private int tamanhoVetorInicial;
    private int tamanhoVetorFinal;
    private int tamanhoConjunto;
    private long tempoInsersao;
    private long tempoBusca;
    private long colisoes;
    private int primeiraLista;
    private int segundaLista;
    private int terceiraLista;
    private int maiorGap;
    private int menorGap;
    private int mediaGap;

    public Resultado(String tipoTabela, int tamanhoVetorInicial, int tamanhoVetorFinal, int tamanhoConjunto, long tempoInsersao, long tempoBusca, long colisoes, int primeiraLista, int segundaLista, int terceiraLista, int maiorGap, int menorGap, int mediaGap) {
        this.tipoTabela = tipoTabela;
        this.tamanhoVetorInicial = tamanhoVetorInicial;
        this.tamanhoVetorFinal = tamanhoVetorFinal;
        this.tamanhoConjunto = tamanhoConjunto;
        this.tempoInsersao = tempoInsersao;
        this.tempoBusca = tempoBusca;
        this.colisoes = colisoes;
        this.primeiraLista = primeiraLista;
        this.segundaLista = segundaLista;
        this.terceiraLista = terceiraLista;
        this.maiorGap = maiorGap;
        this.menorGap = menorGap;
        this.mediaGap = mediaGap;
    }

    public String getTipoTabela() {return tipoTabela;}
    public void setTipoTabela(String tipoTabela) {this.tipoTabela = tipoTabela;}

    public int getTamanhoInicial() {return tamanhoVetorInicial;}
    public void setTamanhoVetor(int tamanhoVetor) {this.tamanhoVetorInicial = tamanhoVetor;}

    public int getTamanhoFinal() {return tamanhoVetorFinal;}
    public void setTamanhoVetorFinal(int tamanhoVetorFinal) {this.tamanhoVetorFinal = tamanhoVetorFinal;}

    public int getTamanhoConjunto() {return tamanhoConjunto;}
    public void setTamanhoConjunto(int tamanhoConjunto) {this.tamanhoConjunto = tamanhoConjunto;}

    public long getTempoInsersao() {return tempoInsersao;}
    public void setTempoInsersao(long tempoInsersao) {this.tempoInsersao = tempoInsersao;}

    public long getTempoBusca() {return tempoBusca;}
    public void setTempoBusca(long tempoBusca) {this.tempoBusca = tempoBusca;}

    public long getColisoes() {return colisoes;}
    public void setColisoes(long colisoes) {this.colisoes = colisoes;}

    public int getPrimeiraLista() {return primeiraLista;}
    public void setPrimeiraLista(int primeiraLista) {this.primeiraLista = primeiraLista;}

    public int getSegundaLista() {return segundaLista;}
    public void setSegundaLista(int segundaLista) {this.segundaLista = segundaLista;}

    public int getTerceiraLista() {return terceiraLista;}
    public void setTerceiraLista(int terceiraLista) {this.terceiraLista = terceiraLista;}

    public int getMaiorGap() {return maiorGap;}
    public void setMaiorGap(int maiorGap) {this.maiorGap = maiorGap;}

    public int getMenorGap() {return menorGap;}
    public void setMenorGap(int menorGap) {this.menorGap = menorGap;}

    public int getMediaGap() {return mediaGap;}
    public void setMediaGap(int mediaGap) {this.mediaGap = mediaGap;}
}
