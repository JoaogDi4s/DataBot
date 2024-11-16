import java.util.Scanner;

public class Introducao {// OBJETOS
    Scanner scan = new Scanner(System.in);
    Mensagens mensagens = new Mensagens();

    // VARIÁVEIS PARA LOOP DE ERRO
    boolean loopIntroducao = false;
    boolean loopTutorial = false;
    boolean loopCadastro = false;

    public void introducao(){
        System.out.println("\nBem vindo");

		while (!loopIntroducao) {
			System.out.println("Já conhece o chat?");
			String resposta = InputUtils.getInput(scan);

			// INTRODUÇÃO
			// RESPOSTA POSITIVA
			if (resposta.contains("sim")) {
				loopIntroducao = true;
				System.out.println("Ok, bom uso!");
				// RESPOSTA NEGATIVA
			} else if (resposta.contains("nao")) {
				loopIntroducao = true;
				while (!loopTutorial) {
					System.out.println("\nGostaria de um tutorial?");

					// TUTORIAL
					String tutorial = InputUtils.getInput(scan);
					// RESPOSTA POSITIVA
					if (tutorial.contains("sim")) {
						loopTutorial = true;
						mensagens.tutorial();
						// RESPOSTA NEGATIVA
					} else if (tutorial.contains("nao")) {
						loopTutorial = true;
						System.out.println("Ok, bom uso!");
						// NÃO ENTENDER
					} else {
						mensagens.nEntender();
					}
				}
				// NÃO ENTENDER
			} else {
				mensagens.nEntender();
			}
		}
    }
}