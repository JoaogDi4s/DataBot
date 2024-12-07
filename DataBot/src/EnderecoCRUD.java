import java.util.*;

public class EnderecoCRUD implements CRUD {

    Scanner scan = new Scanner(System.in);
    EnderecoDAO enderecoDAO = new EnderecoDAO();
    Mensagens msg = new Mensagens();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void criar() {
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

            Endereco novEndereco = new Endereco(
                    enderecoNumero,
                    enderecoBairro,
                    enderecoCEP,
                    enderecoRua,
                    enderecoComplemento);
            enderecoDAO.salvar(novEndereco);
            System.out.println(msg.operacaoExecutada());

            System.out.println("Deseja criar outro endereço?");
            String resposta = InputUtils.getInput(scan);

            if (!resposta.equals("sair")) {
                continuarEndereco = false;
                break;
            }
        }
    }

    public void consultar() {
        // não tem como consultar endereço
    }

    public void atualizar() {
        System.out.println();
        Integer escolhaEndereco = scan.nextInt();
        Endereco enderecoBuscado = enderecoDAO.buscarPorCodigo(escolhaEndereco);

        if (enderecoBuscado != null) {
            System.out.println(msg.separadorEmcima());
            System.out.println(msg.endEncontrado());
            System.out.println(msg.separadorEmbaixo());
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
                        System.out.println(msg.opInvalida());
                        break;
                }
            }
        } else {
            System.out.println(msg.endNaoEncontrado());
        }
    }

    public void deletar() {
        boolean enderecoEncontrado = false;
        while (!enderecoEncontrado) {
            System.out
                    .println(msg.funcionarioCpf() + "cujo endereço você gostaria de deletar?");
            String funcionarioCpf = InputUtils.getInput(scan);

            Funcionario funcionarioBuscado = funcionarioDAO.buscarPorCpf(funcionarioCpf);
            if (funcionarioBuscado != null) {
                System.out.println("Funcionário encontrado: " + funcionarioBuscado.getNome());
                System.out.println(msg.deletarCampo());
                String confirmacao = InputUtils.getInput(scan).toLowerCase();

                if (confirmacao.equals("sim")) {
                    enderecoDAO.deletar(funcionarioCpf); // Método no DAO para deletar o
                                                         // endereço
                    System.out.println("Endereço" + msg.campoDeletado());
                    enderecoEncontrado = true; // Encerra o loop
                } else if (confirmacao.equals("nao")) {
                    System.out.println(msg.operacaoCancelada());
                    enderecoEncontrado = true; // Encerra o loop
                } else {
                    System.out.println(msg.respInvalida());
                }
            } else {
                System.out.println(
                        msg.funcNaoEncontrado() + "Deseja tentar novamente? (sim/nao)");
                String tentarNovamente = InputUtils.getInput(scan).toLowerCase();
                if (!tentarNovamente.equals("sim")) {
                    System.out.println(msg.operacaoEncerrada());
                    break; // Sai do loop
                }
            }
        }
    }
}
