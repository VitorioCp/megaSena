import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MegaSena {

    private static final int NUMEROS_APOSTA = 6;
    private static final int NUMEROS_POSSIVEIS = 60;

    private int[] numerosSorteados;
    private int[] numerosApostados;

    public MegaSena() {
        numerosSorteados = sortearNumeros();
        numerosApostados = new int[NUMEROS_APOSTA];
    }

    public void jogar() {
        System.out.println("Bem-vindo ao Jogo da Mega Sena!");
        System.out.println("Escolha 6 números de 1 a 60:");

        lerNumerosAposta();
        exibirResultados();
    }

    private void lerNumerosAposta() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < NUMEROS_APOSTA; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            int numero = scanner.nextInt();

            validarNumeroAposta(numero, i);

            numerosApostados[i] = numero;
        }

        Arrays.sort(numerosApostados);
    }

    private void validarNumeroAposta(int numero, int indice) {
        if (numero < 1 || numero > NUMEROS_POSSIVEIS) {
            System.out.println("Por favor, escolha um número entre 1 e " + NUMEROS_POSSIVEIS + ".");
            System.exit(1);
        }

        for (int i = 0; i < indice; i++) {
            if (numerosApostados[i] == numero) {
                System.out.println("Número já escolhido. Escolha outro número.");
                System.exit(1);
            }
        }
    }

    private int[] sortearNumeros() {
        int[] numerosSorteados = new int[NUMEROS_APOSTA];
        Random random = new Random();

        for (int i = 0; i < NUMEROS_APOSTA; i++) {
            numerosSorteados[i] = random.nextInt(NUMEROS_POSSIVEIS) + 1;
        }

        Arrays.sort(numerosSorteados);
        return numerosSorteados;
    }

    private void exibirResultados() {
        System.out.println("\nNúmeros apostados: " + Arrays.toString(numerosApostados));
        System.out.println("Números sorteados : " + Arrays.toString(numerosSorteados));

        int acertos = contarAcertos();
        System.out.println("\nVocê acertou " + acertos + " números!");

        if (acertos == NUMEROS_APOSTA) {
            System.out.println("Parabéns! Você acertou todos os números. Ganhou na Mega Sena!");
        } else {
            System.out.println("Não foi dessa vez. Tente novamente!");
        }
    }

    private int contarAcertos() {
        int count = 0;
        for (int numeroApostado : numerosApostados) {
            for (int numeroSorteado : numerosSorteados) {
                if (numeroApostado == numeroSorteado) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MegaSena jogo = new MegaSena();
        jogo.jogar();
    }
}
