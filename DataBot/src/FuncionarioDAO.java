import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.sql.Date;

public class FuncionarioDAO extends GenericDAO<Funcionario> {

    public boolean validarCpf(String cpf) {
        if (cpf == null) {
            return false;
        }
    
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");
    
        // Verifica se o CPF tem exatamente 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
    
        // Verifica se o CPF é uma sequência repetitiva (ex: 11111111111, 22222222222, etc.)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false; // CPF genérico, não válido
        }
    
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
    
        int primeiroDigito = (soma * 10) % 11;
        if (primeiroDigito == 10)
            primeiroDigito = 0;
        if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
            return false; // O primeiro dígito verificador não bate
        }
    
        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
    
        int segundoDigito = (soma * 10) % 11;
        if (segundoDigito == 10)
            segundoDigito = 0;
        return segundoDigito == Character.getNumericValue(cpf.charAt(10)); // Valida o segundo dígito verificador
    }
    

    // Método para formatar CPF
    public String formatarCpf(String cpf) {
        if (cpf != null && cpf.length() == 11) {
            return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return null;
    }

    // Método para padronizar RG
    public String padronizarRg(String rg) {
        if (rg != null) {
            // Remove tudo que não for número
            return rg.replaceAll("\\D", "");
        }
        return null;
    }

    // Método para validar RG (simples, validando apenas comprimento)
    public boolean validarRg(String rg) {
        if (rg == null) {
            return false;
        }

        // Remove caracteres não numéricos para validação
        rg = rg.replaceAll("\\D", "");

        // Verifica se o RG tem entre 9 e 15 dígitos
        return rg.length() >= 9 && rg.length() <= 15;
    }

    public boolean validarData(String data) {
        try {
            LocalDate.parse(data);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$");
    }

    // INSERT
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO funcionario (nome, projetos, carga_horaria, data_admissao, nascimento, cpf, telefone, email, genero, rg, cod_endereco, cod_cargo) "+
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    } 
    // UPDATE
    @Override
    protected String getUpdateQuery() {
        return "UPDATE funcionario SET nome = ?, projetos = ?, carga_horaria = ?, data_admissao = ?, nascimento = ?, cpf = ?, telefone = ?,"
                +
                "email = ?, genero = ?, rg = ?, cod_endereco = ?, cod_cargo = ?  WHERE cpf = ?";
    }

    // DELETE
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM funcionario WHERE cpf = ?";
    }

    // SELECT
    @Override
    protected String getSelectQuery() {
        return "SELECT funcionario.*, endereco.*, cargo.nome as nome_cargo " + "FROM funcionario INNER JOIN " +
                "endereco ON funcionario.cod_endereco = endereco.cod_endereco INNER JOIN " +
                "cargo ON funcionario.cod_cargo = cargo.cod_cargo " +
                "WHERE LOWER(funcionario.nome) LIKE LOWER(?)";
    }

    @Override
    protected void setParameters(PreparedStatement stmt, Funcionario funcionario) throws SQLException {
        // DEFININDO OS PARÂMETROSNA ORDEM CORRETA PARA A CONSULTA DE INSERÇÃO E
        // ATUALIZAÇÃO
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getProjetos());
        stmt.setString(3, funcionario.getCarga_horaria());
        stmt.setString(4, funcionario.getData_admissao());
        stmt.setString(5, funcionario.getNascimento());
        stmt.setString(6, funcionario.getCpf());
        stmt.setString(7, funcionario.getTelefone());
        stmt.setString(8, funcionario.getEmail());
        stmt.setString(9, funcionario.getGenero());
        stmt.setString(10, funcionario.getRg());
        if (funcionario.getCod_endereco() != null) {
            stmt.setInt(11, funcionario.getCod_endereco());
        } else {
            stmt.setNull(11, java.sql.Types.INTEGER);
        }
        if (funcionario.getCod_cargo() != null) {
            stmt.setInt(12, funcionario.getCod_cargo());
        } else {
            stmt.setNull(12, java.sql.Types.INTEGER);
        }
    }

    @Override
    protected Funcionario getEntityFromResultSet(ResultSet rs) throws SQLException {
        // MAPEANDO RESULTSET PARA OBJETO
        Integer codEndereco = rs.getInt("cod_endereco");
        if (rs.wasNull()) {
            codEndereco = null; // Se o valor for null no banco
        }

        Integer codCargo = rs.getInt("cod_cargo");
        if (rs.wasNull()) {
            codCargo = null; // Se o valor for null no banco
        }

        return new Funcionario(
                rs.getString("nome"),
                rs.getString("projetos"),
                rs.getString("carga_horaria"),
                rs.getString("data_admissao"),
                rs.getString("nascimento"),
                rs.getString("cpf"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("genero"),
                rs.getString("rg"),
                codEndereco,
                codCargo);
    }

    // LIMPAR A TABELA FUNCIONARIO
    public void limparTabela() {
        String sql = "DELETE FROM funcionario";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Tabela de funcionario limpa!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // ATUALIZAR FUNCIONARIO NO BD
    public void atualizar(String CPF, Funcionario funcionario) {
        String sql = getUpdateQuery(); // Query de atualização
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);

            // Valida e formata CPF
            String cpf = funcionario.getCpf();
            if (validarCpf(cpf)) {
                cpf = formatarCpf(cpf); // Formata o CPF para "xxx.xxx.xxx-xx"
            } else {
                throw new IllegalArgumentException("CPF inválido: " + cpf);
            }

            // Valida e padroniza RG
            String rg = funcionario.getRg();
            if (validarRg(rg)) {
                rg = padronizarRg(rg); // Padroniza o RG
            } else {
                throw new IllegalArgumentException("RG inválido: " + rg);
            }

            // Definindo os parâmetros
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getProjetos());
            stmt.setString(3, funcionario.getCarga_horaria());

            // Converte datas para SQL
            stmt.setDate(4, Date.valueOf(funcionario.getData_admissao())); // Formato "yyyy-MM-dd"
            stmt.setDate(5, Date.valueOf(funcionario.getNascimento())); // Formato "yyyy-MM-dd"

            stmt.setString(6, cpf); // CPF formatado
            stmt.setString(7, funcionario.getTelefone());
            stmt.setString(8, funcionario.getEmail());
            stmt.setString(9, funcionario.getGenero());
            stmt.setString(10, rg); // RG padronizado

            // Tratamento para cod_endereco
            if (funcionario.getCod_endereco() != null) {
                stmt.setInt(11, funcionario.getCod_endereco());
            } else {
                stmt.setNull(11, java.sql.Types.INTEGER);
            }

            // Tratamento para cod_cargoQA
            if (funcionario.getCod_cargo() != null) {
                stmt.setInt(12, funcionario.getCod_cargo());
            } else {
                stmt.setNull(12, java.sql.Types.INTEGER);
            }

            // WHERE para atualização
            stmt.setString(13, CPF); // CPF original fornecido como argumento

            // Executa atualização
            stmt.executeUpdate();
            System.out.println("Funcionário atualizado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace(); // Exibe o erro para facilitar o debug
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } finally {
            // Garantindo que os recursos serão fechados
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // DELETAR UM FUNCIONARIO PELO CPF
    public void deletar(String cpf) {
        String sql = getDeleteQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            System.out.println("funcionario deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // BUSCAR UM FUNCIONARIO PELO NOME
    public Funcionario buscarPorNome(String nome) {
        String sql = getSelectQuery();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome.toLowerCase() + "%");
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = getEntityFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }

        return funcionario;
    }

    public Funcionario buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf); // Busca exata
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = getEntityFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }

        return funcionario;
    }

}