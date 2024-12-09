import java.util.Scanner;

public class CargoCRUD implements CRUD {
    Scanner scan = new Scanner(System.in);
    Mensagens msg = new Mensagens();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    CargoDAO cargoDAO = new CargoDAO();
    EnderecoDAO enderecoDAO = new EnderecoDAO();
    SetorDAO setorDAO = new SetorDAO();
    
    public void consultar() {
        boolean continuarCargo = true;
        System.out.println(msg.consultaCargo1());
        String resposta3 = InputUtils.getInput(scan);
        Cargo cargoBuscado = cargoDAO.buscarPorNome(resposta3);

        if (cargoBuscado != null) {
            
            while (continuarCargo) {
                System.out.println(msg.separadorEmcima());
                System.out.println(msg.cargoEncontrado());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.opEscolhidas());
                String escolhaCargo = InputUtils.getInput(scan);

                if (escolhaCargo.equals("sair")) {
                    continuarCargo = false;
                    break;
                }

                String[] escolhas = escolhaCargo.split(",");

                for (String op : escolhas) {
                    System.out.println(msg.separadorEmcima());
                    switch (op.trim()) {
                        case "1":
                        case "nome":
                            System.out.println("Nome: " + cargoBuscado.getNome());
                            break;
                        case "2":
                        case "salario base":
                            System.out
                                    .println("Salário Base: " + cargoBuscado.getSalarioBase());
                            break;
                        case "3":
                        case "hierarquia":
                            System.out.println("Hierarquia: " + cargoBuscado.getHierarquia());
                            break;
                        case "4":
                        case "requisitos":
                            System.out.println("Requisitos: " + cargoBuscado.getRequisitos());
                            break;
                        case "5":
                        case "setor":
                            if (cargoBuscado.getCod_setor() != null) {
                                Setor setorBuscado = setorDAO
                                        .buscarPorCodigo(cargoBuscado.getCod_setor());
                                if (setorBuscado != null) {
                                    System.out.println("setor: " + setorBuscado.getNome());
                                } else {
                                    System.out.println("Setor não encontrado para esse cargo.");
                                }
                            } else {
                                System.out.println("Código de setor não associado ao cargo.");
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
                    continuarCargo = false;
                }
            }
        } else {
            System.out.println(msg.cargoNaoEncontrado());
        }
    }
    public void criar() {
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
                    "Qual o código do setor do cargo? [Caso necessário,crie um setor antes.]");
            String cargoCodSetor = scan.nextLine();

            Cargo novoCargo = new Cargo(
                    cargoNome,
                    cargoSalarioBase,
                    cargoHierarquia,
                    cargoRequisitos,
                    cargoCodSetor);
            cargoDAO.salvar(novoCargo);
            System.out.println(msg.operacaoExecutada());

            System.out.println("Deseja criar outro cargo?");
            String resposta = InputUtils.getInput(scan);

            if (!resposta.equals("sair")) {
                System.out.println(msg.operacaoEncerrada());
                continuarCargo = false;
            }
        }
    }


    public void atualizar() {
        System.out.println(msg.atualizarCargo1());
        String escolhaCargo = InputUtils.getInput(scan);
        Cargo cargoBucasdo = cargoDAO.buscarPorNome(escolhaCargo);

        if (cargoBucasdo != null) {
            
            boolean escolhaAtualizarCargo = true;
            while (escolhaAtualizarCargo) {
                System.out.println(msg.separadorEmcima());
                System.out.println(msg.opUpdateCargo());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.opAlterar());
                String respostaAtualizarCargo = InputUtils.getInput(scan);
                switch (respostaAtualizarCargo) {
                    case "1":
                    case "nome":
                        System.out.println("Qual será o novo nome?");
                        String novoNome = InputUtils.getInput(scan);

                        cargoBucasdo.setNome(novoNome);
                        cargoDAO.atualizar(escolhaCargo, cargoBucasdo);

                        break;
                    case "2":
                    case "salario base":
                        System.out.println("Qual será o novo Salario Base?");
                        String novoSalario = InputUtils.getInput(scan);

                        cargoBucasdo.setSalarioBase(novoSalario);
                        cargoDAO.atualizar(escolhaCargo, cargoBucasdo);

                        break;
                    case "4":
                    case "requisitos":
                        System.out.println("Quais serão os novos requisitos?");
                        String novoRequisito = InputUtils.getInput(scan);

                        cargoBucasdo.setRequisitos(novoRequisito);
                        cargoDAO.atualizar(escolhaCargo, cargoBucasdo);
                        break;
                    case "3":
                    case "hierarquia":
                        System.out.println("Quais a nova hierarquia?");
                        String novaHierarquia = InputUtils.getInput(scan);

                        cargoBucasdo.setHierarquia(novaHierarquia);
                        cargoDAO.atualizar(escolhaCargo, cargoBucasdo);
                        break;
                    case "5":
                    case "cod setor":
                        System.out.println("Quais o codigo do novo setor?");
                        String novoSetor = InputUtils.getInput(scan);

                        cargoBucasdo.setCod_setor(novoSetor);
                        cargoDAO.atualizar(escolhaCargo, cargoBucasdo);
                        break;
                    case "sair":
                        escolhaAtualizarCargo = false;
                        System.out.println(msg.operacaoEncerrada());
                        break;
                    default:
                        System.out.println(msg.opInvalida());
                        break;
                }
            }
        } else {
            System.out.println(msg.cargoNaoEncontrado() + msg.opTentarNovamente());
            String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
            if (!tentarNovamente.equals("sim")) {
                System.out.println(msg.operacaoEncerrada());
            }
        }
    }

    public void deletar() {
        boolean cargoEncontrado = false;
        while (!cargoEncontrado) {
            System.out.println(msg.deletarCargo1());
            String cargoNome = InputUtils.getInput(scan);

            Cargo cargoBuscado = cargoDAO.buscarPorNome(cargoNome);
            if (cargoBuscado != null) {
                System.out.println(msg.separadorEmcima());
                System.out.println("Cargo encontrado: " + cargoBuscado.getNome());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.deletarCampo());
                String confirmacao = InputUtils.getInput(scan).toLowerCase();
                if (confirmacao.equals("sim")) {
                    cargoDAO.deletar(cargoNome);
                    cargoEncontrado = true; 
                } else if (confirmacao.equals("nao")) {
                    System.out.println(msg.operacaoCancelada());
                    cargoEncontrado = true; 
                } else {
                    System.out.println(msg.respInvalida());
                }
            } else {
                System.out.println(msg.cargoNaoEncontrado() + msg.opTentarNovamente());
                String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
                if (!tentarNovamente.equals("sim")) {
                    System.out.println(msg.operacaoEncerrada());
                    break;
                }
            }
        }
    }
}
