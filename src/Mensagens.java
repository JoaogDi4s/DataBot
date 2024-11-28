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

    public String pergunta0(){
        return "Você gostaria de consultar a informação de um funcionário, de um setor ou de um cargo?";
    }

    // FUNCIONÁRIO
    public String pergunta1(){
        return "Você gostaria de consultar os dados de qual funcionário?";
    }
    public String pergunta1_1(){
        return "O que você quer saber desse funcionário?";
    }

    // SETOR
    public String pergunta2(){
        return "Você gostaria de consultar as informações de qual setor?";
    }
    public String pergunta2_1(){
        return "O que você gostaria saber sobre esse setor?";
    }

    // CARGO
    public String pergunta3(){
        return "Você gostaria de consultar as informações de qual cargo?";
    }   
    public String pergunta3_1(){
        return "O que você gostaria de consultar sobre esse cargo?";
    }

    // LOOP
    public String peguntaSair(){
        return "Gostaria de fazer outro tipo de consulta?";
    }
    // LOOP
    public String peguntaSairCaso(){
        return "Gostaria de fazer a consulta de outro dado?";
 
    }

    public String perguntaEscolha(){
        return "Você gostaria de consultar, criar ou deletar ou atualizar um dado?";
    }
}   
