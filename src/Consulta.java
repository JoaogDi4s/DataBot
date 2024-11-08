import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Consulta {
    // FUNCIONÁRIO
    public static List<String> consultaFuncionario(Connection conn, String nome, String camposDesejados)
            throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE nome LIKE ?";
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
                            case "id":
                                resultado.add(String.format("%-12s: %s", "Id", dados.getInt("cod_funcionario")));
                                break;
                            case "cargo":
                                resultado.add(String.format("%-12s: %s", "cargo", dados.getString("cod_cargo")));
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
                                resultado.add(String.format("%-12s: %s", "endereço", dados.getString("endereco")));
                                break;
                        }
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
        String sql = "SELECT * FROM cargo WHERE nome LIKE ?";
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
                                resultado.add(String.format("%-12s: %s", "Setor", dados.getInt("cod_setor")));
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
                        }   
                    }
                    resultado.add("\n-----------------\n");
                }
            }
        }
        return resultado;
    }

}
