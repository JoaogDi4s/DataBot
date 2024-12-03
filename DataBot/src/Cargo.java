public class Cargo{
    private String nome;
    private String salarioBase;
    private String hierarquia;
    private String requisitos;
    private String cod_setor;

    // CONSTRUTOR
    public Cargo(String nome, String salarioBase, String hierarquia, String requisito, String cod_setor) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.hierarquia = hierarquia;
        this.requisitos = requisito;
        this.cod_setor = cod_setor;
    }

    // GETTERS 
    public String getNome() {
        return nome;
    }
    public String getSalarioBase() {
        return salarioBase;
    }
    public String getHierarquia() {
        return hierarquia;
    }
    public String getRequisitos(){
        return requisitos;
    }
    public String getCod_setor(){
        return cod_setor;
    }

    //SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSalarioBase(String salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public void setCod_setor(String cod_setor) {
        this.cod_setor = cod_setor;
    }
    
}
