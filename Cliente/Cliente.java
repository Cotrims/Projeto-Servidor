import java.net.*;
import java.io.*;

public class Cliente
{
	public static final String HOST_PADRAO  = "localhost";
	public static final int    PORTA_PADRAO = 3000;

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

            System.err.println ("1Indique o servidor e a porta corretos!\n");
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

            System.err.println ("2Indique o servidor e a porta corretos!\n");
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

            System.err.println ("3Indique o servidor e a porta corretos!\n");
            return;
        }

        Parceiro servidor=null;
        try
        {
            servidor =
            new Parceiro (conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {			erro.printStackTrace();

            System.err.println ("4Indique o servidor e a porta corretos!\n");
            return;
        }

	// colocar tudo os negocio bonitinho de par ou impar

	System.out.print("Digite seu nome: ");
	String nome = Teclado.getUmString();
	try{
		servidor.receba(new PedidoDeNome(nome));
	}
	catch(Exception ex){}

        char opcao=' ';
        do
        {
            System.out.print ("J = Jogar\n" +
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
				if (opcao == 'J')
				{
					char escolha=' ';
					servidor.receba(new PedidoDeJogo());
					if(servidor.getEscolher())
					{
						System.out.print ("Par [P] ou ímpar [I]?");
						for(;;)
						{
							try
							{
								escolha = Teclado.getUmChar();
								if ("PI".indexOf(opcao)!= -1)
									break;
								else
								{
									System.err.println ("Opcao invalida!\n");
									continue;
								}
							}
							catch (Exception erro)
							{
								System.err.println ("Opcao invalida!\n");
							}
						}
						servidor.setEscolha(escolha);
						servidor.receba(new PedidoDeEscolha(escolha));
					}
					else
						System.out.print("Aguarde...");

					switch(servidor.getEscolha())
					{
						case 'P':
							System.out.println ("Você é par");
						break;
						case 'I':
							System.out.println ("Você é ímpar");
						break;
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
					System.out.println ("Seu número: " + valor);
					servidor.receba(new PedidoDeNumero(valor));

					System.out.println ("Número oponente: "+servidor.getNumeroOponente());

					servidor.receba (new PedidoDeResultado());
					Resultado resultado = (Resultado)servidor.envie();
					System.out.println ("E o vencedor é: " + resultado.getVencedor());
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
        try{
        	servidor.receba(new PedidoParaSair());
		}
		catch(Exception ex){}
    }
}