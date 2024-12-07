import java.util.*;

public class FuncionarioCRUD implements CRUD {
    Scanner scan = new Scanner(System.in);
    Mensagens msg = new Mensagens();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    CargoDAO cargoDAO = new CargoDAO();
    EnderecoDAO enderecoDAO = new EnderecoDAO();

    public void criar() {
        boolean continuarFuncionario = true;
        while (continuarFuncionario) {
            String funcionarioNome;////////////////////////////////////////////////////////////////////////////////
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

            String funcionarioGenero;
            do {
                System.out.println("Qual o gênero do funcionário?");
                funcionarioGenero = scan.nextLine();
                if (!funcionarioDAO.validarGenero(funcionarioGenero)) {
                    System.out.println("Genero inválido, tente 'M', 'F' ou 'N'");
                }
            } while (!funcionarioDAO.validarGenero(funcionarioGenero));

            String funcionarioNascimento;
            do {
                System.out.println(
                        "Qual a data de nascimento do funcionário? (Formato: yyyy-MM-dd)");
                funcionarioNascimento = scan.nextLine();
                if (!funcionarioDAO.validarData(funcionarioNascimento)) { // Supondo que há um
                                                                          // método
                    // validarData
                    System.out.println("Data inválida. Use o formato YYYY-MM-DD.");
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
                        "Qual a data de admissão do funcionário? (Formato: YYYY-MM-DD)");
                funcionarioDataAdmissao = scan.nextLine();
                if (!funcionarioDAO.validarData(funcionarioDataAdmissao)) {
                    System.out.println("Data inválida. Use o formato YYYY-MM-DD.");
                }
            } while (!funcionarioDAO.validarData(funcionarioDataAdmissao));

            System.out.println("Qual o projeto do funcionário? [nullable]");
            String funcionarioProjeto = scan.nextLine();
            if (funcionarioProjeto.isBlank()) { // Verifica se a entrada está vazia ou contém
                                                // apenas espaços
                funcionarioProjeto = null; // Define como null
            }

            System.out.println(
                    "Qual o código do cargo do funcionário? [Caso necessário, crie um cargo antes]");
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
            System.out.println(msg.operacaoExecutada());

            System.out.println("Deseja criar outro funcionário? (Sim/não)");
            scan.nextLine();
            String resposta = InputUtils.getInput(scan);

            if (!resposta.equals("sim")) {
                continuarFuncionario = false;
                break;
            }
        }
    }

    public void consultar() {
        boolean continuarFuncionario = true;
        System.out.println(msg.consultaFunc1());
        String resposta1 = InputUtils.getInput(scan);
        Funcionario funcionarioBuscado = funcionarioDAO.buscarPorNome(resposta1);

        if (funcionarioBuscado != null) {
            System.out.println(msg.separadorEmcima());
            System.out.println(msg.funcEncontrado());
            System.out.println(msg.separadorEmbaixo());
            while (continuarFuncionario) {
                System.out.println(msg.opEscolhidas());
                String escolhaFuncionario = InputUtils.getInput(scan);

                if (escolhaFuncionario.equals("sair")) { // Opção de ver todos
                    continuarFuncionario = false;
                    break;
                }

                String[] escolhas = escolhaFuncionario.split(","); // SEPARAR OPÇÕES POR VÍRGULA

                for (String op : escolhas) {
                    System.out.println(msg.separadorEmcima());
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
                                    System.out.println(msg.cargoNaoEncontrado());
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
                                                "Endereço Completo: "
                                                        + enderecoBuscado.getRua() + ", " +
                                                        enderecoBuscado.getNumero() + ", "
                                                        + enderecoBuscado.getBairro() + ", " +
                                                        enderecoBuscado.getComplemento() + ", "
                                                        + enderecoBuscado.getCep());
                                    } else if (resposta.equalsIgnoreCase("separado")) {
                                        boolean continuarEndereco = true;
                                        while (continuarEndereco) {
                                            System.out.println(msg.opEscolhidas());
                                            String escolhaEndereco = InputUtils.getInput(scan);

                                            if (escolhaEndereco.equals("sair")) {
                                                continuarEndereco = false;
                                                break;
                                            }

                                            String[] escolhasEndereco = escolhaEndereco
                                                    .split(",");
                                            for (String opEndereco : escolhasEndereco) {
                                                switch (opEndereco.trim()) {
                                                    case "rua":
                                                        System.out.println("Rua: "
                                                                + enderecoBuscado.getRua());
                                                        break;
                                                    case "numero":
                                                        System.out.println("Número: "
                                                                + enderecoBuscado.getNumero());
                                                        break;
                                                    case "bairro":
                                                        System.out.println("Bairro: "
                                                                + enderecoBuscado.getBairro());
                                                        break;
                                                    case "complemento":
                                                        System.out.println("Complemento: "
                                                                + enderecoBuscado
                                                                        .getComplemento());
                                                        break;
                                                    case "cep":
                                                        System.out.println("CEP: "
                                                                + enderecoBuscado.getCep());
                                                        break;
                                                    default:
                                                        System.out.println(msg.opInvalida());
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
                            System.out.println(msg.opInvalida());
                    }
                    System.out.println(msg.separadorEmbaixo());
                }

                System.out.println(msg.consultaSairCaso());
                String respostaSair = InputUtils.getInput(scan);
                if (respostaSair.equalsIgnoreCase("nao")) {
                    continuarFuncionario = false;
                }
            }
        } else {
            System.out.println(msg.funcNaoEncontrado());
        }
    }

    public void atualizar() {
        System.out.println(msg.funcionarioCpf() + "que gostaria de modificar?");
        String escolhaCPF = InputUtils.getInput(scan);
        Funcionario funcionarioBuscado = funcionarioDAO.buscarPorCpf(escolhaCPF);

        if (funcionarioBuscado != null) {
            System.out.println(msg.separadorEmcima());
            System.out.println(msg.opUpdateFunc());
            System.out.println(msg.separadorEmbaixo());
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

                        funcionarioBuscado.setRg(novoRG);
                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                        break;
                    case "cpf":
                        System.out.println("Qual será o novo CPF?");
                        String novoCPF = scan.nextLine();

                        funcionarioBuscado.setCpf(novoCPF);
                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                        break;
                    case "email":
                        System.out.println("Qual será o novo email?");
                        String novoEmail = scan.nextLine();

                        funcionarioBuscado.setEmail(novoEmail);
                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
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

                        funcionarioBuscado.setNascimento(novoNascimento);
                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
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

                        funcionarioBuscado.setData_admissao(novaDtAdmissao);
                        funcionarioDAO.atualizar(escolhaCPF, funcionarioBuscado);
                        break;
                    case "projetos":
                        System.out.println("Qual será o novo projeto?");
                        String novosProjetos = scan.nextLine();

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
                        System.out.println(msg.opInvalida());
                        break;
                }
            }
        } else {
            System.out.println(msg.funcNaoEncontrado());
        }
    }

    public void deletar() {
        boolean funcionarioEncontrado = false;
        while (!funcionarioEncontrado) {
            System.out.println(msg.funcionarioCpf() + " que você gostaria de deletar?");
            String funcionarioCpf = InputUtils.getInput(scan);
            Funcionario funcionarioBuscado = funcionarioDAO.buscarPorCpf(funcionarioCpf);
            if (funcionarioBuscado != null) {
                System.out.println(msg.separadorEmcima());
                System.out.println("Nome: " + funcionarioBuscado.getNome());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.deletarCampo());
                String confirmacao = InputUtils.getInput(scan).toLowerCase();
                if (confirmacao.equals("sim")) {
                    funcionarioDAO.deletar(funcionarioCpf);
                    System.out.println(msg.operacaoExecutada());
                    funcionarioEncontrado = true; // Encerra o loop
                } else if (confirmacao.equals("nao")) {
                    System.out.println(msg.operacaoCancelada());
                    funcionarioEncontrado = true; // Encerra o loop
                } else {
                    System.out.println(msg.respInvalida());
                }
            } else {
                System.out.println(
                        msg.funcNaoEncontrado() + " Deseja tentar novamente? (sim/nao)"); // Adicionar
                                                                                          // nos
                                                                                          // outros
                                                                                          // tambem
                String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
                if (!tentarNovamente.equals("sim")) {
                    System.out.println(msg.operacaoEncerrada());
                    break; // Sai do loop
                }
            }
        }
    }
}
