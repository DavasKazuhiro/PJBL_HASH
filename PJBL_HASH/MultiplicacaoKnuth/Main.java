import java.util.*;

public class Main {

    public static void main(String[] args) {
        long seed = 32;
        Random random = new Random(seed);
        Resultado[] resultados = new Resultado[27];
        int indiceResultado = 0;

        int[] tamanhosConjuntos = {100000, 1000000, 10000000};

        int[] vetor1 = new int[tamanhosConjuntos[0]];
        int[] vetor2 = new int[tamanhosConjuntos[1]];
        int[] vetor3 = new int[tamanhosConjuntos[2]];
        int[][] vetores = {vetor1, vetor2, vetor3};

        for (int[] vetor : vetores) {
            preencherVetor(vetor, random);
        }

        int[] tamanhosTabelas = {100000, 10000, 1000};

        // Teste para cada tamanho de tabela e conjunto
        for (int tamanhoTabela : tamanhosTabelas) {

            for (int j = 0; j < vetores.length; j++) {
                MultiplicacaoKnuth tabela = new MultiplicacaoKnuth(tamanhoTabela);

                // Insersão
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

                int[] medicoesLista = tabela.medirListas();
                int primeiraLista = medicoesLista[0];
                int segundaLista = medicoesLista[1];
                int terceiraLista = medicoesLista[2];

                int[] medicoesGaps = tabela.medirGaps();
                int maiorGap = medicoesGaps[0];
                int menorGap = medicoesGaps[1];
                int mediaGaps = medicoesGaps[2];
                System.out.println(
                        tabela.getTipo() + " -> " + tabela.getTamanho() + " Posições -> " + tamanhosConjuntos[j] + " Elementos" +
                                " -> Colisões: " + tabela.getColisoes() +
                                " -> Tempo de Insersão: " + tabela.getTempoInsersao() + "ms" +
                                " -> Tempo de Busca: " + tabela.getTempoBusca() + "ms" +
                                " -> Maior Lista: " + primeiraLista +
                                " -> Segunda Lista: " + segundaLista +
                                " -> Terceira Lista: " + terceiraLista +
                                " -> Maior Gap: " + maiorGap +
                                " -> Menor Gap: " + menorGap +
                                " -> Media Gaps: " + mediaGaps);
                resultados[indiceResultado] = new Resultado(tabela.getTipo(), tabela.getTamanho(), tamanhosConjuntos[j],
                                                            tabela.getTempoInsersao(), tabela.getTempoBusca(), tabela.getColisoes(),
                                                            primeiraLista, segundaLista, terceiraLista,
                                                            maiorGap, menorGap, mediaGaps);

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
