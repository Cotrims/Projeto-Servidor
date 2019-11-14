import java.io.*;
import java.net.*;
import java.util.*;

public class SupervisoraDeConexao extends Thread
{
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
				int escolhedor;
                Comunicado comunicado = this.usuario.envie ();

                if(comunicado==null)
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
					escolhedor = Math.random();
					usuarios[escolhedor].setEscolher(true);
                }
                else if (comunicado instanceof PedidoDeEscolha)
				{
					int ind;
					char esc;
					if(escolhedor == 0)
						ind = 1;
					else
						ind = 0;
					if(this.usuarios[escolhedor].getEscolha() == 'P')
						esc = 'I';
					else
						esc = 'P';
					this.usuarios[ind].setEscolha(esc);
                }
                else if (comunicado instanceof PedidoDeNumero)
				{
					PedidoDeNumero pedido = (PedidoDeNumero)comunicado;
					int numero = pedido.getNumero();
					this.usuario.setNumero(numero);
					if(this.usuarios[0].equals(this.usuario))
						this.usuarios[1].setNumeroOponente(numero);
					else
						this.usuarios[0].setNumeroOponente(numero);

                }
                else if (comunicado instanceof PedidoDeResultado)
                {
					String vencedor;
					if((this.usuarios[0].getNumero + this.usuarios[1].getNumero) % 2 == 0)
					{
						if(this.usuarios[0].getEscolha() == 'P')
							vencedor = this.usuarios[0].getNome();
						else
							vencedor = this.usuarios[1].getNome();
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
