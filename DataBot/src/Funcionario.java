public class Funcionario {
    private String nome;
    private String projetos;
    private String cargaHoraria;
    private String dataAdmissao;
    private String nascimento;
    private String cpfRg;
    private String telefone;
    private String email;
    private String genero;

    // CONSTRUTOR
    public Funcionario(String nome, String projetos, String cargaHoraria, String dataAdmissao, String nascimento,
            String cpfRg, String telefone, String email, String genero) {
        this.nome = nome;
        this.projetos = projetos;
        this.cargaHoraria = cargaHoraria;
        this.dataAdmissao = dataAdmissao;
        this.nascimento = nascimento;
        this.cpfRg = cpfRg;
        this.telefone = telefone;
        this.email = email;
        this.genero = genero;
    }
    

    // GETTERS
    public String getNome() {
        return nome;
    }
    public String getProjetos() {
        return projetos;
    }
    public String getCargaHoraria() {
        return cargaHoraria;
    }
    public String getDataAdmissao() {
        return dataAdmissao;
    }
    public String getNascimento() {
        return nascimento;
    }
    public String getCpfRg() {
        return cpfRg;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public String getGenero() {
        return genero;
    }
}
