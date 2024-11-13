public class Main {
    public static void main(String[] args) {
        SetorDAO setorDAO = new SetorDAO();
        CargoDAO cargoDAO = new CargoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();

        Setor setorBuscado = setorDAO.buscarPorNome("Desenvolvimento");
        if (setorBuscado != null) {
            System.out.println("Setor encontrado: " + setorBuscado.getNome() + ", " + setorBuscado.getDescricao());
        } else {
            System.out.println("Setor não encontrado.");
        }

        Cargo cargoBuscado = cargoDAO.buscarPorNome("Analista Financeiro");
        if (cargoBuscado != null) {
            System.out.println("Cargo encontrado: " + cargoBuscado.getNome() + ", " + cargoBuscado.getHierarquia());
        } else {
            System.out.println("Cargo não encontrado.");
        }

        Funcionario funcionarioBuscado = funcionarioDAO.buscarPorCpfRg("456.789.012-33");
        if (funcionarioBuscado != null) {
            System.out.println("Funcionario encontrado: " + funcionarioBuscado.getNome() + ", " + funcionarioBuscado.getEmail());
        } else {
            System.out.println("Funcionario não encontrado.");
        }

        Endereco enderecoBuscado = enderecoDAO.buscarPorCep("12345-678");
        if (enderecoBuscado != null) {
            System.out.println("Endereco encontrado: " + enderecoBuscado.getCep() + ", " + enderecoBuscado.getNumero());
        } else {
            System.out.println("Endereco não encontrado.");
        }

        Setor novoSetor = new Setor("teste", "teste");
        setorDAO.salvar(novoSetor);
        System.out.println("Setor salvo com sucesso!");
    }
}
