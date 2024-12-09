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
    private Integer cod_endereco;
    private Integer cod_cargo;

    // CONSTRUTOR
    public Funcionario(String nome, String projetos, String carga_horaria, String data_admissao, String nascimento,
            String cpf, String telefone, String email, String genero, String rg, Integer cod_endereco, Integer cod_cargo) {
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
    public Integer getCod_endereco(){
        return cod_endereco;
    }
    public Integer getCod_cargo(){
        return cod_cargo;
    }
    //SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setProjetos(String projetos) {
        this.projetos = projetos;
    }
    public void setCarga_horaria(String carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
    public void setData_admissao(String data_admissao) {
        this.data_admissao = data_admissao;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public void setCod_endereco(Integer cod_endereco) {
        this.cod_endereco = cod_endereco;
    }
    public void setCod_cargo(Integer cod_cargo) {
        this.cod_cargo = cod_cargo;
    }

