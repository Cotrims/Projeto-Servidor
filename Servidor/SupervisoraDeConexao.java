import java.io.*;
import java.net.*;
import java.util.*;

public class SupervisoraDeConexao extends Thread
{
    private Parceiro            usuario;
    private Socket              conexao;
    private ArrayList<Parceiro> usuarios;
    private static int qtdJogadores = 0;

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

		System.out.println("ddddd");
            synchronized (this.usuarios)
            {
                this.usuarios.add (this.usuario);
                this.qtdJogadores++;
                if(this.qtdJogadores == 2)
                	for(Parceiro jogador: this.usuarios)
                	{
						jogador.receba(new ComunicadoIniciar(true));
					} 
            }


            for(;;)
            {
				int escolhedor = -1;

				System.out.println("eeeeee");
                Comunicado comunicado = this.usuario.envie ();

                if(comunicado==null)
                    return;
                
                if (comunicado instanceof PedidoDeNome)
                {
                	PedidoDeNome comuni = (PedidoDeNome) comunicado;
                	String nome = comuni.getNome();
                    this.usuario.setNome(nome);
                    //this.usuario.receba (new Nome(Teclado.getUmString()));
					//this.usuario.setNome(Teclado.getUmString()); // ver onde q isso vai com o gitzel pq tem q passar pro cliente
                }
                else if (comunicado instanceof PedidoDeJogo)
                {
					escolhedor = new Random().nextInt(1);
					usuarios.get(escolhedor).setEscolher(true);
                }
                else if (comunicado instanceof PedidoDeEscolha) // tirar parametro
				{
					int ind;
					char esc;
					if(escolhedor == 0)
						ind = 1;
					else
						ind = 0;
					if(this.usuarios.get(escolhedor).getEscolha() == 'P')
						esc = 'I';
					else
						esc = 'P';
					this.usuarios.get(ind).setEscolha(esc);
                }
                else if (comunicado instanceof PedidoDeNumero)
				{
					PedidoDeNumero pedido = (PedidoDeNumero)comunicado;
					int numero = pedido.getNumeroJogador();
					this.usuario.setNumero(numero);
					if(this.usuarios.get(0).equals(this.usuario))
						this.usuarios.get(1).setNumeroOponente(numero);
					else
						this.usuarios.get(0).setNumeroOponente(numero);

                }
                else if (comunicado instanceof PedidoDeResultado)
                {
					String vencedor = this.quemGanhou();
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
    
    private String quemGanhou() 
    {
    	String vencedor="";
		if((this.usuarios.get(0).getNumero() + this.usuarios.get(1).getNumero()) % 2 == 0)
		{
			if(this.usuarios.get(0).getEscolha() == 'P')
				vencedor = this.usuarios.get(0).getNome();
			else
				vencedor = this.usuarios.get(1).getNome();
		}
		return vencedor;
    }
}
