public class Mensagens {
    //ERRO
	public void nEntender(){
        System.out.println("\nDesculpe, não entendi sua resposta. Sou um chatbot simples, tente usar respostas simples que contenham \"sim\" ou \"não\"");
    }

    // TUTORIAL
    public void tutorial(){
        System.out.println("\nVocê pode começar escolhendo de qual funcionário a consulta será feita ");
        System.out.println("Depois escolha qual dado você irá colsultar");
        System.out.println("Caso não saiba a informação completa, tente digitar o mínimo que sabe para eu lhe dar opções de funcinários");
    }

    // Pergunta inicial
    public String perguntaEscolha(){
        return "Você gostaria de consultar, criar, atualizar ou deletar um dado?";
    }

    //consultar

     public String consultaInicial(){
         return "Você gostaria de consultar a informação de um funcionário, de um setor ou de um cargo?";
     }
    
        // FUNCIONÁRIO
        public String consultaFunc1(){
            return "Você gostaria de consultar os dados de qual funcionário?";
        }
        public String consultaFunc2(){
            return "O que você quer saber desse funcionário?";
        }
    
        // SETOR
        public String consultaSetor1(){
            return "Você gostaria de consultar as informações de qual setor?";
        }
        public String consultaSetor2(){
            return "O que você gostaria saber sobre esse setor?";
        }
    
        // CARGO
        public String consultaCargo1(){
            return "Você gostaria de consultar as informações de qual cargo?";
        }   
        public String consultaCargo2(){
            return "O que você gostaria de consultar sobre esse cargo?";
        }
    
        // LOOP
        public String consultaSair(){
            return "Gostaria de fazer outro tipo de consulta?";
        }
        // LOOP
        public String consultaSairCaso(){
            return "Gostaria de fazer a consulta de outro dado?";
        
        }


    //CRIAR //ARRUMAR

    public String criarInicial(){
        return "Você gostaria de criar a informação de um funcionário, de um setor ou de um cargo?";
    }

        //funcionário
        public String criarFunc1(){
            return "Você gostaria de criar os dados de qual funcionário?";
        }
        public String criarFunc2(){
            return "O que você quer criar desse funcionário?";
        }

        //setor
        public String criarSetor1(){
            return "Você gostaria de criar os dados de qual setor?";
        }
        public String criarSetor2(){
            return "O que você gostaria de criar desse setor?";
        }

        //cargo
        public String criarCargo1(){
            return "Você gostaria de criar os dados de qual cargo?";
        }
        public String criarCargo2(){
            return "O que você gostaria de criar deste cargo?";
        }

    //atualizar

    public String atualizarInicial(){
        return "Você gostaria de alterar a informação de um funcionário, de um setor ou de um cargo?";

    }

        //funcionario
        public String atualizarFunc1(){
            return "Você gostaria de alterar os dados de qual funcionário?";
        }
        public String atualizarFunc2(){
            return "O que você quer alterar nesse funcionário?";
        }

        //setor
        public String atualizarSetor1(){
            return "Você gostaria de alterar os dados de qual setor?";
        }
        public String atualizarSetor2(){
            return "O que você gostaria de alterar deste setor?";
        }

        //cargo
        public String atualizarCargo1(){
            return "Você gostaria de alterar os dados de qual cargo?";
        }
        public String atualizarCargo2(){
            return "O que você gostaria de alterar deste cargo?";
        }

        //Endereço
        public String atualizarEnd1(){
            return "Qual o código do endereço que gostaria de alterar?";
        }


    //deletar

    public String deletarInicial(){
        return "Você gostaria de deletar a informação de um funcionário, de um setor ou de um cargo?";
    }
    public String deletarCampo(){
        return "Deseja realmente deletar este campo? (sim/nao)";
    }
    public String campoDeletado(){
        return " deletado com sucesso.";
    }

        //funcionario
        public String deletarFunc1(){
            return "Você gostaria de deletar os dados de qual funcionário?";
        }
        public String deletarFunc2(){
            return "O que você quer deletar nesse funcionário?";
        }

        //setor
        public String deletarSetor1(){
            return "Você gostaria de deletar os dados de qual setor?";
        }
        public String deletarSetor2(){
            return "O que você gostaria de deletar deste setor?";
        }

        //cargo
        public String deletarCargo1(){
            return "Você gostaria de deletar os dados de qual cargo?";
        }
        public String deletarCargo2(){
            return "O que você gostaria de deletar deste cargo?";
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
        return "Digite 'sim' para continuar ou digite 'sair' para encerrar";
    }
    public String opEscolhidas(){
        return "Digite as opções que deseja ver (separadas por vírgula, ex: nome, rua, email) ou 'sair' para encerrar.";
    }
    public String opInvalida(){
        return "Opção inválida, tente novamente.";
    }
    public String respInvalida(){
        return "Resposta não compreendida. Por favor, responda com 'sim' ou 'nao'.";
    }
    public String operacaoExecutada(){
        return "Operação executada com sucesso.";
    }
    public String operacaoCancelada(){
        return "Operação cancelada.";
    }
    public String operacaoEncerrada(){
        return "Encerrando operação.";
    }
    
    
    //FUNCIONÁRIO
        public String funcionarioCpf(){ 
            return "Qual o CPF do funcionário "; // "da operação que deseja executar?"
        }

        public String funcNaoEncontrado(){
            return "Funcionário não encontrado."; // deseja tentar novamente (sim/não)?
        }


        public String funcEncontrado(){
          return 
            "Funcionário encontrado!\nOpções:" +
            "\n.Nome" +
            "\n.Nascimento" +
            "\n.CPF" +
            "\n.RG" +
            "\n.Gênero" +
            "\n.Projetos" +
            "\n.Telefone" +
            "\n.Email" +
            "\n.Endereço";
        }
        public String opUpdateFunc(){
            return 
            "\nFuncionário encontrado!\nOpções:" +
            "\n.Nome" +
            "\n.Nascimento" +
            "\n.CPF" +
            "\n.RG" +
            "\n.Gênero" +
            "\n.Projetos" +
            "\n.Telefone" +
            "\n.Email" +
            "\n.Endereço" +
            "\n.carga horária" +
            "\n.data admissão" +
            "\n.endereco" +
            "\n.cargo";
          }

    //SETOR
        public String setorNaoEncotrado(){ //fazer um loop while com condição boolean
          return "Setor não encontrado."; //" Deseja tentar novamente? (sim/não)"
        }
    
    
        public String setorEncontrado(){
          return 
            "\nSetor encontrado!" +
            "\n.Nome" +
            "\n.Descricao";
        }

    //CARGO
      public String cargoNaoEncontrado(){
        return "Cargo não encontrado."; //" Deseja tentar novamente? (sim/não)"
      }

      public String cargoEncontrado(){
        return 
            "\nCargo encontrado!\nOpções:" +
            "\n.Nome" +
            "\n.SalarioBase" +
            "\n.Hierarquia" +
            "\n.Requisitos" +
            "\n.Setor";
      }
    
    //ENDEREÇO
    public String endNaoEncontrado(){
        return "Endereço não encontrado"; 
    }
    public String endEncontrado(){
        return 
        "\nEndereço encontrado!\n" +
        "\n.Número" +
        "\n.Bairro" +
        "\n.Cep"+
        "\n.Rua"+
        "\n.Complemento";
    }
}