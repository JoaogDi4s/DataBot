import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO extends GenericDAO<Endereco> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO endereco (numero, bairro, cep, rua, complemento) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE endereo SET numero = ?, bairro = ?, cep = ?, rua = ?, complemento = ? WHERE cep = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM endereco WHERE cep = ?";
    }

    @Override
    protected String getSelectQuery() {
        return "SELECT * FROM endereco WHERE cep = ?";
    }

    @Override
    protected void setParameters(PreparedStatement stmt, Endereco endereco) throws SQLException {
        // Definindo os parâmetros na ordem correta para a consulta de inserção e atualização
        stmt.setString(1, endereco.getNumero());   // Nome é o primeiro parâmetro
        stmt.setString(2, endereco.getBairro());  // Email é o segundo parâmetro
        stmt.setString(3, endereco.getCep());  // Email é o terceiro parâmetro
        stmt.setString(4, endereco.getRua());  // Email é o quarto parâmetro
        stmt.setString(5, endereco.getComplemento());  // Email é o quarto parâmetro
    }

    @Override
    protected Endereco getEntityFromResultSet(ResultSet rs) throws SQLException {
        // Mapeando o ResultSet para o objeto Aluno
        return new Endereco(
            rs.getString("numero"),
            rs.getString("bairro"),
            rs.getString("cep"),
            rs.getString("rua"),
            rs.getString("complemento")
        );
    }

    // Método para limpar a tabela de setor
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

    // Método para atualizar um aluno no banco de dados
    public void atualizar(Endereco endereco) {
        String sql = getUpdateQuery();
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, endereco.getNumero());   // Nome é o primeiro parâmetro
            stmt.setString(2, endereco.getBairro());  // Email é o segundo parâmetro
            stmt.setString(3, endereco.getCep());  // Email é o terceiro parâmetro
            stmt.setString(4, endereco.getRua());  // Email é o quarto parâmetro
            stmt.setString(5, endereco.getComplemento());  // Email é o quarto parâmetro
            stmt.executeUpdate();
            System.out.println("Endereco atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    // Método para deletar um aluno pelo email
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

    // Método para buscar um aluno pelo email
    public Endereco buscarPorCep(String cep) {
        String sql = getSelectQuery();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Endereco endereco = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cep);
            rs = stmt.executeQuery();

            if (rs.next()) {
                endereco = getEntityFromResultSet(rs);
                //System.out.println("Setor encontrado: " + setor.getNome() + ", " + setor.getDescricao());
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
