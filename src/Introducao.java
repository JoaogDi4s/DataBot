import java.util.Scanner;

public class Introducao {
	public void introducao() {
		// OBJETOS
		Scanner scan = new Scanner(System.in);
		Mensagens mensagens = new Mensagens();
		Usuario usuario = new Usuario();

		// VARIÁVEIS PARA LOOP DE ERRO
		boolean loopIntroducao = false;
		boolean loopTutorial = false;
		boolean loopCadastro = false;

		// CADASTRO
		while (!loopCadastro) {
			System.out.println("\nOlá, tudo bem?");
			System.out.println("Já tem cadastro?");

			String cadastro = scan.nextLine();
			cadastro = cadastro.toLowerCase();

			if (cadastro.contains("sim")) {
				loopCadastro = true;

				System.out.println("\nOk, então faça o Login");
				usuario.Logar();
			} else if (cadastro.contains("não") || cadastro.contains("nao")) {
				loopCadastro = true;

				System.out.println("\nOk, então vamos fazer o cadastro");
				usuario.Registrar();

				System.out.println("\nAgora que tem o cadastro, vamos fazer o Login");
				usuario.Logar();
			} else {
				mensagens.nEntender();
			}
		}

		System.out.println("\nBem vindo");

		while (!loopIntroducao) {
			System.out.println("Já conhece o chat?");
			String resposta = scan.nextLine();
			resposta = resposta.toLowerCase();

			// INTRODUÇÃO
			// RESPOSTA POSITIVA
			if (resposta.contains("sim")) {
				loopIntroducao = true;
				System.out.println("Ok, bom uso!");
				// RESPOSTA NEGATIVA
			} else if (resposta.contains("nao") || resposta.contains("nao")) {
				loopIntroducao = true;
				while (!loopTutorial) {
					System.out.println("\nGostaria de um tutorial?");

					// TUTORIAL
					String tutorial = scan.nextLine();
					tutorial = tutorial.toLowerCase();
					// RESPOSTA POSITIVA
					if (tutorial.contains("sim")) {
						loopTutorial = true;
						mensagens.tutorial();
						// RESPOSTA NEGATIVA
					} else if (tutorial.contains("nao") || tutorial.contains("nao")) {
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
