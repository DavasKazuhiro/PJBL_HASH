import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] qtds = {100000, 1000000, 10000000};
        int[] tamanhos = {1000, 10000, 1000000};
        int limite = 100000000;
        int seed = 42;

        Random random = new Random(seed);

        MultiplicacaoKnuth tabela1 = new MultiplicacaoKnuth(tamanhos[0]);
        MultiplicacaoKnuth tabela2 = new MultiplicacaoKnuth(tamanhos[1]);
        MultiplicacaoKnuth tabela3 = new MultiplicacaoKnuth(tamanhos[2]);

        int[] numeros1 = new int[qtds[0]];
        int[] numeros2 = new int[qtds[1]];
        int[] numeros3 = new int[qtds[2]];

        for(int i = 0; i < qtds[0]; i++){
            numeros1[i] = random.nextInt(limite);
        }

        for(int i = 0; i < qtds[1]; i++){
            numeros2[i] = random.nextInt(limite);
        }

        for(int i = 0; i < qtds[2]; i++){
            numeros3[i] = random.nextInt(limite);
        }

        for(int n : numeros1){
            tabela1.inserir(n);
        }

        for(int n : numeros2){
            tabela2.inserir(n);
        }

        for(int n : numeros3){
            tabela3.inserir(n);
        }

        System.out.println(tabela1.getColisoes());
        System.out.println(tabela2.getColisoes());
        System.out.println(tabela3.getColisoes());
    }
}