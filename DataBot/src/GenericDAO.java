import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericDAO<T> {

    // Método abstrato para query de inserção
    protected abstract String getInsertQuery();

    // Método abstrato para query de atualização
    protected abstract String getUpdateQuery();

    // Método abstrato para query de deletar
    protected abstract String getDeleteQuery();

    // Método abstrato para query de busca
    protected abstract String getSelectQuery();

    // Define os parâmetros da query
    protected abstract void setParameters(PreparedStatement stmt, T entity) throws SQLException;

    // Define os parâmetros para buscar por chave única
    protected abstract T getEntityFromResultSet(ResultSet rs) throws SQLException;

    // Salvar um novo objeto no banco de dados
    public void salvar(T entity) {
        String sql = getInsertQuery();
        executeUpdate(sql, entity);
    }

    // Atualizar um objeto no banco de dados
    public void atualizar(T entity) {
        String sql = getUpdateQuery();
        executeUpdate(sql, entity);
    }

    // Deletar um objeto pelo identificador único (neste caso, email)
    public void deletar(String identifier) {
        String sql = getDeleteQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, identifier);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // Buscar um objeto pelo identificador único (neste caso, email)
    public T buscarPorEmail(String email) {
        String sql = getSelectQuery();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        T entity = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                entity = getEntityFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }

        return entity;
    }

    // Método genérico para executar inserts/updates
    private void executeUpdate(String sql, T entity) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            setParameters(stmt, entity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }
}
