import java.io.*;
import java.net.*;
import java.util.*;

public class SupervisoraDeConexao extends Thread
{
    private Parceiro            jogador;
    private Socket              conexao;
    private ArrayList<Parceiro> jogadores;
    private static int qtdJogadores = 0;

    public SupervisoraDeConexao
    (Socket conexao, ArrayList<Parceiro> jogadores)
    throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (jogadores==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.jogadores = jogadores;
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
            this.jogador =
            new Parceiro (this.conexao,
                          receptor,
                          transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        try
        {

		System.out.println("ddddd");
            synchronized (this.jogadores)
            {
                this.jogadores.add (this.jogador);
                this.qtdJogadores++;
                if(this.qtdJogadores == 2)
                	for(Parceiro jogador: this.jogadores)
                	{
						jogador.receba(new ComunicadoDeIniciar(true));
					} 
            }


            for(;;)
            {
				int escolhedor = -1;

				System.out.println("eeeeee");
                Comunicado comunicado = this.jogador.envie ();

                if(comunicado==null)
                    return;
                
                if (comunicado instanceof PedidoDeNome)
                {
                	PedidoDeNome pedido = (PedidoDeNome) comunicado;
                	String nome = pedido.getNome();
                    this.jogador.setNome(nome);
                    //this.usuario.receba (new Nome(Teclado.getUmString()));
					//this.usuario.setNome(Teclado.getUmString()); // ver onde q isso vai com o gitzel pq tem q passar pro cliente
                }
                else if (comunicado instanceof PedidoDeJogo)
                {
					escolhedor = new Random().nextInt(1);
					jogadores.get(escolhedor).setEscolher(true);
                }
                else if (comunicado instanceof PedidoDeEscolha) // tirar parametro
				{
					int ind;
					char esc;
					if(escolhedor == 0)
						ind = 1;
					else
						ind = 0;
					if(this.jogadores.get(escolhedor).getEscolha() == 'P')
						esc = 'I';
					else
						esc = 'P';
					this.jogadores.get(ind).setEscolha(esc);
                }
                else if (comunicado instanceof PedidoDeNumero)
				{
					PedidoDeNumero pedido = (PedidoDeNumero)comunicado;
					int numero = pedido.getNumeroJogador();
					this.jogador.setNumero(numero);
					if(this.jogadores.get(0).equals(this.jogador))
						this.jogadores.get(1).setNumeroOponente(numero);
					else
						this.jogadores.get(0).setNumeroOponente(numero);

                }
                else if (comunicado instanceof PedidoDeResultado)
                {
                    this.jogador.receba (new Resultado (vencedor()));
                }
                else if (comunicado instanceof PedidoParaSair)
                {
                    synchronized (this.jogadores)
                    {
                        this.jogadores.remove (this.jogador);
                    }
                    this.jogador.adeus();
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
    
    private String vencedor() 
    {
    	String vencedor="";
		if((this.jogadores.get(0).getNumero() + this.jogadores.get(1).getNumero()) % 2 == 0)
		{
			if(this.jogadores.get(0).getEscolha() == 'P')
				vencedor = this.jogadores.get(0).getNome();
			else
				vencedor = this.jogadores.get(1).getNome();
		}
		return vencedor;
    }
}
