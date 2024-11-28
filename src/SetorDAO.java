import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetorDAO extends GenericDAO<Setor> {
    // INSERT
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO setor (nome, descricao) VALUES (?, ?)";
    }

    // UPDATE
    @Override
    protected String getUpdateQuery() {
        return "UPDATE setor SET nome = ?, descricao = ? WHERE nome = ?";
    }

    // DELETE
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM setor WHERE nome = ?";
    }

    // SELECT
    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM setor WHERE nome = ?";
    }

    @Override
    protected void setParameters(PreparedStatement stmt, Setor setor) throws SQLException {
        // DEFININDO OS PARÂMETROSNA ORDEM CORRETA PARA A CONSULTA DE INSERÇÃO E
        // ATUALIZAÇÃO
        stmt.setString(1, setor.getNome());
        stmt.setString(2, setor.getDescricao());
    }

    @Override
    protected Setor getEntityFromResultSet(ResultSet rs) throws SQLException {
        // MAPEANDO RESULTSET PARA OBJETO
        return new Setor(
                rs.getString("nome"),
                rs.getString("descricao"));
    }

    // LIMPAR A TABELA SETOR
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

    // ATUALIZAR UM SETOR NO BD
    public void atualizar(String nomeAtual, Setor setor) {
        String sql = getUpdateQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);  
            stmt.setString(1, setor.getNome());
            stmt.setString(2, setor.getDescricao());
            stmt.setString(3, nomeAtual); // PARA WHERE
            stmt.executeUpdate();
            System.out.println("Setor atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // DELETAR UM SETOR PELO NOME
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

    // BUSCAR UM SETOR PELO NOME
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

    public Setor buscarPorCodigo(String cod_setor) {
        String sql = "SELECT * FROM setor WHERE cod_setor = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Setor setor = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cod_setor);
            rs = stmt.executeQuery();

            if (rs.next()) {
                setor = getEntityFromResultSet(rs);
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