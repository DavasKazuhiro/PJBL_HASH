import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args){
        Resultado[] resultados = new Resultado[27];
        int indiceResultado = 0;

        int[] tamanhosConjuntos = {100000, 1000000, 10000000};
        int[][] conjuntos = new int[3][];
        for(int i = 0; i < tamanhosConjuntos.length; i++){
            conjuntos[i] = new int[tamanhosConjuntos[i]];
        }
        for(int[] conjunto : conjuntos){
            preencherVetor(conjunto);
        }

        int[] tamanhosTabelas = {1000, 10000, 100000};

        for(int tipo = 0; tipo < 3; tipo++){
            for(int tamanhoTabela : tamanhosTabelas){
                for(int t = 0; t < conjuntos.length; t++){
                    TabelaHash tabela;
                    switch (tipo) {
                        case 0:
                            tabela = new MultiplicacaoKnuth(tamanhoTabela);break;
                        case 1:
                            tabela = new RestoDivisao(tamanhoTabela);break;
                        case 2:
                            tabela = new Rehash(tamanhoTabela);break;
                        default:
                            tabela = new MultiplicacaoKnuth(tamanhoTabela);break;
                    }

                    tabela.setInicioInsersao(System.currentTimeMillis());
                    for (int chave : conjuntos[t]) {
                        tabela.inserir(chave);
                    }
                    tabela.setFimInsersao(System.currentTimeMillis());

                    tabela.setInicioBusca(System.currentTimeMillis());
                    for (int chave : conjuntos[t]) {
                        tabela.buscar(chave);
                    }
                    tabela.setFimBusca(System.currentTimeMillis());

                    int[] medicoesLista = tabela.medirListas();
                    int primeiraLista = medicoesLista[0];
                    int segundaLista = medicoesLista[1];
                    int terceiraLista = medicoesLista[2];

                    int[] medicoesGaps = tabela.medirGaps();
                    int maiorGap = medicoesGaps[0];
                    int menorGap = medicoesGaps[1];
                    int mediaGaps = medicoesGaps[2];

                    System.out.println(
                            tabela.getTipo() + " -> " + tabela.getTamanho() + " Posições -> " + tamanhosConjuntos[t] + " Elementos" +
                                    " -> Colisões: " + tabela.getColisoes() +
                                    " -> Tempo de Insersão: " + tabela.getTempoInsersao() + "ms" +
                                    " -> Tempo de Busca: " + tabela.getTempoBusca() + "ms" +
                                    " -> Maior Lista: " + primeiraLista +
                                    " -> Segunda Lista: " + segundaLista +
                                    " -> Terceira Lista: " + terceiraLista +
                                    " -> Maior Gap: " + maiorGap +
                                    " -> Menor Gap: " + menorGap +
                                    " -> Media Gaps: " + mediaGaps);

                    resultados[indiceResultado] = new Resultado(tabela.getTipo(), tamanhoTabela, tabela.getTamanho(),tamanhosConjuntos[t],
                            tabela.getTempoInsersao(), tabela.getTempoBusca(), tabela.getColisoes(),
                            primeiraLista, segundaLista, terceiraLista,
                            maiorGap, menorGap, mediaGaps);

                    indiceResultado ++;
                }
            }
        }

        String caminho = "resultadoTabelaHash.csv";
        String s = ";";

        try (PrintWriter writer = new PrintWriter(new FileWriter(caminho))) {
            writer.println( "Tipo de Tabela;Tamanho Inicial do Vetor;Tamanho Final do Vetor;Tamanho do Conjunto;" +
                    "Tempo de Inserção;Quantidade de Colisões;Tempo de Busca;" +
                    "Primeira Lista;Segunda Lista;Terceira Lista;" +
                    "Maior Gap;Menor Gap; Media de Gaps");

            for(Resultado r : resultados){
                writer.println( r.getTipoTabela() + s + r.getTamanhoInicial() + s + r.getTamanhoFinal() + s + r.getTamanhoConjunto() + s +
                        r.getTempoInsersao() + s + r.getColisoes() + s + r.getTempoBusca() + s +
                        r.getPrimeiraLista() + s + r.getSegundaLista() + s + r.getTerceiraLista() + s +
                        r.getMaiorGap() + s + r.getMenorGap() + s + r.getMediaGap());
            }

            System.out.println("Arquivo CSV criado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void preencherVetor(int[] vetor) {
        int seed = 32;
        Random random = new Random(seed);

        int limite = 999_999_999;
        boolean[] usados = new boolean[limite];
        int i = 0;

        while (i < vetor.length) {
            int num = random.nextInt(limite);
            if (!usados[num]) {
                usados[num] = true;
                vetor[i++] = num;
            }
        }
    }
}
