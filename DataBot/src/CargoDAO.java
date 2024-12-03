import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CargoDAO extends GenericDAO<Cargo> {
    // INSERT
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO cargo (nome, salarioBase, hierarquia, requisitos, cod_setor) VALUES (?, ?, ?, ?, ?)";
    }

    // UPDATE
    @Override
    protected String getUpdateQuery() {
        return "UPDATE cargo SET nome = ?, salarioBase = ?, hierarquia = ?, requisitos = ?, cod_setor = ? WHERE nome = ?";
    }

    // DELETE
    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM cargo WHERE nome = ?";
    }

    // SELECT
    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM cargo WHERE nome = ?";
    }

    @Override
    protected void setParameters(PreparedStatement stmt, Cargo cargo) throws SQLException {
        // DEFININDO OS PARÂMETROSNA ORDEM CORRETA PARA A CONSULTA DE INSERÇÃO E
        // ATUALIZAÇÃO
        stmt.setString(1, cargo.getNome());
        stmt.setString(2, cargo.getSalarioBase());
        stmt.setString(3, cargo.getHierarquia());
        stmt.setString(4, cargo.getRequisitos());
        stmt.setString(5, cargo.getCod_setor());


    }

    @Override
    protected Cargo getEntityFromResultSet(ResultSet rs) throws SQLException {
        // MAPEANDO RESULTSET PARA OBJETO
        return new Cargo(
                rs.getString("nome"),
                rs.getString("salarioBase"),
                rs.getString("hierarquia"),
                rs.getString("requisitos"),
                rs.getString("cod_setor"));

    }

    // LIMPAR A TABELA CARGO
    public void limparTabela() {
        String sql = "DELETE FROM cargo";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            System.out.println("Tabela de cargo limpa!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // ATUALIZAR UM CARGO NO BD
    public void atualizar(String nomeAtual, Cargo cargo) {
        String sql = getUpdateQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cargo.getNome());
            stmt.setString(2, cargo.getSalarioBase());
            stmt.setString(3, cargo.getHierarquia());
            stmt.setString(4, cargo.getRequisitos());
            stmt.setString(5, cargo.getCod_setor());
            stmt.setString(6, cargo.getNome()); // PARA WHERE
            stmt.executeUpdate();
            System.out.println("Cargo atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // DELETAR UM CARGO PELO NOME
    public void deletar(String nome) {
        String sql = getDeleteQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.executeUpdate();
            System.out.println("cargo deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // BUSCAR UM CARGO PELO NOME
    public Cargo buscarPorNome(String nome) {
        String sql = getSelectQuery();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cargo cargo = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cargo = getEntityFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }

        return cargo;
    }

    public Cargo buscarPorCodigo(Integer cod_cargo) {
        String sql = "SELECT * FROM cargo WHERE cod_cargo = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cargo cargo = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            if (cod_cargo != null) {
                stmt.setInt(1, cod_cargo);
            } else {
                throw new IllegalArgumentException("O código do endereço não pode ser nulo.");
            }
            rs = stmt.executeQuery();

            if (rs.next()) {
                cargo = getEntityFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }

        return cargo;
    }
}