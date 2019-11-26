import java.net.*;
import java.io.*;

public class Cliente //interface do jogo.
{
	public static final String HOST_PADRAO = "localhost";
	public static final int PORTA_PADRAO = 3000;

	public static void main(String[] args) //o vetor de String "args" recebe o servidor e aporta que sera utilizada.
	{
		if (args.length > 2)
		{
			System.err.println("Uso esperado: java Cliente [HOST [PORTA]]\n");
			return; //o programa para a execucao.
		}

		Socket conexao = null;
		try
		{
			String host = Cliente.HOST_PADRAO;
			int porta = Cliente.PORTA_PADRAO;

			if (args.length > 0) //se foi passado mais de um parâmetro, o primeiro é o servidor.
				host = args[0];

			if (args.length == 2)
				porta = Integer.parseInt(args[1]); //se foi passado dois, o segundo é a porta.

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
			new ObjectOutputStream(conexao.getOutputStream());

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
			receptor = new ObjectInputStream(conexao.getInputStream());
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

		try //aguarda outro jogador, enviando um comunicado de início para o servidor
		{
			System.out.println("@@@@@@    @@@   @@@@@@                    @@@ @@      @@ @@@@@@   @@@   @@@@@@");
			System.out.println("@    @   @   @  @    @                     @  @ @    @ @ @    @  @   @  @    @");
			System.out.println("@    @  @     @ @    @                     @  @  @  @  @ @    @ @     @ @    @");
			System.out.println("@@@@@@  @@@@@@@ @@@@@@                     @  @   @@   @ @@@@@@ @@@@@@@ @@@@@@");
			System.out.println("@       @     @ @   @      @@@@@ @   @     @  @        @ @      @     @ @   @");
			System.out.println("@       @     @ @    @     @   @ @   @     @  @        @ @      @     @ @    @");
			System.out.println("@       @     @ @     @    @@@@@ @@@@@    @@@ @        @ @      @     @ @     @\n");
			System.out.println("Aguardando outro jogador...");
			ComunicadoDeIniciar inicio = (ComunicadoDeIniciar) servidor.envie();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String nome = null;
		System.out.print("Digite seu nome: ");

		try //recebe o nome do jogador e envia para o servidor
		{
			nome = Teclado.getUmString();
			servidor.receba(new PedidoDeNome(nome));
		}
		catch (Exception ex)
		{
			System.out.println("Nome invalido! Digite novamente:");
			continue;
		}

		char opcao = ' ';

		do //verifica a opcao escolhia pelo usuario
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
				if (opcao == 'J') //se a opcao for J (jogar novamente), o jogo inicia.
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
						 	tipo = new Tipo(Character.toUpperCase(Teclado.getUmChar())); //o usuario escolhe se quer par ou impar
							if ("PI".indexOf(tipo.getTipo()) == -1)
								throw new Exception();

							servidor.receba(new PedidoDeTipo(tipo.getTipo()));
						}
						catch (Exception erro)
						{
							System.err.println("Opcao invalida!\n");
							continue;
						}
					}
					else
					{
						System.out.println("Aguarde seu oponente escolher...");
						tipo = (Tipo)servidor.envie();
					}

					System.out.println("Você é: " + tipo.toString()); //printa a escolha do jogador

					int valor = 0;

					for(;;) //aguarda o jogador escolher um número
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

					servidor.receba(new PedidoDeNumero(valor)); //envia o numero pedido pelo jogador

					Resultado resultado = (Resultado) servidor.envie();

					System.out.println("E o vencedor é....: " + resultado.getVencedor());
				}
			}
			catch (Exception erro)
			{
				System.err.println("Erro de comunicacao com o servidor, tente novamente!");
				System.err.println("Caso o erro persista, termine o programa e volte a tentar mais tarde!\n");
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