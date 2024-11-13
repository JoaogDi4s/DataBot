import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetorDAO extends GenericDAO<Setor> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO setor (nome, descricao) VALUES (?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE setor SET nome = ?, descricao = ? WHERE nome = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM setor WHERE nome = ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM setor WHERE nome = ?";
    }

    @Override
    protected void setParameters(PreparedStatement stmt, Setor setor) throws SQLException {
        // Definindo os parâmetros na ordem correta para a consulta de inserção e atualização
        stmt.setString(1, setor.getNome());   // Nome é o primeiro parâmetro
        stmt.setString(2, setor.getDescricao());  // Email é o segundo parâmetro
    }

    @Override
    protected Setor getEntityFromResultSet(ResultSet rs) throws SQLException {
        // Mapeando o ResultSet para o objeto Aluno
        return new Setor(
            rs.getString("nome"),
            rs.getString("descricao")
        );
    }

    // Método para limpar a tabela de setor
    public void limparTabela() {
        String sql = "DELETE FROM setor";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Tabela de setor limpa!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // Método para atualizar um aluno no banco de dados
    public void atualizar(Setor setor) {
        String sql = getUpdateQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, setor.getNome());   // Nome
            stmt.setString(2, setor.getDescricao());     // Idade
            stmt.executeUpdate();
            System.out.println("Setor atualizado com sucesso!");
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
            System.out.println("Setor deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // Método para buscar um aluno pelo email
    public Setor buscarPorNome(String nome) {
        String sql = getSelectQuery();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Setor setor = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            if (rs.next()) {
                setor = getEntityFromResultSet(rs);
                //System.out.println("Setor encontrado: " + setor.getNome() + ", " + setor.getDescricao());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }

        return setor;
    }
}
