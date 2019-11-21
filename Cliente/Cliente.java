import java.net.*;
import java.io.*;

public class Cliente {
	public static final String HOST_PADRAO = "localhost";
	public static final int PORTA_PADRAO = 3000;

	public static void main(String[] args)
	{
		if (args.length > 2)
		{
			System.err.println("Uso esperado: java Cliente [HOST [PORTA]]\n");
			return;
		}

		Socket conexao = null;
		try
		{
			String host = Cliente.HOST_PADRAO;
			int porta = Cliente.PORTA_PADRAO;

			if (args.length > 0)
				host = args[0];

			if (args.length == 2)
				porta = Integer.parseInt(args[1]);
			conexao = new Socket(host, porta);
		}
		catch (Exception erro)
		{
			erro.printStackTrace();

			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		ObjectOutputStream transmissor = null;
		try
		{
			transmissor =
			new ObjectOutputStream(
				conexao.getOutputStream());

		}
		catch (Exception erro)
		{
			erro.printStackTrace();

			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		ObjectInputStream receptor = null;
		try
		{
			receptor =
			new ObjectInputStream(
				conexao.getInputStream());
		}
		catch (Exception erro)
		{
			erro.printStackTrace();

			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		Parceiro servidor = null;
		try
		{
			servidor = new Parceiro(conexao, receptor, transmissor);
		}
		catch (Exception erro)
		{
			erro.printStackTrace();

			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		try
		{
			System.out.println("Aguardando outro jogador...");
			ComunicadoDeIniciar inicio = (ComunicadoDeIniciar) servidor.envie();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String nome = null;
		System.out.print("Digite seu nome: ");
		try
		{
			nome = Teclado.getUmString();
			servidor.receba(new PedidoDeNome(nome));
		}
		catch (Exception ex)
		{
			System.out.println("Nome invalido! Digite novamente:");
			return;
		}

		char opcao = ' ';
		do
		{
			System.out.print("O que deseja fazer, "+nome+"? \n" +
							 "   J = Jogar\n" + "   S = Sair\n");

			try
			{
				opcao = Character.toUpperCase(Teclado.getUmChar());
			}
			catch (Exception erro)
			{
				System.err.println("Opcao invalida!\n");
				continue;
			}

			if ("JS".indexOf(opcao) == -1)
			{
				System.err.println("Opcao invalida!\n");
				continue;
			}

			try {
				if (opcao == 'J')
				{
					servidor.receba(new PedidoDeEscolha(false));
					PedidoDeEscolha pedido = (PedidoDeEscolha)servidor.envie();

					Tipo tipo = null;
					if(pedido.getEscolha())
					{

						System.out.println("Escolha seu tipo de numero:");
						System.out.println("   Par [P]  ou  Impar[I]   ");

						try
						{
						 	tipo = new Tipo(Character.toUpperCase(Teclado.getUmChar()));
							if ("PI".indexOf(tipo.getTipo()) == -1)
								throw new Exception();

							servidor.receba(new PedidoDeTipo(tipo.getTipo()));
						}
						catch (Exception erro)
						{
							System.err.println("Opcao invalida!\n");
							return;
						}
					}
					else
					{
						System.out.println("Aguarde seu oponente escolher...");
						tipo = (Tipo)servidor.envie();
					}

					System.out.println("Voce eh: " + tipo.toString());

					int valor = 0;

					for(;;)
					{
						try
						{
							System.out.print("Escolha seu numero: ");
							valor = Teclado.getUmInt();
							break;
						}
						catch (Exception erro)
						{
							System.err.println("Numero invalido!");
							continue;
						}
					}

					servidor.receba(new PedidoDeNumero(valor));

					Resultado resultado = (Resultado) servidor.envie();
					System.out.println("E o vencedor eh: " + resultado.getVencedor());
				}
			}
			catch (Exception erro)
			{
				System.err.println("Erro de comunicacao com o servidor;");
				System.err.println("Tente novamente!");
				System.err.println("Caso o erro persista, termine o programa");
				System.err.println("e volte a tentar mais tarde!");
			}
		}
		while(opcao!='S');
		try
		{
			servidor.receba(new PedidoParaSair());
		}
		catch(Exception ex)
		{}
	}
}