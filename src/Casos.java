import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class Casos {
    Mensagens msg = new Mensagens();
    Scanner scan = new Scanner(System.in);

    public void casoFuncionario(Connection conn) {
        try {
            boolean continuar = true;
            while (continuar) {
                System.out.println(msg.pergunta1());
                String resposta1 = InputUtils.getInput(scan);

                System.out.println(msg.pergunta1_1());
                String resposta1_1 = InputUtils.getInput(scan);
                List<String> resultadoFuncionario = Consulta.consultaFuncionario(conn, resposta1, resposta1_1);
                resultadoFuncionario.forEach(System.out::println);
    
                // Pergunta se o usuário deseja continuar
                System.out.println(msg.peguntaSair());
                String respostaSair = InputUtils.getInput(scan);
    
                // Verificar se o usuário quer encerrar
                if (respostaSair.equalsIgnoreCase("não") || respostaSair.equalsIgnoreCase("nao")) {
                    continuar = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados do funcionário: " + e.getMessage());
            e.printStackTrace();
        } 
    }
    
    public void casoSetor(Connection conn){
        try {
            boolean continuar = true;
            while (continuar) {
                System.out.println(msg.pergunta2());
                String resposta2 = InputUtils.getInput(scan);
                System.out.println(msg.pergunta2_1());
                String resposta2_1 = InputUtils.getInput(scan);
                List<String> resultadoSetor = Consulta.consultaSetor(conn, resposta2, resposta2_1);
                resultadoSetor.forEach(System.out::println);
    
                // Pergunta se o usuário deseja continuar
                System.out.println(msg.peguntaSair());
                String respostaSair = InputUtils.getInput(scan);
    
                // Verificar se o usuário quer encerrar
                if (respostaSair.equalsIgnoreCase("não") || respostaSair.equalsIgnoreCase("nao")) {
                    continuar = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados do setor: " + e.getMessage());
            e.printStackTrace();
        } 
    }

    public void casoCargo(Connection conn){
        try {
            boolean continuar = true;
            while (continuar) {
                System.out.println(msg.pergunta3());
                String resposta3 = InputUtils.getInput(scan);
                System.out.println(msg.pergunta3_1());
                String resposta3_1 = InputUtils.getInput(scan);
                List<String> resultadoCargo = Consulta.consultaCargo(conn, resposta3, resposta3_1);
                resultadoCargo.forEach(System.out::println);
    
                // Pergunta se o usuário deseja continuar
                System.out.println(msg.peguntaSair());
                String respostaSair = InputUtils.getInput(scan);
    
                // Verificar se o usuário quer encerrar
                if (respostaSair.equalsIgnoreCase("não") || respostaSair.equalsIgnoreCase("nao")) {
                    continuar = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar dados do cargo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
