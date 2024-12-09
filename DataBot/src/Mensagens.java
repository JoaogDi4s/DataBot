public class Mensagens {
    //ERRO
	public void nEntender(){
        System.out.println("\n-Desculpe, não entendi sua resposta. Sou um chatbot simples, tente usar respostas simples que contenham \"sim\" ou \"não\"");
    }

    // TUTORIAL
    public void tutorial(){
        System.out.println("\n-Você pode começar escolhendo de qual funcionário a consulta será feita ");
        System.out.println("Depois escolha qual dado você irá colsultar");
        System.out.println("Caso não saiba a informação completa, tente digitar o mínimo que sabe para eu lhe dar opções de funcinários");
    }

    // Pergunta inicial
    public String perguntaEscolha(){
        return "\n-Você gostaria de consultar, criar, atualizar ou deletar um dado?";
    }

    //consultar

    public String consultaInicial(){
         return "\n-Você gostaria de consultar a informação de um funcionário, de um setor ou de um cargo? \n[ou 'sair' para encerrar.]";
     }
    
        // FUNCIONÁRIO
        public String consultaFunc1(){
            return "-Você gostaria de consultar os dados de qual funcionário?";
        }
        // SETOR
        public String consultaSetor1(){
            return "-Você gostaria de consultar as informações de qual setor?";
        }
        // CARGO
        public String consultaCargo1(){
            return "-Você gostaria de consultar as informações de qual cargo?";
        }  
        // LOOP
        public String consultaSairCaso(){
            return "-Gostaria de fazer a consulta de outro dado?";
        
        }

    //CRIAR 

    public String criarInicial(){
        return "\n-Você gostaria de criar um funcionário, um setor, um cargo ou um endereco? \n[ou 'sair' para encerrar.]";
    }

    //atualizar

    public String atualizarInicial(){
        return "\n-Você gostaria de alterar a informação de um funcionário, de um setor, de um cargo ou de um endereço? \n[ou 'sair' para encerrar.]";

    }
    public String opAlterar(){
        return "-Digite a opção que deseja alterar ou 'Sair' para encerrar:";
    }

        //funcionario (busca por cpf)

        //setor
        public String atualizarSetor1(){
            return "-Você gostaria de alterar os dados de qual setor?";
        }
        //cargo
        public String atualizarCargo1(){
            return "-Você gostaria de alterar os dados de qual cargo?";
        }
        //Endereço
        public String atualizarEnd1(){
            return "-Qual o código do endereço que gostaria de alterar?";
        }

    //deletar

    public String deletarInicial(){
        return "\n-Você gostaria de deletar um funcionário, um setor, um cargo ou um endereço? \n[ou 'sair' para encerrar.]";
    }
    public String deletarCampo(){
        return "-Deseja realmente deletar este campo? (sim/nao)";
    }
    public String campoDeletado(){
        return " deletado com sucesso.";
    }

        //funcionario
        public String deletarFunc1(){
            return "-Você gostaria de deletar os dados de qual funcionário?";
        }
        //setor
        public String deletarSetor1(){
            return "-Você gostaria de deletar os dados de qual setor?";
        } 
        //cargo
        public String deletarCargo1(){
            return "-Você gostaria de deletar os dados de qual cargo?";
        }
  
    //TODOS //REVER
    // JA MOSTRAR TODAS AS INFORMAÇÕES, SEM PERGUNTAR QUAL ELE QUER SABER
    public String separadorEmcima(){
        return "\n--------------------";
    }
    public String separadorEmbaixo(){
        return "--------------------\n";
    }
    public String escolhaContinuar(){
        return "-Digite 'sim' para continuar ou digite 'sair' para encerrar";
    }
    public String opEscolhidas(){
        return "-Digite as opções que deseja separadas por vírgula, ou 'sair' para encerrar.";
    }
    public String opInvalida(){
        return "-Opção inválida, tente novamente.";
    }
    public String opTentarNovamente(){
        return "Deseja tentar novamente (sim/não)?";
    }
    public String respInvalida(){
        return "-Resposta não compreendida. Por favor, responda com 'sim' ou 'nao'.";
    }
    public String operacaoExecutada(){
        return "-Operação executada com sucesso.";
    }
    public String operacaoCancelada(){
        return "-Operação cancelada.";
    }
    public String operacaoEncerrada(){
        return "-Encerrando operação...";
    }
    
    
    //FUNCIONÁRIO
        public String funcionarioCpf(){ 
            return "-Qual o CPF do funcionário "; // "da operação que deseja executar?"
        }

        public String funcNaoEncontrado(){
            return "-Funcionário não encontrado.\n"; // deseja tentar novamente (sim/não)?
        }


        public String funcEncontrado(){
          return 
            "-Funcionário encontrado!\nOpções:" +
            "\n1.Nome" +
            "\n2.Nascimento" +
            "\n3.CPF" +
            "\n4.RG" +
            "\n5.Gênero" +
            "\n6.Projetos" +
            "\n7.Telefone" +
            "\n8.Email" +
            "\n9.Data de admissão" +
            "\n10.Cargo" +
            "\n11.Carga horária" +
            "\n12.endereco"
            ;
        }
        public String opUpdateFunc(){
            return 
            "\nFuncionário encontrado!\nOpções:" +
            "\n1.Nome" +
            "\n2.Nascimento" +
            "\n3.CPF" +
            "\n4.RG" +
            "\n5.Gênero" +
            "\n6.Projetos" +
            "\n7.Telefone" +
            "\n8.Email" +
            "\n9.Data admissão" +
            "\n10.Cod cargo" +
            "\n11.Carga horária" +
            "\n12.Cod endereco";
          }

    //SETOR
        public String setorNaoEncotrado(){
          return "-Setor não encontrado.\n"; 
        }
    
    
        public String setorEncontrado(){
          return 
            "-Setor encontrado!" +
            "\n1.Nome" +
            "\n2.Descricao";
        }

    //CARGO
      public String cargoNaoEncontrado(){
        return "-Cargo não encontrado.\n";
      }

      public String cargoEncontrado(){
        return 
            "-Cargo encontrado!" +
            "\n1.Nome" +
            "\n2.Salario base" +
            "\n3.Hierarquia" +
            "\n4.Requisitos" +
            "\n5.Setor";
      }
      public String opUpdateCargo(){
        return 
            "-Cargo encontrado!" +
            "\n1.Nome" +
            "\n2.Salario base" +
            "\n3.Hierarquia" +
            "\n4.Requisitos" +
            "\n5.Cod setor";
      }
    
    //ENDEREÇO
    public String endNaoEncontrado(){
        return "-Endereço não encontrado.\n"; 
    }
    public String endEncontrado(){
        return 
        "-Endereço encontrado!\n" +
        "\n1.Número" +
        "\n2.Bairro" +
        "\n3.Cep"+
        "\n4.Rua"+
        "\n5.Complemento";
    }
}