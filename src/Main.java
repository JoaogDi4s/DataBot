import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // DADOS DO BD
        String nomeBanco = "databot";
        String usuario = "root";
        String senha = "";

        System.out.println();
        // Uso do try-with-resources para fechar automaticamente os recursos
        try (
                // INICIA COMUNICAÇÃO COM BF
                Connection conn = DataBaseUtils.getConnection(nomeBanco, usuario, senha);
                Scanner scan = new Scanner(System.in)) {
                System.out.println("Conexão estabelecida com sucesso!");

            // OBJETOS
            Introducao intro = new Introducao();
            Mensagens msg = new Mensagens();
            Casos casos = new Casos();

            // INTRODUÇÃO (LOGIN E TUTORIAL)
             //intro.introducao();

            boolean continuar = true;
            while (continuar) {
                System.out.println(msg.pergunta0());
                String resposta0 = InputUtils.getInput(scan);

                switch (resposta0) {
                    case "funcionario":
                    case "funcionário":
                        casos.casoFuncionario(DataBaseUtils.getConnection(nomeBanco, usuario, senha));
                        break;

                    case "setor":
                        casos.casoSetor(DataBaseUtils.getConnection(nomeBanco, usuario, senha));
                        break;

                    case "cargo":
                        casos.casoCargo(DataBaseUtils.getConnection(nomeBanco, usuario, senha));
                        break;
                    case "sair":
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        continue; // Reinicia o loop ao detectar uma entrada inválida
                }

                if (!resposta0.equals("sair")) {
                    System.out.println(msg.peguntaSair());
                    String respostaContinuar = InputUtils.getInput(scan); // Pergunta se quer continuar
                
                    if (respostaContinuar.equals("nao") || respostaContinuar.equals("não")) {
                        continuar = false;
                    }
                }
                
            }

            System.out.println("Conexão fechada.");

        } catch (ClassNotFoundException e) {
            System.err.println("Driver não encontrado: " + e.getMessage()); // CASO NÃO ENCONTRE O DRIVER
        } catch (SQLException e) {
            System.err.println("Erro no SQL: " + e.getMessage()); // CASO ERRO NO SQL
        }
    }
}