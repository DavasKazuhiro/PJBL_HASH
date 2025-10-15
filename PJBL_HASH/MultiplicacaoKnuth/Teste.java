import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Teste {
    public static void main(String[] args) {
        long seed = 32;
        Random random = new Random(seed);
        String[][] resultados = new String[27][10];

        int[] tamanhosConjuntos = {1000};

        int[] vetor1 = new int[tamanhosConjuntos[0]];

        int[][] vetores = {vetor1};

        for (int[] vetor : vetores) {
            preencherVetor(vetor, random);
        }

        int[] tamanhosTabelas = {100};

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

                tabela.imprimir();

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
