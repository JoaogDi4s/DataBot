import java.util.*;

public class SetorCRUD implements CRUD {
    Scanner scan = new Scanner(System.in);
    Mensagens msg = new Mensagens();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    CargoDAO cargoDAO = new CargoDAO();
    EnderecoDAO enderecoDAO = new EnderecoDAO();
    SetorDAO setorDAO = new SetorDAO();
    
    public void consultar() {
        boolean continuarSetor = true;
        System.out.println(msg.consultaSetor1());
        String resposta2 = InputUtils.getInput(scan);
        Setor setorBuscasdo = setorDAO.buscarPorNome(resposta2);

        if (setorBuscasdo != null) {
            
            while (continuarSetor) {
                System.out.println(msg.separadorEmcima());
                System.out.println(msg.setorEncontrado());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.opEscolhidas());
                String escolhaSetor = InputUtils.getInput(scan);

                if (escolhaSetor.equals("sair")) {
                    continuarSetor = false;
                    break;
                }

                String[] escolhas = escolhaSetor.split(",");

                for (String op : escolhas) {
                    System.out.println(msg.separadorEmcima());
                    switch (op.trim()) {
                        case "1":
                        case "nome":
                            System.out.println("Nome: " + setorBuscasdo.getNome());
                            break;
                        case "2":
                        case "descricao":
                            System.out.println("Descrição: " + setorBuscasdo.getDescricao());
                            break;
                        default:
                            System.out.println("Opção inválida: " + op);
                    }
                    System.out.println(msg.separadorEmbaixo());
                }

                System.out.println(msg.consultaSairCaso());
                String respostaSair = InputUtils.getInput(scan);
                if (respostaSair.equalsIgnoreCase("nao")) {
                    continuarSetor = false;
                }
            }
        } else {
            System.out.println(msg.setorNaoEncotrado());
        }
    }

    public void criar() {
        boolean continuarSetor = true;

        while (continuarSetor) {
            System.out.println("Qual o nome do setor?");
            String setorNome = scan.nextLine();

            System.out.println("Qual a descrição do setor?");
            String setorDescricao = scan.nextLine();

            Setor novoSetor = new Setor(setorNome, setorDescricao);
            setorDAO.salvar(novoSetor);
            System.out.println(msg.operacaoExecutada());

            System.out.println("Deseja criar outro setor?");
            String resposta = InputUtils.getInput(scan);

            if (!resposta.equals("sim")) {
                System.out.println(msg.operacaoEncerrada());
                continuarSetor = false;
                break;
            }
        }
    }

    public void atualizar() {
        System.out.println(msg.atualizarSetor1());
        String escolhaSetor = InputUtils.getInput(scan);
        Setor setorBuscado = setorDAO.buscarPorNome(escolhaSetor);

        if (setorBuscado != null) {
            
            boolean escolhaAtualizarSetor = true;
            while (escolhaAtualizarSetor) {
                System.out.println(msg.separadorEmcima());
                System.out.println(msg.setorEncontrado());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.opAlterar());
                String respostaAtualizarSetor = InputUtils.getInput(scan);
                switch (respostaAtualizarSetor) {
                    case "1":
                    case "nome":
                        System.out.println("Qual será o novo nome?");
                        String novoNome = InputUtils.getInput(scan);

                        setorBuscado.setNome(novoNome);
                        setorDAO.atualizar(escolhaSetor, setorBuscado);

                        break;
                    case "2":
                    case "descricao":
                        System.out.println("Qual será a nova descrição?");
                        String novaDescricao = InputUtils.getInput(scan);

                        setorBuscado.setDescricao(novaDescricao);
                        setorDAO.atualizar(escolhaSetor, setorBuscado);

                        break;
                    case "sair":
                        escolhaAtualizarSetor = false;
                        break;
                    default:
                        System.out.println(msg.opInvalida());
                        break;
                }
            }
        } else {
            System.out.println(msg.setorNaoEncotrado());
        }
    }

    public void deletar() {
        boolean setorEncontrado = false;
        while (!setorEncontrado) {
            System.out.println(msg.deletarSetor1());
            String setorNome = InputUtils.getInput(scan);

            Setor setorBuscado = setorDAO.buscarPorNome(setorNome);
            if (setorBuscado != null) {
                System.out.println(msg.separadorEmcima());
                System.out.println("Setor encontrado: " + setorBuscado.getNome());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.deletarCampo());
                String confirmacao = InputUtils.getInput(scan).toLowerCase();
                if (confirmacao.equals("sim")) {
                    setorDAO.deletar(setorNome);
                    setorEncontrado = true; 
                } else if (confirmacao.equals("nao")) {
                    System.out.println(msg.operacaoCancelada());
                    setorEncontrado = true;
                } else {
                    System.out.println(msg.respInvalida());
                }
            } else {
                System.out.println(msg.setorNaoEncotrado() + " Deseja tentar novamente? (sim/nao)");
                String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
                if (!tentarNovamente.equals("sim")) {
                    System.out.println(msg.operacaoEncerrada());
                    break; 
                }
            }
        }
    }
}