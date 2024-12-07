import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // OBJETOS
        Introducao intro = new Introducao();
        Mensagens msg = new Mensagens();
        Scanner scan = new Scanner(System.in);
        FuncionarioCRUD funcionarioCRUD = new FuncionarioCRUD();
        SetorCRUD setorCRUD = new SetorCRUD();
        CargoCRUD cargoCRUD = new CargoCRUD();
        EnderecoCRUD enderecoCRUD = new EnderecoCRUD();

        System.out.println(msg.consultaFunc2());

        // INTRODUÇÃO
        intro.introducao();

        boolean escolha = true;
        while (escolha) {
            System.out.println(msg.perguntaEscolha());
            String respostaEscolha = InputUtils.getInput(scan);
            switch (respostaEscolha) {
                // CONSULTAR
                case "consultar":
                    boolean continuar = true;
                    while (continuar) {
                        System.out.println(msg.consultaInicial());
                        String resposta0 = InputUtils.getInput(scan);

                        switch (resposta0) {
                            case "funcionario":
                                funcionarioCRUD.consultar();
                                break;
                            case "setor":
                                setorCRUD.consultar();
                                break;
                            case "cargo":
                                cargoCRUD.consultar();
                                break;
                            case "sair":
                                continuar = false;
                                break;
                            default:
                                System.out.println(msg.opInvalida());
                        }
                    }
                    break;
                // CRIAR
                case "criar":
                    boolean escolhaCriar = true;
                    System.out.println(msg.criarInicial());
                    while (escolhaCriar) {
                        String respostaCriar = InputUtils.getInput(scan);
                        switch (respostaCriar) {
                            case "funcionario":
                                funcionarioCRUD.criar();
                                break;
                            case "setor":
                                setorCRUD.criar();
                                break;
                            case "cargo":
                                cargoCRUD.criar();
                                break;
                            case "endereco":
                                enderecoCRUD.criar();
                                break;
                            case "sair":
                                escolhaCriar = false;
                                break;
                            default:
                                System.out.println(msg.opInvalida());
                                break;
                        }
                    }

                    break;

                // ATUALIZAR
                case "atualizar":
                    boolean escolhaAtualizar = true;
                    System.out.println(msg.atualizarInicial());
                    while (escolhaAtualizar) {
                        String respostaAtualizar = InputUtils.getInput(scan);
                        switch (respostaAtualizar) {
                            case "funcionario":
                                funcionarioCRUD.atualizar();
                                break;
                            case "setor":
                                setorCRUD.atualizar();
                                break;
                            case "cargo":
                                cargoCRUD.atualizar();
                                break;
                            case "endereco":
                                enderecoCRUD.atualizar();
                                break;
                            case "sair":
                                escolhaAtualizar = false;
                                break;
                            default:
                                System.out.println(msg.opInvalida());
                                break;
                        }
                    }

                    break;
                // DELETAR
                case "deletar":
                    boolean escolhaDeletar = true;
                    System.out.println(msg.deletarInicial());
                    while (escolhaDeletar) {
                        String respostaDeletar = InputUtils.getInput(scan);
                        switch (respostaDeletar) {
                            case "funcionario":
                                funcionarioCRUD.deletar();
                                break;

                            case "setor":
                                setorCRUD.deletar();
                                break;

                            case "cargo":
                                cargoCRUD.deletar();
                                break;

                            case "endereco":

                                break;

                            case "sair":
                                escolhaDeletar = false;
                                break;
                            default:
                                System.out.println(msg.opInvalida());
                                break;
                        }
                    }
                    break;
                case "sair":
                    escolha = false;
                    break;
                default:
                    System.out.println(msg.opInvalida());
                    break;
            }
        }
    }
}