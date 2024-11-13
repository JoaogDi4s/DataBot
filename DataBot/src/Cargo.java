public class Cargo{
    private String nome;
    private String salarioBase;
    private String hierarquia;
    private String requisitos;

    // CONSTRUTOR
    public Cargo(String nome, String salarioBase, String hierarquia, String requisito) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.hierarquia = hierarquia;
        this.requisitos = requisito;
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
}
