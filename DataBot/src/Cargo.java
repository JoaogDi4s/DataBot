public class Cargo{
    private String nome;
    private String salarioBase;
    private String hierarquia;
    private String Requisito;

    // CONSTRUTOR
    public Cargo(String nome, String salarioBase, String hierarquia, String Requisito) {
        this.nome = nome;
        this.salarioBase = salarioBase;
        this.hierarquia = hierarquia;
        this.Requisito = Requisito;
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
    public String getRequisito(){
        return Requisito;
    }
}
