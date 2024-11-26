public class Funcionario {
    private String nome;
    private String projetos;
    private String carga_horaria;
    private String data_admissao;
    private String nascimento;
    private String cpf;
    private String telefone;
    private String email;
    private String genero;
    private String rg;
    private String cod_endereco;
    private String cod_cargo;

    // CONSTRUTOR
    public Funcionario(String nome, String projetos, String carga_horaria, String data_admissao, String nascimento,
            String cpf, String telefone, String email, String genero, String rg, String cod_endereco, String cod_cargo) {
        this.nome = nome;
        this.projetos = projetos;
        this.carga_horaria = carga_horaria;
        this.data_admissao = data_admissao;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.genero = genero;
        this.rg = rg;
        this.cod_endereco = cod_endereco;
        this.cod_cargo = cod_cargo;
    }
    

    // GETTERS
    public String getNome() {
        return nome;
    }
    public String getProjetos() {
        return projetos;
    }
    public String getCarga_horaria() {
        return carga_horaria;
    }
    public String getData_admissao() {
        return data_admissao;
    }
    public String getNascimento() {
        return nascimento;
    }
    public String getCpf() {
        return cpf;
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
    public String getRg(){
        return rg;
    }
    public String getCod_endereco(){
        return cod_endereco;
    }
    public String getCod_cargo(){
        return cod_cargo;
    }
}
