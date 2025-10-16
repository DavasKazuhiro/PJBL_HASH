import java.util.*;

public class Main {

    public static void main(String[] args) {
        long seed = 32;
        Random random = new Random(seed);
        Resultado2[] resultados = new Resultado2 [27];
        int indiceResultado = 0;

        int[] tamanhosConjuntos = {100000, 1000000, 10000000};

        int[] vetor1 = new int[tamanhosConjuntos[0]];
        int[] vetor2 = new int[tamanhosConjuntos[1]];
        int[] vetor3 = new int[tamanhosConjuntos[2]];
        int[][] vetores = {vetor1, vetor2, vetor3};

        for (int[] vetor : vetores) {
            preencherVetor(vetor, random);
        }

        int[] tamanhosTabelas = {1000, 10000, 100000};

        // Teste para cada tamanho de tabela e conjunto
        for (int tamanhoTabela : tamanhosTabelas) {

            for (int j = 0; j < vetores.length; j++) {
                ReHash tabela = new ReHash(tamanhoTabela);

                // Inserção
                tabela.setInicioInsersao(System.currentTimeMillis());
                for (int chave : vetores[j]) {
                    tabela.inserir(chave);
                }
                tabela.setFimInsersao(System.currentTimeMillis());

                // Busca
                tabela.setInicioBusca(System.currentTimeMillis());
                for (int chave : vetores[j]) {
                    tabela.buscar(chave);
                }
                tabela.setFimBusca(System.currentTimeMillis());

                int[] medicoesGaps = tabela.medirGaps();
                int maiorGap = medicoesGaps[0];
                int menorGap = medicoesGaps[1];
                int mediaGaps = medicoesGaps[2];

                System.out.println(
                        tabela.getTipo() + " -> " + tabela.getTamanho() + " Posições -> " + tamanhosConjuntos[j] + " Elementos" +
                                " -> Colisões: " + tabela.getColisoes() +
                                " -> Tempo de Inserção: " + tabela.getTempoInsersao() + "ms" +
                                " -> Tempo de Busca: " + tabela.getTempoBusca() + "ms" +
                                " -> Maior Gap: " + maiorGap +
                                " -> Menor Gap: " + menorGap +
                                " -> Média Gaps: " + mediaGaps);

                resultados[indiceResultado] = new Resultado2(
                        tabela.getTipo(), tabela.getTamanho(), tamanhosConjuntos[j],
                        tabela.getTempoInsersao(), tabela.getTempoBusca(), tabela.getColisoes(),
                        maiorGap, menorGap, mediaGaps
                );

                indiceResultado++;
            }
        }
    }

    // Gera números aleatórios únicos em cada vetor
    public static void preencherVetor(int[] vetor, Random random) {
        Set<Integer> usados = new HashSet<>();
        int i = 0;

        while (i < vetor.length) {
            int num = random.nextInt(100_000_000); // espaço grande = menos colisões
            if (usados.add(num)) { // adiciona só se for novo
                vetor[i++] = num;
            }
        }
    }
}