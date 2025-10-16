public class Resultado2 {
    private String tipoTabela;
    private int tamanhoVetor;
    private int tamanhoConjunto;
    private long tempoInsersao;
    private long tempoBusca;
    private long colisoes;
    private int maiorGap;
    private int menorGap;
    private int mediaGap;

    public Resultado2(String tipoTabela, int tamanhoVetor, int tamanhoConjunto, long tempoInsersao, long tempoBusca, long colisoes, int maiorGap, int menorGap, int mediaGap) {
        this.tipoTabela = tipoTabela;
        this.tamanhoVetor = tamanhoVetor;
        this.tamanhoConjunto = tamanhoConjunto;
        this.tempoInsersao = tempoInsersao;
        this.tempoBusca = tempoBusca;
        this.colisoes = colisoes;
        this.maiorGap = maiorGap;
        this.menorGap = menorGap;
        this.mediaGap = mediaGap;
    }

    public String getTipoTabela() {return tipoTabela;}
    public void setTipoTabela(String tipoTabela) {this.tipoTabela = tipoTabela;}

    public int getTamanhoVetor() {return tamanhoVetor;}
    public void setTamanhoVetor(int tamanhoVetor) {this.tamanhoVetor = tamanhoVetor;}

    public int getTamanhoConjunto() {return tamanhoConjunto;}
    public void setTamanhoConjunto(int tamanhoConjunto) {this.tamanhoConjunto = tamanhoConjunto;}

    public long getTempoInsersao() {return tempoInsersao;}
    public void setTempoInsersao(long tempoInsersao) {this.tempoInsersao = tempoInsersao;}

    public long getTempoBusca() {return tempoBusca;}
    public void setTempoBusca(long tempoBusca) {this.tempoBusca = tempoBusca;}

    public long getColisoes() {return colisoes;}
    public void setColisoes(long colisoes) {this.colisoes = colisoes;}

    public int getMaiorGap() {return maiorGap;}
    public void setMaiorGap(int maiorGap) {this.maiorGap = maiorGap;}

    public int getMenorGap() {return menorGap;}
    public void setMenorGap(int menorGap) {this.menorGap = menorGap;}

    public int getMediaGap() {return mediaGap;}
    public void setMediaGap(int mediaGap) {this.mediaGap = mediaGap;}
}
