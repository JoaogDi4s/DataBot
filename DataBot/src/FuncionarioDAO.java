import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO extends GenericDAO<Funcionario> {
    // INSERT
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO funcionario (nome, projetos, carga_horaria, data_admissao, nascimento, cpf, telefone, email, genero, rg, cod_endereco, cod_cargo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        stmt.setString(11, funcionario.getCod_endereco());
        stmt.setString(12, funcionario.getCod_cargo());
    }

    @Override
    protected Funcionario getEntityFromResultSet(ResultSet rs) throws SQLException {
        // MAPEANDO RESULTSET PARA OBJETO
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
                rs.getString("cod_endereco"),
                rs.getString("cod_cargo")
                );
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
    public void atualizar(Funcionario funcionario) {
        String sql = getUpdateQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
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
            stmt.setString(11, funcionario.getCod_endereco());
            stmt.setString(12, funcionario.getCod_cargo());
            stmt.setString(13, funcionario.getCpf()); // PARA WHERE
            stmt.executeUpdate();
            System.out.println("funcionario atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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