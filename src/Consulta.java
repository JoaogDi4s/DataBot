import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consulta {
    static Scanner scan = new Scanner(System.in);
    static boolean sair = false;

    // FUNCIONÁRIO
    public static List<String> consultaFuncionario(Connection conn, String nome, String camposDesejados)
            throws SQLException {

        sair = false;

        String sql = "SELECT funcionario.*, endereco.*, cargo.nome as nome_cargo " + "FROM funcionario " +
                "INNER JOIN endereco ON funcionario.cod_endereco = endereco.cod_endereco " +
                "INNER JOIN cargo ON funcionario.cod_cargo = cargo.cod_cargo " +
                "WHERE funcionario.nome LIKE ?";

        List<String> resultado = new ArrayList<>();

        // ESCOLHA DOS CAMPOS
        String[] campos = camposDesejados.toLowerCase().split(",\\s*");

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");

            try (ResultSet dados = stmt.executeQuery()) {
                while (dados.next() && !sair) {
                    resultado.add("\n-----------------\n");

                    // MOSTRAR CAMPOS ESCOLHIDOS
                    for (String campo : campos) {
                        switch (campo.trim()) {
                            case "id":
                                resultado.add(String.format("%-12s: %s", "Id", dados.getInt("cod_funcionario")));
                                break;
                            case "cargo":
                                resultado.add(String.format("%-12s: %s", "cargo", dados.getString("nome_cargo")));
                                break;
                            case "projetos":
                                resultado.add(String.format("%-12s: %s", "projetos", dados.getInt("projetos")));
                                break;
                            case "carga horaria":
                            case "carga horária":
                                resultado.add(
                                        String.format("%-12s: %s", "carga horaria", dados.getString("carga_horaria")));
                                break;
                            case "data de admissão":
                            case "data de admissao":
                                resultado.add(String.format("%-12s: %s", "data de admissao",
                                        dados.getString("data_admissao")));
                                break;
                            case "nome":
                                resultado.add(String.format("%-12s: %s", "nome", dados.getString("nome")));
                                break;
                            case "nascimento":
                                resultado.add(String.format("%-12s: %s", "nascimento", dados.getString("nascimento")));
                                break;
                            case "telefone":
                                resultado.add(String.format("%-12s: %s", "telefone", dados.getString("telefone")));
                                break;
                            case "email":
                                resultado.add(String.format("%-12s: %s", "email", dados.getString("email")));
                                break;
                            case "genero":
                                resultado.add(String.format("%-12s: %s", "genero", dados.getString("genero")));
                                break;
                            case "cpf":
                                resultado.add(String.format("%-12s: %s", "cpf", dados.getString("cpf_rg")));
                                break;
                            case "endereco":
                            case "endereço":
                                System.out.println(
                                        "Deseja o endereço completo ou informações separadas? (Digite 'completo' ou 'separado')");
                                String resposta = InputUtils.getInput(scan);

                                if ("completo".equals(resposta)) {
                                    // Pegando os valores de cada campo de endereço
                                    String rua = dados.getString("rua");
                                    String numero = dados.getString("numero");
                                    String bairro = dados.getString("bairro");
                                    String complemento = dados.getString("complemento");
                                    String cep = dados.getString("cep");

                                    // Se algum campo for NULL, não mostra nada
                                    StringBuilder enderecoCompleto = new StringBuilder();

                                    if (rua != null)
                                        enderecoCompleto.append(rua);
                                    if (numero != null)
                                        enderecoCompleto.append(", ").append(numero);
                                    if (bairro != null)
                                        enderecoCompleto.append(", ").append(bairro);
                                    if (complemento != null)
                                        enderecoCompleto.append(", ").append(complemento);
                                    if (cep != null)
                                        enderecoCompleto.append(", ").append(cep);

                                    // Adiciona o endereço completo se algum campo estiver disponível
                                    if (enderecoCompleto.length() > 0) {
                                        resultado.add(String.format("%-12s: %s", "endereço completo",
                                                enderecoCompleto.toString()));
                                    }
                                } else if ("separado".equals(resposta)) {
                                    System.out.println(
                                            "Escolha os campos do endereço que deseja ver: rua, numero, bairro, complemento, cep");
                                    String camposEndereco = InputUtils.getInput(scan);
                                    String[] camposEspecificos = camposEndereco.split(",\\s*");

                                    for (String campoEndereco : camposEspecificos) {
                                        switch (campoEndereco) {
                                            case "numero":
                                            case "número":
                                                String numero = dados.getString("numero");
                                                if (numero != null) {
                                                    resultado.add(String.format("%-12s: %s", "número", numero));
                                                }
                                                break;
                                            case "bairro":
                                                String bairro = dados.getString("bairro");
                                                if (bairro != null) {
                                                    resultado.add(String.format("%-12s: %s", "bairro", bairro));
                                                }
                                                break;
                                            case "cep":
                                                String cep = dados.getString("cep");
                                                if (cep != null) {
                                                    resultado.add(String.format("%-12s: %s", "CEP", cep));
                                                }
                                                break;
                                            case "rua":
                                                String rua = dados.getString("rua");
                                                if (rua != null) {
                                                    resultado.add(String.format("%-12s: %s", "rua", rua));
                                                }
                                                break;
                                            case "complemento":
                                                String complemento = dados.getString("complemento");
                                                if (complemento != null) {
                                                    resultado.add(
                                                            String.format("%-12s: %s", "complemento", complemento));
                                                }
                                                break;
                                            default:
                                                System.out.println("Campo '" + campoEndereco + "' não reconhecido.");
                                                break;
                                        }
                                    }
                                } else {
                                    System.out.println("Opção inválida. Ignorando campo 'endereço'.");
                                }
                                break;
                            case "sair":
                                sair = true;
                                break;

                        }
                        if (sair)
                            break; // Interrompe o loop for se "sair" for selecionado
                    }
                    resultado.add("\n-----------------\n");
                }
            }
        }
        return resultado;
    }

    // SETOR    
    public static List<String> consultaSetor(Connection conn, String nome, String camposDesejados)
            throws SQLException {

        sair = false;

        String sql = "SELECT * FROM setor WHERE nome LIKE ?";
        List<String> resultado = new ArrayList<>();

        // ESCOLHA DOS CAMPOS
        String[] campos = camposDesejados.toLowerCase().split(",\\s*");

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");

            try (ResultSet dados = stmt.executeQuery()) {
                while (dados.next()) {
                    resultado.add("\n-----------------\n");

                    // MOSTRAR CAMPOS ESCOLHIDOS
                    for (String campo : campos) {
                        switch (campo.trim()) {
                            case "codigo":
                                resultado.add(String.format("%-12s: %s", "Id", dados.getInt("cod_setor")));
                                break;
                            case "nome":
                                resultado.add(String.format("%-12s: %s", "nome", dados.getString("nome")));
                                break;
                            case "descricao":
                            case "descrição":
                                resultado.add(String.format("%-12s: %s", "descricao", dados.getString("descricao")));
                                break;
                            case "sair":
                                sair = true;
                                break;
                        }
                    }
                    resultado.add("\n-----------------\n");
                }
            }
        }
        return resultado;
    }

    // CARGOS
    public static List<String> consultaCargo(Connection conn, String nome, String camposDesejados)
            throws SQLException {

        sair = false;

        String sql = "SELECT setor.nome as nome_setor, cargo.* " +
             "FROM cargo " +
             "INNER JOIN setor ON setor.cod_setor = cargo.cod_setor " +
             "WHERE cargo.nome LIKE ?";

        List<String> resultado = new ArrayList<>();

        // ESCOLHA DOS CAMPOS
        String[] campos = camposDesejados.toLowerCase().split(",\\s*");

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");

            try (ResultSet dados = stmt.executeQuery()) {
                while (dados.next()) {
                    resultado.add("\n-----------------\n");

                    // MOSTRAR CAMPOS ESCOLHIDOS
                    for (String campo : campos) {
                        switch (campo.trim()) {
                            case "codigo":
                                resultado.add(String.format("%-12s: %s", "Id", dados.getInt("cod_cargo")));
                                break;
                            case "setor":
                                resultado.add(String.format("%-12s: %s", "Setor", dados.getString("nome_setor")));
                                break;
                            case "salário base":
                            case "salario base":
                                resultado.add(String.format("%-12s: %s", "Salário", dados.getDouble("salarioBase")));
                                break;
                            case "requisitos":
                                resultado.add(String.format("%-12s: %s", "Requisitos", dados.getString("requisitos")));
                                break;
                            case "nome":
                                resultado.add(String.format("%-12s: %s", "Nome", dados.getString("nome")));
                                break;
                            case "hierarquia":
                                resultado.add(String.format("%-12s: %s", "Hierarquia", dados.getString("hierarquia")));
                                break;
                            case "sair":
                                sair = true;
                                break;
                        }
                    }
                    resultado.add("\n-----------------\n");
                }
            }
        }
        return resultado;
    }

}