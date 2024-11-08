import java.util.Scanner;

public class Usuario {
	Scanner scan = new Scanner(System.in);

	private String username;
	private String senha;
	private String confirmarSenha;
	private String empresa;
	
	// GETTERS
	public String getUsername() {
		return username;
	}
	public String getSenha() {
		return senha;
	}
	public String getConfirmarSenha() {
		return confirmarSenha;
	}
	public String getEmpresa() {
		return empresa;
	}

	// REGISTRAR USUARIO
	public void Registrar() {
		System.out.println("Qual será o nome de usuário?");
		username = scan.nextLine();

		System.out.println("Digite uma senha:");
		senha = scan.nextLine();

		System.out.println("Digite a senha novamente:");
		boolean senhaVerificada = false;
		do {
			 confirmarSenha = scan.nextLine();
			if (!confirmarSenha.equals(senha)) {
				System.out.println("As senhas estão diferentes");
				System.out.println("Digite a senha novamente:");
			} else {
				senhaVerificada = true;
			}
		} while (!senhaVerificada);

		System.out.println("Digite o nome da empresa:");
		empresa = scan.nextLine();
	}

	 // LOGAR
    public void Logar() {
        boolean loginBemSucedido = false;  
        do {
            System.out.println("Digite seu usuário:");
            String tUsername = scan.nextLine();
            System.out.println("Digite sua senha:");
            String tSenha = scan.nextLine();
            System.out.println("Digite sua empresa:");
            String tEmpresa = scan.nextLine();

            if (Verificar(tUsername, tSenha, tEmpresa)) {
                System.out.println("\nLogin realizado com sucesso!");
                loginBemSucedido = true;}  
             else {
                System.out.println("\nFalha ao fazer Login, tente novamente.");
            }
        } while (!loginBemSucedido);  
    }

    // VERIFICAR LOGIN (CRIADO OU ADMIN)
    public boolean Verificar(String tUsername, String tSenha, String tEmpresa) {
        return tUsername.equals(this.username) && tSenha.equals(this.senha) && tEmpresa.equals(this.empresa) ||
		(tUsername.equals("admin") && tSenha.equals("admin") && tEmpresa.equals("DataBot"));
    }
}
