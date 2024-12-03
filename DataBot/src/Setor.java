public class Setor {
    private String nome;
    private String descricao;

    // CONSTRUTOR
    public Setor(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // GETTERS
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    
    // SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
