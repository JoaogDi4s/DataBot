public class Endereco {
    private String numero;
    private String bairro;
    private String cep;
    private String rua;
    private String complemento;
   
    // CONSTRUTOR
    public Endereco(String numero, String bairro, String cep, String rua, String complemento) {
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.rua = rua;
        this.complemento = complemento;
    }

    // GETTERS
    public String getNumero() {
        return numero;
    }
    public String getBairro() {
        return bairro;
    }
    public String getCep() {
        return cep;
    }
    public String getRua() {
        return rua;
    }
    public String getComplemento() {
        return complemento;
    }   
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }       
}

