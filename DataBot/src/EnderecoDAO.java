import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO extends GenericDAO<Endereco> {
    // INSERT
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO endereco (numero, bairro, cep, rua, complemento) VALUES (?, ?, ?, ?, ?)";
    }

    // UPDATE
    @Override
    protected String getUpdateQuery() {
        return "UPDATE endereco SET numero = ?, bairro = ?, cep = ?, rua = ?, complemento = ? WHERE cep = ?";
    }

    // DELETE
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM endereco WHERE cep = ?";
    }

    // SELECT
    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM endereco WHERE cep = ?";
    }

    @Override
    protected void setParameters(PreparedStatement stmt, Endereco endereco) throws SQLException {
        // DEFININDO OS PARÂMETROSNA ORDEM CORRETA PARA A CONSULTA DE INSERÇÃO E
        // ATUALIZAÇÃO
        stmt.setString(1, endereco.getNumero());
        stmt.setString(2, endereco.getBairro());
        stmt.setString(3, endereco.getCep());
        stmt.setString(4, endereco.getRua());
        stmt.setString(5, endereco.getComplemento());
    }

    @Override
    protected Endereco getEntityFromResultSet(ResultSet rs) throws SQLException {
        // MAPEANDO RESULTSET PARA OBJETO
        return new Endereco(
                rs.getString("numero"),
                rs.getString("bairro"),
                rs.getString("cep"),
                rs.getString("rua"),
                rs.getString("complemento"));
    }

    // LIMPAR A TABELA ENDERECO
    public void limparTabela() {
        String sql = "DELETE FROM endereco";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Tabela de endereco limpa!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // ATUALIZAR UM ENDERECO NO BD
    public void atualizar(Endereco endereco) {
        String sql = getUpdateQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, endereco.getNumero());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCep());
            stmt.setString(4, endereco.getRua());
            stmt.setString(5, endereco.getComplemento());
            stmt.setString(6, endereco.getCep()); // PARA WHERE
            stmt.executeUpdate();
            System.out.println("Endereco atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // DELETAR UM ENDERECO PELO CEP
    public void deletar(String cep) {
        String sql = getDeleteQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cep);
            stmt.executeUpdate();
            System.out.println("endereco deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // BUSCAR UM ENDERECO PELO CODIGO
   public Endereco buscarPorCodigo(String cod_edereco) {
    String sql = "SELECT * FROM endereco WHERE cod_endereco = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Endereco endereco = null;

    try {
        conn = DatabaseConnection.getConnection();
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, cod_edereco);
        rs = stmt.executeQuery();

        if (rs.next()) {
            endereco = getEntityFromResultSet(rs);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        DatabaseConnection.closeResultSet(rs);
        DatabaseConnection.closeStatement(stmt);
        DatabaseConnection.closeConnection(conn);
    }

    return endereco;
}

}