import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FuncionarioDAO extends GenericDAO<Funcionario>{
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO funcionario (nome, projetos, cargaHoraria, dataAdmissao, nascimento, cpf_rg, telefone, email, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE funcionario SET nome = ?, projetos = ?, cargaHoraria = ?, dataAdmissao = ?, nascimento = ?, cpfRg = ?, telefone = ?" +
        "email = ?, genero = ?  WHERE cpf_rg = ?";
    } 

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM funcionario WHERE cpf_rg = ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM funcionario WHERE cpf_rg = ?";
    }

    @Override
    protected void setParameters(PreparedStatement stmt, Funcionario funcionario) throws SQLException {
        // Definindo os parâmetros na ordem correta para a consulta de inserção e atualização
        stmt.setString(1, funcionario.getNome());   // Nome é o primeiro parâmetro
        stmt.setString(2, funcionario.getProjetos());  // Email é o segundo parâmetro
        stmt.setString(3, funcionario.getCargaHoraria());  // Email é o terceiro parâmetro
        stmt.setString(4, funcionario.getDataAdmissao());  // Email é o quarto parâmetro
        stmt.setString(5, funcionario.getNascimento());  // Email é o quarto parâmetro
        stmt.setString(6, funcionario.getCpfRg());  // Email é o quarto parâmetro
        stmt.setString(7, funcionario.getTelefone());  // Email é o quarto parâmetro
        stmt.setString(8, funcionario.getEmail());  // Email é o quarto parâmetro
        stmt.setString(9, funcionario.getGenero());  // Email é o quarto parâmetro

    }

    @Override
    protected Funcionario getEntityFromResultSet(ResultSet rs) throws SQLException {
        // Mapeando o ResultSet para o objeto Aluno
        return new Funcionario(
            rs.getString("nome"),
            rs.getString("projetos"),
            rs.getString("carga_horaria"),
            rs.getString("data_admissao"),
            rs.getString("nascimento"),
            rs.getString("cpf_rg"),
            rs.getString("telefone"),
            rs.getString("email"),
            rs.getString("genero")
        );
    }

    // Método para limpar a tabela de setor
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

    // Método para atualizar um aluno no banco de dados
    public void atualizar(Funcionario funcionario) {
        String sql = getUpdateQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());   // Nome é o primeiro parâmetro
            stmt.setString(2, funcionario.getProjetos());  // Email é o segundo parâmetro
            stmt.setString(3, funcionario.getCargaHoraria());  // Email é o terceiro parâmetro
            stmt.setString(4, funcionario.getDataAdmissao());  // Email é o quarto parâmetro
            stmt.setString(5, funcionario.getNascimento());  // Email é o quarto parâmetro
            stmt.setString(6, funcionario.getCpfRg());  // Email é o quarto parâmetro
            stmt.setString(7, funcionario.getTelefone());  // Email é o quarto parâmetro
            stmt.setString(8, funcionario.getEmail());  // Email é o quarto parâmetro
            stmt.setString(9, funcionario.getGenero());  // Email é o quarto parâmetro
            stmt.executeUpdate();
            System.out.println("funcionario atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // Método para deletar um aluno pelo email
    public void deletar(String nome) {
        String sql = getDeleteQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();
            System.out.println("funcionario deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // Método para buscar um aluno pelo email
    public Funcionario buscarPorCpfRg(String cpfRg) {
        String sql = getSelectQuery();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario funcionario = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpfRg);
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario = getEntityFromResultSet(rs);
                //System.out.println("Setor encontrado: " + setor.getNome() + ", " + setor.getDescricao());
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
