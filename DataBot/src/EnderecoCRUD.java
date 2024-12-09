import java.util.*;

public class EnderecoCRUD implements CRUD {

    Scanner scan = new Scanner(System.in);
    EnderecoDAO enderecoDAO = new EnderecoDAO();
    Mensagens msg = new Mensagens();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void consultar() {
        // não tem como consultar endereço
    }

    public void criar() {
        boolean continuarEndereco = true;

        while (continuarEndereco) {
            System.out.println("Qual a rua?");
            String enderecoRua = scan.nextLine();
            System.out.println("Qual o número?");
            String enderecoNumero = scan.nextLine();
            System.out.println("Qual o bairro?");
            String enderecoBairro = scan.nextLine();
            System.out.println("Qual o complemento? [nullable]");
            String enderecoComplemento = scan.nextLine();

            if (enderecoComplemento.isBlank()) {
                enderecoComplemento = null; 
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
                System.out.println(msg.operacaoEncerrada());
                continuarEndereco = false;
                break;
            }
        }
    }

    public void atualizar() {
        System.out.println(msg.atualizarEnd1());
        Integer escolhaEndereco = scan.nextInt();
        Endereco enderecoBuscado = enderecoDAO.buscarPorCodigo(escolhaEndereco);

        if (enderecoBuscado != null) {
            boolean escolhaAtualizarEndereco = true;
            while (escolhaAtualizarEndereco) {
                System.out.println(msg.separadorEmcima());
                System.out.println(msg.endEncontrado());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.opAlterar());
                String respostaAtualizarEndereco = InputUtils.getInput(scan);

                switch (respostaAtualizarEndereco) {
                    case "4":
                    case "rua":
                        System.out.println("Qual será a nova rua?");
                        String novaRua = InputUtils.getInput(scan);

                        enderecoBuscado.setRua(novaRua);
                        enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                        break;
                    case "1":
                    case "numero":
                        System.out.println("Qual será o novo número?");
                        String novoNumero = InputUtils.getInput(scan);

                        enderecoBuscado.setNumero(novoNumero);
                        enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                        break;
                    case "2":
                    case "bairro":
                        System.out.println("Qual será o novo bairro?");
                        String novoBairro = InputUtils.getInput(scan);

                        enderecoBuscado.setBairro(novoBairro);
                        enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                        break;
                    case "5":
                    case "complemento":
                        System.out.println("Qual será o novo complemento?");
                        String novoComplemento = InputUtils.getInput(scan);

                        enderecoBuscado.setComplemento(novoComplemento);
                        enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                        break;
                    case "3":
                    case "cep":
                        System.out.println("Qual será o novo CEP?");
                        String NovoCEP = InputUtils.getInput(scan);

                        enderecoBuscado.setCep(NovoCEP);
                        enderecoDAO.atualizar(escolhaEndereco, enderecoBuscado);
                        break;
                    case "sair":
                        System.out.println(msg.operacaoEncerrada());
                        escolhaAtualizarEndereco = false;
                        break;
                    default:
                        System.out.println(msg.opInvalida());
                        break;
                }
            }
        } else {
            System.out.println(msg.endNaoEncontrado() + "Tente novamente.");
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
                System.out.println(msg.separadorEmcima());
                System.out.println("Funcionário encontrado: " + funcionarioBuscado.getNome());
                System.out.println("Código endereço: " + funcionarioBuscado.getCod_endereco());
                System.out.println(msg.separadorEmbaixo());
                System.out.println(msg.deletarCampo());
                String confirmacao = InputUtils.getInput(scan).toLowerCase();

                if (confirmacao.equals("sim")) {
                    enderecoDAO.deletar(funcionarioCpf);
                    enderecoEncontrado = true; 
                } else if (confirmacao.equals("nao")) {
                    System.out.println(msg.operacaoCancelada());
                    enderecoEncontrado = true; 
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