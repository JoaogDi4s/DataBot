import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CargoDAO extends GenericDAO<Cargo> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO cargo (nome, salarioBase, hierarquia, requisitos) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE cargo SET nome = ?, salarioBase = ?, hierarquia = ?, requisitos = ? WHERE nome = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM cargo WHERE nome = ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM cargo WHERE nome = ?";
    }

    @Override
    protected void setParameters(PreparedStatement stmt, Cargo cargo) throws SQLException {
        // Definindo os parâmetros na ordem correta para a consulta de inserção e atualização
        stmt.setString(1, cargo.getNome());   // Nome é o primeiro parâmetro
        stmt.setString(2, cargo.getSalarioBase());  // Email é o segundo parâmetro
        stmt.setString(3, cargo.getHierarquia());  // Email é o terceiro parâmetro
        stmt.setString(4, cargo.getRequisitos());  // Email é o quarto parâmetro
    }

    @Override
    protected Cargo getEntityFromResultSet(ResultSet rs) throws SQLException {
        // Mapeando o ResultSet para o objeto Aluno
        return new Cargo(
            rs.getString("nome"),
            rs.getString("salarioBase"),
            rs.getString("hierarquia"),
            rs.getString("requisitos")
        );
    }

    // Método para limpar a tabela de setor
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

     // Método para atualizar um aluno no banco de dados
     public void atualizar(Cargo cargo) {
        String sql = getUpdateQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cargo.getNome());   // Nome
            stmt.setString(2, cargo.getSalarioBase());     // Idade
            stmt.setString(3, cargo.getHierarquia());     // HIERARQUIA
            stmt.setString(4, cargo.getRequisitos());     // requisitos
            stmt.executeUpdate();
            System.out.println("Cargo atualizado com sucesso!");
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
            System.out.println("cargo deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // Método para buscar um aluno pelo email
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
                //System.out.println("Setor encontrado: " + setor.getNome() + ", " + setor.getDescricao());
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