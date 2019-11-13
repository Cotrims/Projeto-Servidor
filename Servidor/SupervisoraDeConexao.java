import java.io.*;
import java.net.*;
import java.util.*;

public class SupervisoraDeConexao extends Thread
{
    private int numero;
	private int numeroOutro;
    private Parceiro            usuario;
    private Socket              conexao;
    private ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao
    (Socket conexao, ArrayList<Parceiro> usuarios)
    throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }

    public void run ()
    {
        ObjectInputStream receptor=null;
        try
        {
            receptor=
            new ObjectInputStream(
            this.conexao.getInputStream());
        }
        catch (Exception erro)
        {
            return;
        }

        ObjectOutputStream transmissor;
        try
        {
            transmissor =
            new ObjectOutputStream(
            this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            try
            {
                receptor.close ();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }

        try
        {
            this.usuario =
            new Parceiro (this.conexao,
                          receptor,
                          transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        // colocar tudo os negocio bonitinho de par ou impar


        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
            }


            for(;;)
            {
                Comunicado comunicado = this.usuario.envie ();

                if      (comunicado==null)
                    return;
                else if (comunicado instanceof PedidoDeNome)
                {
                    System.out.print("Digite seu nome: ");
                    this.usuario.receba (new Nome(Teclado.getUmString()));
					//this.usuario.setNome(Teclado.getUmString()); // ver onde q isso vai com o gitzel pq tem q passar pro cliente
                }
                else if (comunicado instanceof PedidoDeJogo)
                {
					PedidoDeJogo pedido = (PedidoDeJogo)comunicado;
					escolha = pedido.getEscolha();
					if(escolha == ' ')
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
					else if(escolha == 'P')
						System.out.print("Você é ímpar");
						else if(escolha == 'I')
							System.out.print("Você é par");
					this.usuario.setEscolha(escolha);
                }
                else if (comunicado instanceof PedidoDeNumero)
				{
					PedidoDeNumero pedido = (PedidoDeNumero)comunicado;
					numeroOutro = pedido.getNumero();
					System.out.print ("Escolha seu número:");
					try
					{
						numero = Teclado.getUmInt();
					}
					catch (Exception erro)
					{
						System.err.println ("Número invalido!\n");
						continue;
					}
					System.out.println ("Seu número: " + valor.toString());
                }
                else if (comunicado instanceof PedidoDeResultado)
                {
					//ver qual é o vencedor
					String vencedor;
					if((this.numero + this.numeroOutro) % 2 = 0)
					{
						if(this.usuario.getEscolha() == 'P')
							vencedor = this.usuario.getNome();
						else
							vencedor = outroUsuario.getNome(); // como pegar o outro usuario
					}

                    this.usuario.receba (new Resultado (vencedor));
                }
                else if (comunicado instanceof PedidoParaSair)
                {
                    synchronized (this.usuarios)
                    {
                        this.usuarios.remove (this.usuario);
                    }
                    this.usuario.adeus();
                }
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close ();
                receptor   .close ();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }
    }
}
