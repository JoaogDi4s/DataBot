public class Main {
    public static void main(String[] args) {
        SetorDAO setorDAO = new SetorDAO();
        CargoDAO cargoDAO = new CargoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
/* 
        // SETOR
            // INSERT
                Setor novoSetor= new Setor("SetorTeste", "TestandoSetor");
                setorDAO.salvar(novoSetor);
                System.out.println("Setor salvo com sucesso!");
            // UPDATE - PELO NOME DO SETOR
                Setor setorAtualizado = new Setor("SetorTeste2", "TestandoSetor");
                setorDAO.atualizar("SetorTeste", setorAtualizado);
                System.out.println("Setor atualizado com sucesso!");
            // DELETE - PELO NOME DO SETOR
                setorDAO.deletar("SetorTeste2");
            // SELECT - PELO NOME DO SETOR
                Setor setorBuscado = setorDAO.buscarPorNome("SetorTeste");
                if (setorBuscado != null) {
                    System.out.println("Setor encontrado: " + setorBuscado.getDescricao());
                } else {
                    System.out.println("Setor não encontrado.");
                }

        // CARGO
            // INSERT
                Cargo novoCargo= new Cargo("Teste", "1000", "pika", "nada", "1");
                cargoDAO.salvar(novoCargo);
                System.out.println("Cargo salvo com sucesso!");
            // UPDATE - PELO NOME DO CARGO
                Cargo cargoAtualizado = new Cargo("Teste", "3000", "pika", "nada", "1");
                cargoDAO.atualizar(cargoAtualizado);
                System.out.println("Cargo atualizado com sucesso!");
            // DELETE - PELO NOME DO CARGO
                cargoDAO.deletar("Teste");
            // SELECT - PELO NOME DO CARGO
                Cargo cargoBuscado = cargoDAO.buscarPorNome("Teste");
                if (cargoBuscado != null) {
                    System.out.println("Aluno encontrado: " + cargoBuscado.getSalarioBase() + ", " + cargoBuscado.getRequisitos() + ", " + cargoBuscado.getNome() + ", " + cargoBuscado.getHierarquia());
                } else {
                    System.out.println("Cargo não encontrado.");
                }
      
        // FUNCIONARIO
            // INSERT
                Funcionario novoFuncionario= new Funcionario("João Silva", "A", "40h", "2024-01-15", "1990-05-20","48553951880" , "987564321", "joaosilva@gmail.com", "masculino", "123456789", "1", "2");
                funcionarioDAO.salvar(novoFuncionario);
                System.out.println("Funcionario salvo com sucesso!");
            // UPDATE - PELO CPF DO FUNCIONARIO
                Funcionario funcionarioAtualizado = new Funcionario("João Silva", "B", "40h", "2024-01-15", "1990-05-20","48553951880" , "987564321", "joaosilva@gmail.com", "masculino", "123456789", "1", "2");
                funcionarioDAO.atualizar(funcionarioAtualizado);
                System.out.println("Cargo atualizado com sucesso!");
            // DELETE - PELO CPF DO FUNCIONARIO
            funcionarioDAO.deletar("48553951880");
            // SELECT - PELO CPF DO FUNCIONARIO
            Funcionario funcionarioBuscado = funcionarioDAO.buscarPorCpf("48553951880");
            if (funcionarioBuscado != null) {
                System.out.println("Aluno encontrado: " + funcionarioBuscado.getTelefone() + ", " + funcionarioBuscado.getGenero() + ", " + funcionarioBuscado.getNome());
            } else {
                System.out.println("Cargo não encontrado.");
            }
 */ 

        // ENDERECO
            // INSERT
                Endereco novoEndereco = new Endereco("900", "Vila Gabriel", "18090602", "Joaão Riberiro", "Bloco 5 apto 12");
                enderecoDAO.salvar(novoEndereco);
                System.out.println("Endereco salvo com sucesso!");
            // UPDATE - PELO CEP DO ENDERECO
            Endereco enderecoAtualizado = new Endereco("900", "Vila Gabriel", "18090602", "João Ribeiro", "Bloco 5 apto 12");
                enderecoDAO.atualizar(enderecoAtualizado);
                System.out.println("Cargo atualizado com sucesso!");
            // DELETE - PELO CEP DO ENDERECO
            //enderecoDAO.deletar("18090602");
            // SELECT - PELO CEP DO ENDERECO
            Endereco enderecoBuscado = enderecoDAO.buscarPorCep("18090602");
            if (enderecoBuscado != null) {
                System.out.println("Endereco encontrado: " + enderecoBuscado.getBairro() + ", " + enderecoBuscado.getNumero() + ", " + enderecoBuscado.getRua());
            } else {
                System.out.println("Cargo não encontrado.");
            }
    }
}
