import java.net.*;
import java.io.*;

public class Cliente
{
	public static final String HOST_PADRAO  = "177.220.18.44";
	public static final int    PORTA_PADRAO = 3000;
	private String nome;

	public static void main (String[] args)
	{
        if (args.length>2)
        {
            System.err.println ("Uso esperado: java Cliente [HOST [PORTA]]\n");
            return;
        }

        Socket conexao=null;
        try
        {
            String host = Cliente.HOST_PADRAO;
            int    porta= Cliente.PORTA_PADRAO;

            if (args.length>0)
                host = args[0];

            if (args.length==2)
                porta = Integer.parseInt(args[1]);
			System.out.println (host+" "+porta);
            conexao = new Socket (host, porta);
        }
        catch (Exception erro)
        {			erro.printStackTrace();

            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectOutputStream transmissor=null;
        try
        {
            transmissor =
            new ObjectOutputStream(
            conexao.getOutputStream());
        }
        catch (Exception erro)
        {			erro.printStackTrace();

            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectInputStream receptor=null;
        try
        {
            receptor =
            new ObjectInputStream(
            conexao.getInputStream());
        }
        catch (Exception erro)
        {			erro.printStackTrace();

            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }



        Parceiro servidor=null;
        try
        {
            servidor =
            new Parceiro (conexao, receptor, transmissor, "");
        }
        catch (Exception erro)
        {			erro.printStackTrace();

            System.err.println ("Indique o servidor e a porta corretos!\n");
            return;
        }

        servidor.receba(new PedidoDeNome());
        Nome nome = (Nome)servidor.envie();
        servidor.setNome(nome.getNome());

		System.out.print("Digite seu nome: ");
		this.nome = Teclado.getUmString();

        char opcao=' ';
        do
        {
            System.out.print ("J = Jogar" +
                              "S = Sair)" +
                              "? ");

            try
            {
				opcao = Character.toUpperCase(Teclado.getUmChar());
		    }
		    catch (Exception erro)
		    {
				System.err.println ("Opcao invalida!\n");
				continue;
			}

			if ("JS".indexOf(opcao)==-1)
		    {
				System.err.println ("Opcao invalida!\n");
				continue;
			}

			try
			{
				if (opcao == "J")
				{
					char escolha = ' '; // tem q usar Escolha escolha ou apenaix char escolha?
					int escolhedor = (int)(Math.random());
					if(escolhedor == 0)
					{
						System.out.print ("Par [P] ou ímpar [I]?");
						try
						{
							escolha = Teclado.getUmChar();
						}
						catch (Exception erro)
						{
							System.err.println ("Opcao invalida!\n");
							continue;
						}
						if ("PI".indexOf(opcao)==-1)
						{
							System.err.println ("Opcao invalida!\n");
							continue;
						}
					}
					else
						System.out.print("Aguarde...");

					servidor.receba(new PedidoDeJogo(escolha));

					switch(escolha)
					{
						case 'P':
						System.out.println ("Você é par");
						break;
						case 'I':
						System.out.println ("Você é ímpar");
						break;
						case ' ':
						Escolha escolhaDoOutro = (Escolha) servidor.envie();
						if(escolhaDoOutro.getEscolha() == 'P')
							System.out.println ("Você é ímpar");
						else if(escolhaDoOutro.getEscolha() == 'I')
							System.out.println ("Você é par");
					}

					int valor=0;
					System.out.print ("Escolha seu número:");
					try
					{
						valor = Teclado.getUmInt();
					}
					catch (Exception erro)
					{
						System.err.println ("Número invalido!\n");
						continue;
					}
					System.out.println ("Seu número: " + valor.toString());
					servidor.receba(new PedidoDeNumero(valor));
					Numero numeroDoOutro = (Numero)servidor.envie();
					System.out.println ("Número oponente: "+numeroDoOutro.getNumero());

					servidor.receba (new PedidoDeResultado());
					Resultado resultado = (Resultado)servidor.envie();
					System.out.println ("E o vencedor é: "+resultado.getVencedor());
				}
			}
			catch (Exception erro)
			{
				System.err.println ("Erro de comunicacao com o servidor;");
				System.err.println ("Tente novamente!");
				System.err.println ("Caso o erro persista, termine o programa");
				System.err.println ("e volte a tentar mais tarde!");
			}
        }
        while (opcao != 'S');
    }
}