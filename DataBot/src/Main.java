import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SetorDAO setorDAO = new SetorDAO();
        CargoDAO cargoDAO = new CargoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Introducao intro = new Introducao();
        Mensagens msg = new Mensagens();
        Scanner scan = new Scanner(System.in);

        intro.introducao();
        boolean escolha = true;
        while (escolha) {
            System.out.println(msg.perguntaEscolha());
            String respostaEscolha = InputUtils.getInput(scan);

            switch (respostaEscolha) {
                case "consultar":
                    boolean continuar = true;
                    while (continuar) {
                        System.out.println(msg.pergunta0());
                        String resposta0 = InputUtils.getInput(scan);

                        switch (resposta0) {
                            case "funcionario":
                                boolean continuarFuncionario = true;
                                System.out.println(msg.pergunta1());
                                String resposta1 = InputUtils.getInput(scan);
                                Funcionario funcionarioBuscado = funcionarioDAO.buscarPorNome(resposta1);

                                if (funcionarioBuscado != null) {
                                    System.out.println("\n--------------------");
                                    System.out.println("Funcionário encontrado! O que você deseja visualizar?");
                                    System.out.println("1. Nome");
                                    System.out.println("2. Nascimento");
                                    System.out.println("3. CPF");
                                    System.out.println("4. RG");
                                    System.out.println("5. Gênero");
                                    System.out.println("6. Projetos");
                                    System.out.println("7. Telefone");
                                    System.out.println("8. Email");
                                    System.out.println("9. Endereço");
                                    System.out.println("--------------------\n");

                                    while (continuarFuncionario) {
                                        System.out.println(
                                                "Digite as opções que deseja ver (separadas por vírgula, ex: 1, 3, 7) ou 'sair' para encerrar.");
                                        String escolhaFuncionario = InputUtils.getInput(scan);

                                        if (escolhaFuncionario.equals("sair")) {
                                            continuarFuncionario = false;
                                            break;
                                        }
                                        String[] escolhas = escolhaFuncionario.split(","); // SEPARAR OPÇÕES POR VÍRGULA

                                        for (String op : escolhas) {
                                            System.out.println("\n--------------------");
                                            switch (op.trim()) {
                                                case "nome":
                                                    System.out.println("Nome: " + funcionarioBuscado.getNome());
                                                    break;
                                                case "nascimento":
                                                    System.out.println(
                                                            "Nascimento: " + funcionarioBuscado.getNascimento());
                                                    break;
                                                case "cpf":
                                                    System.out.println("CPF: " + funcionarioBuscado.getCpf());
                                                    break;
                                                case "rg":
                                                    System.out.println("RG: " + funcionarioBuscado.getRg());
                                                    break;
                                                case "genero":
                                                    System.out.println("Gênero: " + funcionarioBuscado.getGenero());
                                                    break;
                                                case "projetos":
                                                    System.out.println("Projetos: " + funcionarioBuscado.getProjetos());
                                                    break;
                                                case "telefone":
                                                    System.out.println("Telefone: " + funcionarioBuscado.getTelefone());
                                                    break;
                                                case "email":
                                                    System.out.println("Email: " + funcionarioBuscado.getEmail());
                                                    break;
                                                case "cargo":
                                                    if (funcionarioBuscado.getCod_cargo() != null) {
                                                        Cargo cargoBuscado = cargoDAO
                                                                .buscarPorCodigo(funcionarioBuscado.getCod_cargo());
                                                        if (cargoBuscado != null) {
                                                            System.out.println("Cargo: " + cargoBuscado.getNome());
                                                        } else {
                                                            System.out.println(
                                                                    "Cargo não encontrado para este funcionário.");
                                                        }
                                                    } else {
                                                        System.out.println(
                                                                "Código de cargo não associado ao funcionário.");
                                                    }
                                                    break;

                                                case "endereco":
                                                    System.out.println(
                                                            "Deseja o endereço completo ou informações separadas? (Digite 'completo' ou 'separado')");
                                                    String resposta = InputUtils.getInput(scan);

                                                    if (funcionarioBuscado.getCod_endereco() != null) {
                                                        Endereco enderecoBuscado = enderecoDAO
                                                                .buscarPorCodigo(funcionarioBuscado.getCod_endereco());

                                                        if (enderecoBuscado != null) {
                                                            if (resposta.equalsIgnoreCase("completo")) {
                                                                System.out.println(
                                                                        "Endereço Completo: " + enderecoBuscado.getRua()
                                                                                + ", " +
                                                                                enderecoBuscado.getNumero() + ", "
                                                                                + enderecoBuscado.getBairro() + ", " +
                                                                                enderecoBuscado.getComplemento() + ", "
                                                                                + enderecoBuscado.getCep());
                                                            } else if (resposta.equalsIgnoreCase("separado")) {
                                                                boolean continuarEndereco = true;
                                                                while (continuarEndereco) {
                                                                    System.out.println(
                                                                            "\nDigite as opções de endereço que deseja ver (ex: rua, numero, bairro) ou 'sair' para encerrar.");
                                                                    String escolhaEndereco = InputUtils.getInput(scan);

                                                                    if (escolhaEndereco.equals("sair")) {
                                                                        continuarEndereco = false;
                                                                        break;
                                                                    }

                                                                    String[] escolhasEndereco = escolhaEndereco
                                                                            .split(","); // SEPARAR
                                                                    for (String opEndereco : escolhasEndereco) {
                                                                        switch (opEndereco.trim()) {
                                                                            case "rua":
                                                                                System.out.println(
                                                                                        "Rua: " + enderecoBuscado
                                                                                                .getRua());
                                                                                break;
                                                                            case "numero":
                                                                                System.out.println(
                                                                                        "Número: " + enderecoBuscado
                                                                                                .getNumero());
                                                                                break;
                                                                            case "bairro":
                                                                                System.out.println(
                                                                                        "Bairro: " + enderecoBuscado
                                                                                                .getBairro());
                                                                                break;
                                                                            case "complemento":
                                                                                System.out.println("Complemento: "
                                                                                        + enderecoBuscado
                                                                                                .getComplemento());
                                                                                break;
                                                                            case "cep":
                                                                                System.out.println(
                                                                                        "CEP: " + enderecoBuscado
                                                                                                .getCep());
                                                                                break;
                                                                            default:
                                                                                System.out.println("Opção inválida: "
                                                                                        + opEndereco);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                System.out.println(
                                                                        "Opção inválida. Digite 'completo' ou 'separado'.");
                                                            }
                                                        } else {
                                                            System.out.println(
                                                                    "Endereço não encontrado para este funcionário.");
                                                        }
                                                    } else {
                                                        System.out.println(
                                                                "Código de endereço não associado ao funcionário.");
                                                    }
                                                    break;

                                                default:
                                                    System.out.println("Opção inválida: " + op);
                                            }
                                            System.out.println("--------------------\n");
                                        }

                                        System.out.println(msg.peguntaSairCaso());
                                        String respostaSair = InputUtils.getInput(scan);
                                        if (respostaSair.equalsIgnoreCase("nao")) {
                                            continuarFuncionario = false;
                                        }
                                    }
                                } else {
                                    System.out.println("Funcionário não encontrado.");
                                }
                                break;

                            case "setor":
                                boolean continuarSetor = true;
                                System.out.println(msg.pergunta2());
                                String resposta2 = InputUtils.getInput(scan);
                                Setor setorBuscasdo = setorDAO.buscarPorNome(resposta2);

                                if (setorBuscasdo != null) {
                                    System.out.println("\n--------------------");
                                    System.out.println("Cargo encontrado! O que você deseja visualizar?");
                                    System.out.println("1. Nome");
                                    System.out.println("2. Descricao");
                                    System.out.println("--------------------\n");

                                    while (continuarSetor) {
                                        System.out.println(
                                                "Digite as opções que deseja ver (separadas por vírgula, ex: 1, 3, 7) ou 'sair' para encerrar.");
                                        String escolhaSetor = InputUtils.getInput(scan);

                                        if (escolhaSetor.equals("sair")) {
                                            continuarSetor = false;
                                            break;
                                        }

                                        String[] escolhas = escolhaSetor.split(",");

                                        for (String op : escolhas) {
                                            System.out.println("\n--------------------");
                                            switch (op.trim()) {
                                                case "nome":
                                                    System.out.println("Nome: " + setorBuscasdo.getNome());
                                                    break;
                                                case "descricao":
                                                    System.out.println("Descrição: " + setorBuscasdo.getDescricao());
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida: " + op);
                                            }
                                            System.out.println("--------------------\n");
                                        }

                                        System.out.println(msg.peguntaSairCaso());
                                        String respostaSair = InputUtils.getInput(scan);
                                        if (respostaSair.equalsIgnoreCase("nao")) {
                                            continuarSetor = false;
                                        }
                                    }
                                } else {
                                    System.out.println("Setor não encontrado.");
                                }
                                break;

                            case "cargo":
                                boolean continuarCargo = true;
                                System.out.println(msg.pergunta3());
                                String resposta3 = InputUtils.getInput(scan);
                                Cargo cargoBuscado = cargoDAO.buscarPorNome(resposta3);

                                if (cargoBuscado != null) {
                                    System.out.println("\n--------------------");
                                    System.out.println("Cargo encontrado! O que você deseja visualizar?");
                                    System.out.println("1. Nome");
                                    System.out.println("2. Salário Base");
                                    System.out.println("1. Hierarquia");
                                    System.out.println("2. Requisitos");
                                    System.out.println("2. Setor");
                                    System.out.println("--------------------\n");

                                    while (continuarCargo) {
                                        System.out.println(
                                                "Digite as opções que deseja ver (separadas por vírgula, ex: 1, 3, 7) ou 'sair' para encerrar.");
                                        String escolhaCargo = InputUtils.getInput(scan);

                                        if (escolhaCargo.equals("sair")) {
                                            continuarCargo = false;
                                            break;
                                        }

                                        String[] escolhas = escolhaCargo.split(","); // SEPARAR OPÇÕES POR VÍRGULA

                                        for (String op : escolhas) {
                                            System.out.println("\n--------------------");
                                            switch (op.trim()) {
                                                case "nome":
                                                    System.out.println("Nome: " + cargoBuscado.getNome());
                                                    break;
                                                case "salario base":
                                                    System.out
                                                            .println("Salário Base: " + cargoBuscado.getSalarioBase());
                                                    break;
                                                case "hierarquia":
                                                    System.out.println("Hierarquia: " + cargoBuscado.getHierarquia());
                                                    break;
                                                case "requisitos":
                                                    System.out.println("Requisitos: " + cargoBuscado.getRequisitos());
                                                    break;
                                                case "setor":
                                                    if (cargoBuscado.getCod_setor() != null) {
                                                        Setor setorBuscado = setorDAO
                                                                .buscarPorCodigo(cargoBuscado.getCod_setor());
                                                        if (setorBuscado != null) {
                                                            System.out.println("setor: " + setorBuscado.getNome());
                                                        } else {
                                                            System.out.println(
                                                                    "Cargo não encontrado para este funcionário.");
                                                        }
                                                    } else {
                                                        System.out.println(
                                                                "Código de cargo não associado ao funcionário.");
                                                    }
                                                    break;
                                                default:
                                                    System.out.println("Opção inválida: " + op);
                                            }
                                            System.out.println("--------------------\n"); // Fechamento do bloco de
                                                                                          // informações
                                        }

                                        System.out.println(msg.peguntaSairCaso());
                                        String respostaSair = InputUtils.getInput(scan);
                                        if (respostaSair.equalsIgnoreCase("nao")) {
                                            continuarCargo = false;
                                        }
                                    }
                                } else {
                                    System.out.println("Cargo não encontrado.");
                                }
                                break;

                            case "sair":
                                continuar = false;
                                break;

                            default:
                                System.out.println("Opção inválida, tente novamente.");
                        }
                    }
                    break;
                case "criar":
                    boolean escolhaCriar = true;
                    System.out.println("O que você gostaria de adicionar ao banco de dados?");
                    while (escolhaCriar) {
                        String respostaCriar = InputUtils.getInput(scan);
                        switch (respostaCriar) {
                            case "funcionario":
                                boolean continuarFuncionario = true;
                                while (continuarFuncionario) {
                                    String funcionarioNome;
                                    do {
                                        System.out.println("Qual o nome do funcionário?");
                                        funcionarioNome = scan.nextLine();
                                        if (funcionarioNome.isEmpty()) {
                                            System.out.println(
                                                    "O nome do funcionário não pode estar vazio. Tente novamente.");
                                        }
                                    } while (funcionarioNome.isEmpty());

                                    String funcionarioRG;
                                    do {
                                        System.out.println("Qual o RG do funcionário?");
                                        funcionarioRG = scan.nextLine();
                                        if (funcionarioRG != null && !funcionarioDAO.validarRg(funcionarioRG)) {
                                            System.out.println("RG inválido. Certifique-se de que está correto.");
                                        }
                                    } while (funcionarioRG != null && !funcionarioDAO.validarRg(funcionarioRG));

                                    String funcionarioCPF;
                                    do {
                                        System.out.println("Qual o CPF do funcionário?");
                                        funcionarioCPF = scan.nextLine();
                                        if (funcionarioCPF != null && !funcionarioDAO.validarCpf(funcionarioCPF)) {
                                            System.out.println(
                                                    "CPF inválido. Certifique-se de que contém 11 dígitos válidos.");
                                        }
                                    } while (funcionarioCPF != null && !funcionarioDAO.validarCpf(funcionarioCPF));

                                    String funcionarioEmail;
                                    do {
                                        System.out.println("Qual o email do funcionário?");
                                        funcionarioEmail = scan.nextLine();
                                        if (!funcionarioDAO.validarEmail(funcionarioEmail)) { // Supondo que há um
                                                                                              // método validarEmail
                                            System.out.println("Email inválido. Tente novamente.");
                                        }
                                    } while (!funcionarioDAO.validarEmail(funcionarioEmail));

                                    System.out.println("Qual o gênero do funcionário?");
                                    String funcionarioGenero = scan.nextLine();

                                    String funcionarioNascimento;
                                    do {
                                        System.out.println(
                                                "Qual a data de nascimento do funcionário? (Formato: yyyy-MM-dd)");
                                        funcionarioNascimento = scan.nextLine();
                                        if (!funcionarioDAO.validarData(funcionarioNascimento)) { // Supondo que há um
                                                                                                  // método
                                            // validarData
                                            System.out.println("Data inválida. Use o formato yyyy-MM-dd.");
                                        }
                                    } while (!funcionarioDAO.validarData(funcionarioNascimento));

                                    System.out.println("Qual o telefone do funcionário? [nullable]");
                                    String funcionarioTelefone = scan.nextLine();
                                    if (funcionarioTelefone.isBlank()) { // Verifica se a entrada está vazia ou contém
                                                                         // apenas espaços
                                        funcionarioTelefone = null; // Define como null
                                    }

                                    System.out.println(
                                            "Qual o código do endereço do funcionário? [Você precisa criar um endereço antes]");
                                    Integer funcionarioCodEndereco = scan.nextInt();
                                    scan.nextLine();

                                    System.out.println("Qual a carga horária do funcionário?");
                                    String funcionarioCargaHoraria = scan.nextLine();

                                    String funcionarioDataAdmissao;
                                    do {
                                        System.out.println(
                                                "Qual a data de admissão do funcionário? (Formato: yyyy-MM-dd)");
                                        funcionarioDataAdmissao = scan.nextLine();
                                        if (!funcionarioDAO.validarData(funcionarioDataAdmissao)) {
                                            System.out.println("Data inválida. Use o formato yyyy-MM-dd.");
                                        }
                                    } while (!funcionarioDAO.validarData(funcionarioDataAdmissao));

                                    System.out.println("Qual o projeto do funcionário? [nullable]");
                                    String funcionarioProjeto = scan.nextLine();
                                    if (funcionarioProjeto.isBlank()) { // Verifica se a entrada está vazia ou contém
                                                                        // apenas espaços
                                        funcionarioProjeto = null; // Define como null
                                    }

                                    System.out.println(
                                            "Qual o código do cargo do funcionário? [Você precisa criar um cargo antes]");
                                    Integer funcionarioCodCargo = scan.nextInt();
                                    scan.nextLine();

                                    Funcionario novoFuncionario = new Funcionario(
                                            funcionarioNome,
                                            funcionarioProjeto,
                                            funcionarioCargaHoraria,
                                            funcionarioDataAdmissao,
                                            funcionarioNascimento,
                                            funcionarioCPF,
                                            funcionarioTelefone,
                                            funcionarioEmail,
                                            funcionarioGenero,
                                            funcionarioRG,
                                            funcionarioCodEndereco,
                                            funcionarioCodCargo);

                                    funcionarioDAO.salvar(novoFuncionario);
                                    System.out.println("Funcionário criado com sucesso!");

                                    System.out.println("Deseja criar outro funcionário?");
                                    String resposta = InputUtils.getInput(scan);

                                    if (!resposta.equals("sim")) {
                                        continuarFuncionario = false;
                                    }
                                }
                                break;

                            case "setor":
                                boolean continuarSetor = true;

                                while (continuarSetor) {
                                    System.out.println("Qual o nome do setor?");
                                    String setorNome = scan.nextLine();

                                    System.out.println("Qual a descrição do setor?");
                                    String setorDescricao = scan.nextLine();

                                    Setor novoSetor = new Setor(setorNome, setorDescricao);
                                    setorDAO.salvar(novoSetor);
                                    System.out.println("Setor criado com sucesso!");

                                    System.out.println("Deseja criar outro setor?");
                                    String resposta = InputUtils.getInput(scan);

                                    if (!resposta.equals("sim")) {
                                        continuarSetor = false;
                                    }
                                }
                                break;
                            case "cargo":
                                boolean continuarCargo = true;

                                while (continuarCargo) {
                                    System.out.println("Qual o nome do cargo?");
                                    String cargoNome = scan.nextLine();
                                    System.out.println("Qual o salário base do cargo?");
                                    String cargoSalarioBase = scan.nextLine();
                                    System.out.println("Quais os requisitos do cargo?");
                                    String cargoRequisitos = scan.nextLine();
                                    System.out.println("Qual a hierarquia do cargo?");
                                    String cargoHierarquia = scan.nextLine();
                                    System.out.println(
                                            "Qual o código do setor do cargo? [Você precisa criar um setor antes]");
                                    String cargoCodSetor = scan.nextLine();

                                    Cargo novoCargo = new Cargo(cargoNome, cargoSalarioBase, cargoHierarquia,
                                            cargoRequisitos, cargoCodSetor);
                                    cargoDAO.salvar(novoCargo);
                                    System.out.println("Setor Criado com sucesso");

                                    System.out.println("Deseja criar outro setor?");
                                    String resposta = scan.nextLine();

                                    if (!resposta.equals("sair")) {
                                        continuarCargo = false;
                                    }
                                }

                                break;
                            case "endereco":
                                boolean continuarEndereco = true;

                                while (continuarEndereco) {
                                    System.out.println("Qual o a rua?");
                                    String enderecoRua = scan.nextLine();
                                    System.out.println("Qual número?");
                                    String enderecoNumero = scan.nextLine();
                                    System.out.println("Qual o bairro?");
                                    String enderecoBairro = scan.nextLine();
                                    System.out.println("QUal complemento? [nullable]");
                                    String enderecoComplemento = scan.nextLine();
                                    if (enderecoComplemento.isBlank()) { // Verifica se a entrada está vazia ou contém
                                                                         // apenas espaços
                                        enderecoComplemento = null; // Define como null
                                    }
                                    System.out.println("Qual o CEP?");
                                    String enderecoCEP = scan.nextLine();

                                    Endereco novEndereco = new Endereco(enderecoNumero, enderecoBairro, enderecoCEP,
                                            enderecoRua, enderecoComplemento);
                                    enderecoDAO.salvar(novEndereco);
                                    System.out.println("Endereço criado com sucesso");

                                    System.out.println("Deseja criar outro setor?");
                                    String resposta = InputUtils.getInput(scan);

                                    if (!resposta.equals("sair")) {
                                        continuarCargo = false;
                                    }
                                }

                                break;
                            case "sair":
                                escolhaCriar = false;
                                break;
                            default:
                                System.out.println("Opção inválida, tente novamente.");
                                break;
                        }
                    }
                    break;
                case "atualizar":
                    boolean escolhaAtualizar = true;
                    System.out.println("O que você gostaria de alterar?");
                    while (escolhaAtualizar) {
                        String respostaAtualizar = InputUtils.getInput(scan);
                        switch (respostaAtualizar) {
                            case "funcionario":
                                System.out.println("Qual o CPF do funcionário que gostaria de modificar?");
                                String escolhaCPF = InputUtils.getInput(scan);
                                Funcionario funcionarioBuscado = funcionarioDAO.buscarPorCpf(escolhaCPF);

                                if (funcionarioBuscado != null) {
                                    System.out.println("Qual dado gostaria de alterar?");
                                    boolean escolhaAtualizarFuncionario = true;
                                    while (escolhaAtualizarFuncionario) {
                                        String respostaAtualizarFuncionario = InputUtils.getInput(scan);
                                        switch (respostaAtualizarFuncionario) {
                                            case "nome":
                                                System.out.println("Qual será o novo nome?");
                                                String novoNome = scan.nextLine();

                                                funcionarioBuscado.setNome(novoNome);
                                                funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                break;
                                            case "rg":
                                                System.out.println("Qual será o novo RG?");
                                                String novoRG = scan.nextLine();

                                                // Depuração: Verificar o valor de novoRG
                                                if (novoRG != null && !novoRG.trim().isEmpty()) {
                                                    if (funcionarioDAO.validarRg(novoRG)) {
                                                        funcionarioBuscado.setRg(novoRG);
                                                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                    } else {
                                                        System.out.println("RG inválido!");
                                                    }
                                                } else {
                                                    System.out.println("RG não pode ser vazio!");
                                                }

                                                break;
                                            case "cpf":
                                                System.out.println("Qual será o novo CPF?");
                                                String novoCPF = scan.nextLine();
                                                if (novoCPF != null && !novoCPF.trim().isEmpty()) {
                                                    if (funcionarioDAO.validarCpf(novoCPF)) {
                                                        funcionarioBuscado.setCpf(novoCPF);
                                                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                    } else {
                                                        System.out.println("RG inválido!");
                                                    }
                                                } else {
                                                    System.out.println("CPF não pode ser vazio!");
                                                }
                                                break;
                                            case "email":
                                                System.out.println("Qual será o novo email?");
                                                String novoEmail = scan.nextLine();

                                                if (novoEmail != null && !novoEmail.trim().isEmpty()) {
                                                    if (funcionarioDAO.validarEmail(novoEmail)) {
                                                        funcionarioBuscado.setEmail(novoEmail);
                                                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                    } else {
                                                        System.out.println("RG inválido!");
                                                    }
                                                } else {
                                                    System.out.println("Email não pode ser vazio!");
                                                }
                                                break;
                                            case "genero":
                                                System.out.println("Qual será o novo gênero?");
                                                String novoGenero = scan.nextLine();

                                                funcionarioBuscado.setGenero(novoGenero);
                                                funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                break;
                                            case "nascimento":
                                                System.out.println("Qual será o novo nascimento?");
                                                String novoNascimento = scan.nextLine();

                                                if (novoNascimento != null && !novoNascimento.trim().isEmpty()) {
                                                    if (funcionarioDAO.validarData(novoNascimento)) {
                                                        funcionarioBuscado.setNascimento(novoNascimento);
                                                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                    } else {
                                                        System.out.println("Data inválida!");
                                                    }
                                                } else {
                                                    System.out.println("data não pode ser vazio!");
                                                }
                                                break;
                                            case "telefone":
                                                System.out.println("Qual será o novo telefone?");
                                                String novoTelefone = scan.nextLine();

                                                funcionarioBuscado.setTelefone(novoTelefone);
                                                funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                break;
                                            case "codigo endereco":
                                                System.out.println("Qual será o novo código de endereço?");
                                                Integer novoCodEndereco = scan.nextInt();

                                                funcionarioBuscado.setCod_endereco(novoCodEndereco);
                                                funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                break;
                                            case "carga horaria":
                                                System.out.println("Qual será o nova carga-horária?");
                                                String novaCargaHoraria = scan.nextLine();

                                                funcionarioBuscado.setCarga_horaria(novaCargaHoraria);
                                                funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                break;
                                            case "data admisao":
                                                System.out.println("Qual será o nova data de admissão?");
                                                String novaDtAdmissao = scan.nextLine();
                                                if (novaDtAdmissao != null && !novaDtAdmissao.trim().isEmpty()) {
                                                    if (funcionarioDAO.validarData(novaDtAdmissao)) {
                                                        funcionarioBuscado.setData_admissao(novaDtAdmissao);
                                                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                    } else {
                                                        System.out.println("Data inválida!");
                                                    }
                                                } else {
                                                    System.out.println("data não pode ser vazio!");
                                                }
                                                break;
                                            case "projetos":
                                                System.out.println("Qual será o novo projeto?");
                                                String novosProjetos = scan.nextLine();
                                                if (novosProjetos.isBlank()){
                                                    novosProjetos = null;
                                                }
                                                funcionarioBuscado.setProjetos(novosProjetos);
                                                funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                break;
                                            case "codigo cargo":
                                                System.out.println("Qual será o novo código do cargo?");
                                                Integer novoCodCargo = scan.nextInt();

                                                funcionarioBuscado.setCod_cargo(novoCodCargo);
                                                funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                                                break;
                                            case "sair":
                                                escolhaAtualizarFuncionario = false;
                                                break;
                                            default:
                                                System.out.println("Opção inválida, tente novamente.");
                                                break;
                                        }
                                    }
                                } else {
                                    System.out.println("Funcionário não encontrado.");
                                }
                                break;
                            case "setor":
                                System.out.println("Qual setor gostaria de alterar?");
                                String escolhaSetor = InputUtils.getInput(scan);
                                Setor setorBuscado = setorDAO.buscarPorNome(escolhaSetor);

                                if (setorBuscado != null) {
                                    System.out.println("Qual dado gostaria de alterar?");
                                    boolean escolhaAtualizarSetor = true;
                                    while (escolhaAtualizarSetor) {
                                        String respostaAtualizarSetor = InputUtils.getInput(scan);
                                        switch (respostaAtualizarSetor) {
                                            case "nome":
                                                System.out.println("Qual será o novo nome?");
                                                String novoNome = InputUtils.getInput(scan);

                                                setorBuscado.setNome(novoNome);
                                                setorDAO.atualizar(escolhaSetor, setorBuscado);

                                                System.out.println("Nome do setor atualizado com sucesso!");
                                                break;
                                            case "descricao":
                                                System.out.println("Qual será a nova descrição?");
                                                String novaDescricao = InputUtils.getInput(scan);

                                                setorBuscado.setDescricao(novaDescricao);
                                                setorDAO.atualizar(escolhaSetor, setorBuscado);

                                                System.out.println("Descrição do setor atualizada com sucesso!");
                                                break;
                                            case "sair":
                                                escolhaAtualizarSetor = false;
                                                break;
                                            default:
                                                System.out.println("Opção inválida, tente novamente.");
                                                break;
                                        }
                                    }
                                } else {
                                    System.out.println("Setor não encontrado");
                                }
                                break;
                            case "cargo":
                                System.out.println("Qual cargo gostaria de alterar?");
                                String escolhaCargo = InputUtils.getInput(scan);
                                Cargo cargoBucasdo = cargoDAO.buscarPorNome(escolhaCargo);

                                if (cargoBucasdo != null) {
                                    System.out.println("Qual dado gostaria de alterar?");
                                    boolean escolhaAtualizarCargo = true;
                                    while (escolhaAtualizarCargo) {
                                        String respostaAtualizarCargo = InputUtils.getInput(scan);
                                        switch (respostaAtualizarCargo) {
                                            case "nome":
                                                System.out.println("Qual será o novo nome?");
                                                String novoNome = InputUtils.getInput(scan);

                                                cargoBucasdo.setNome(novoNome);
                                                cargoDAO.atualizar(escolhaCargo, cargoBucasdo);

                                                break;
                                            case "salario base":
                                                System.out.println("Qual será o novo Salario Base?");
                                                String novoSalario = InputUtils.getInput(scan);

                                                cargoBucasdo.setSalarioBase(novoSalario);
                                                cargoDAO.atualizar(escolhaCargo, cargoBucasdo);

                                                break;
                                            case "requisitos":
                                                System.out.println("Quais serão os novos requisitos?");
                                                String novoRequisito = InputUtils.getInput(scan);

                                                cargoBucasdo.setRequisitos(novoRequisito);
                                                cargoDAO.atualizar(escolhaCargo, cargoBucasdo);
                                                break;
                                            case "hierarquia":
                                                System.out.println("Quais a nova hierarquia?");
                                                String novaHierarquia = InputUtils.getInput(scan);

                                                cargoBucasdo.setHierarquia(novaHierarquia);
                                                cargoDAO.atualizar(escolhaCargo, cargoBucasdo);
                                                break;
                                            case "codigo setor":
                                                System.out.println("Quais o codigo do novo setor?");
                                                String novoSetor = InputUtils.getInput(scan);

                                                cargoBucasdo.setCod_setor(novoSetor);
                                                cargoDAO.atualizar(escolhaCargo, cargoBucasdo);
                                                break;
                                            case "sair":
                                                escolhaAtualizarCargo = false;
                                                break;
                                            default:
                                                System.out.println("Opção inválida, tente novamente.");
                                                break;
                                        }
                                    }
                                } else {
                                    System.out.println("Cargo não encontrado");
                                }

                                break;
                            case "endereco":
                                System.out.println("Qual o código do endereço que gostaria de alterar?");
                                Integer escolhaEndereco = scan.nextInt();
                                Endereco enderecoBuscado = enderecoDAO.buscarPorCodigo(escolhaEndereco);

                                if (enderecoBuscado != null) {
                                    System.out.println("Qual dado gostaria de alterar?");
                                    boolean escolhaAtualizarEndereco = true;
                                    while (escolhaAtualizarEndereco) {
                                        String respostaAtualizarEndereco = InputUtils.getInput(scan);
                                        switch (respostaAtualizarEndereco) {
                                            case "rua":
                                                System.out.println("Qual será a nova rua?");
                                                String novaRua = InputUtils.getInput(scan);

                                                enderecoBuscado.setRua(novaRua);
                                                enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                                                break;
                                            case "numero":
                                                System.out.println("Qual será o novo número?");
                                                String novoNunero = InputUtils.getInput(scan);

                                                enderecoBuscado.setNumero(novoNunero);
                                                enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                                                break;
                                            case "bairro":
                                                System.out.println("Qual será o novo bairro?");
                                                String novoNumero = InputUtils.getInput(scan);

                                                enderecoBuscado.setNumero(novoNumero);
                                                enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                                                break;
                                            case "complemento":
                                                System.out.println("Qual será o novo complemento?");
                                                String novoComplemento = InputUtils.getInput(scan);
                                                if (novoComplemento.isBlank()){
                                                    novoComplemento = null;
                                                }
                                                enderecoBuscado.setComplemento(novoComplemento);
                                                enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                                                break;
                                            case "cep":
                                                System.out.println("Qual será o novo CEP?");
                                                String NovoCEP = InputUtils.getInput(scan);

                                                enderecoBuscado.setCep(NovoCEP);
                                                enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                                                break;
                                            case "sair":
                                                escolhaAtualizarEndereco = false;
                                                break;
                                            default:
                                                System.out.println("Opção inválida, tente novamente.");
                                                break;
                                        }
                                    }
                                } else {
                                    System.out.println("Endereço não encontrado");
                                }

                                break;
                            case "sair":
                                escolhaAtualizar = false;
                                break;
                            default:
                                System.out.println("Opção inválida, tente novamente.");
                                break;
                        }
                    }
                    break;
                case "deletar":
                    boolean escolhaDeletar = true;
                    System.out.println("O que você gostaria de deletar?");
                    while (escolhaDeletar) {
                        String respostaDeletar = InputUtils.getInput(scan);
                        switch (respostaDeletar) {
                            case "funcionario":
                                boolean funcionarioEncontrado = false;
                                while (!funcionarioEncontrado) {
                                    System.out.println("Qual o CPF do funcionário que você gostaria de deletar?");
                                    String funcionarioCpf = InputUtils.getInput(scan);

                                    Funcionario funcionarioBuscado = funcionarioDAO.buscarPorCpf(funcionarioCpf);
                                    if (funcionarioBuscado != null) {
                                        System.out.println("Funcionário encontrado: " + funcionarioBuscado.getNome());
                                        System.out.println("Deseja realmente deletar este funcionário? (sim/nao)");
                                        String confirmacao = InputUtils.getInput(scan).toLowerCase();
                                        if (confirmacao.equals("sim")) {
                                            funcionarioDAO.deletar(funcionarioCpf);
                                            System.out.println("Funcionário deletado com sucesso!");
                                            funcionarioEncontrado = true; // Encerra o loop
                                        } else if (confirmacao.equals("nao")) {
                                            System.out.println("Operação cancelada.");
                                            funcionarioEncontrado = true; // Encerra o loop
                                        } else {
                                            System.out.println(
                                                    "Resposta não compreendida. Por favor, responda com 'sim' ou 'nao'.");
                                        }
                                    } else {
                                        System.out.println(
                                                "Funcionário não encontrado. Deseja tentar novamente? (sim/nao)");
                                        String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
                                        if (!tentarNovamente.equals("sim")) {
                                            System.out.println("Encerrando operação.");
                                            break; // Sai do loop
                                        }
                                    }
                                }
                                break;

                            case "cargo":
                                boolean cargoEncontrado = false;
                                while (!cargoEncontrado) {
                                    System.out.println("Qual cargo gostaria de deletar?");
                                    String cargoNome = InputUtils.getInput(scan);

                                    Cargo cargoBuscado = cargoDAO.buscarPorNome(cargoNome);
                                    if (cargoBuscado != null) {
                                        System.out.println("Cargo encontrado: " + cargoBuscado.getNome());
                                        System.out.println("Deseja realmente deletar este cargo? (sim/nao)");
                                        String confirmacao = InputUtils.getInput(scan).toLowerCase();
                                        if (confirmacao.equals("sim")) {
                                            cargoDAO.deletar(cargoNome);
                                            System.out.println("Cargo deletado com sucesso!");
                                            cargoEncontrado = true; // Encerra o loop
                                        } else if (confirmacao.equals("nao")) {
                                            System.out.println("Operação cancelada.");
                                            cargoEncontrado = true; // Encerra o loop
                                        } else {
                                            System.out.println(
                                                    "Resposta não compreendida. Por favor, responda com 'sim' ou 'nao'.");
                                        }
                                    } else {
                                        System.out.println("Cargo não encontrado. Deseja tentar novamente? (sim/nao)");
                                        String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
                                        if (!tentarNovamente.equals("sim")) {
                                            System.out.println("Encerrando operação.");
                                            break; // Sai do loop
                                        }
                                    }
                                }
                                break;

                            case "setor":
                                boolean setorEncontrado = false;
                                while (!setorEncontrado) {
                                    System.out.println("Qual o nome do setor que você gostaria de deletar?");
                                    String setorNome = InputUtils.getInput(scan);

                                    Setor setorBuscado = setorDAO.buscarPorNome(setorNome);
                                    if (setorBuscado != null) {
                                        System.out.println("Setor encontrado: " + setorBuscado.getNome());
                                        System.out.println("Deseja realmente deletar este setor? (sim/nao)");
                                        String confirmacao = InputUtils.getInput(scan).toLowerCase();
                                        if (confirmacao.equals("sim")) {
                                            setorDAO.deletar(setorNome);
                                            System.out.println("Setor deletado com sucesso!");
                                            setorEncontrado = true; // Encerra o loop
                                        } else if (confirmacao.equals("nao")) {
                                            System.out.println("Operação cancelada.");
                                            setorEncontrado = true; // Encerra o loop
                                        } else {
                                            System.out.println(
                                                    "Resposta não compreendida. Por favor, responda com 'sim' ou 'nao'.");
                                        }
                                    } else {
                                        System.out.println("Setor não encontrado. Deseja tentar novamente? (sim/nao)");
                                        String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
                                        if (!tentarNovamente.equals("sim")) {
                                            System.out.println("Encerrando operação.");
                                            break; // Sai do loop
                                        }
                                    }
                                }
                                break;

                            case "endereco":
                                boolean enderecoEncontrado = false;
                                while (!enderecoEncontrado) {
                                    System.out.println(
                                            "Qual o CPF do funcionário cujo endereço você gostaria de deletar?");
                                    String funcionarioCpf = InputUtils.getInput(scan);

                                    Funcionario funcionarioBuscado = funcionarioDAO.buscarPorCpf(funcionarioCpf); // Busca
                                                                                                                  // o
                                                                                                                  // funcionário
                                                                                                                  // pelo
                                                                                                                  // CPF
                                    if (funcionarioBuscado != null) {
                                        System.out.println("Funcionário encontrado: " + funcionarioBuscado.getNome());
                                        System.out.println(
                                                "Deseja realmente deletar o endereço vinculado a este funcionário? (sim/nao)");
                                        String confirmacao = InputUtils.getInput(scan).toLowerCase();

                                        if (confirmacao.equals("sim")) {
                                            enderecoDAO.deletar(funcionarioCpf); // Método no DAO para deletar o
                                                                                 // endereço
                                            System.out.println("Endereço deletado com sucesso!");
                                            enderecoEncontrado = true; // Encerra o loop
                                        } else if (confirmacao.equals("nao")) {
                                            System.out.println("Operação cancelada.");
                                            enderecoEncontrado = true; // Encerra o loop
                                        } else {
                                            System.out.println(
                                                    "Resposta não compreendida. Por favor, responda com 'sim' ou 'nao'.");
                                        }
                                    } else {
                                        System.out.println(
                                                "Funcionário não encontrado. Deseja tentar novamente? (sim/nao)");
                                        String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
                                        if (!tentarNovamente.equals("sim")) {
                                            System.out.println("Encerrando operação.");
                                            break; // Sai do loop
                                        }
                                    }
                                }
                                break;

                            case "sair":
                                escolhaDeletar = false;
                                break;
                            default:
                                System.out.println("Opção inválida, tente novamente.");
                                break;
                        }
                    }

                    break;
                case "sair":
                    escolha = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
    }
}
