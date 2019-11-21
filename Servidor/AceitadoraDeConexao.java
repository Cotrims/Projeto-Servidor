import java.net.*;
import java.util.*;

public class AceitadoraDeConexao extends Thread 
{
    private static final int PORTA_PADRAO = 3000;

    private ServerSocket pedido;
    private ArrayList<Parceiro> usuarios;

    public AceitadoraDeConexao
    (String escolha, ArrayList<Parceiro> usuarios) 
    throws Exception 
    {
        int porta = AceitadoraDeConexao.PORTA_PADRAO;

        if (escolha != null) 
        {
            porta = Integer.parseInt(escolha);
        }

        try 
        {
            this.pedido = new ServerSocket(porta);
        } 
        catch (Exception ex)
        {
            throw new Exception("Porta invalida");
        }

        if (usuarios == null)
            throw new Exception("Usuarios ausentes");

        this.usuarios = usuarios;
    }

    public void run() 
    {
        for (;;) 
        {
            Socket conexao = null;
            try 
            {
                conexao = this.pedido.accept();
                System.out.println("Foi o accept");
            } 
            catch (Exception erro) 
            {
                continue;
            }

            SupervisoraDeConexao supervisoraDeConexao = null;
            try 
            {
                supervisoraDeConexao = new SupervisoraDeConexao(conexao, usuarios);
            } 
            catch (Exception erro) 
            {} // sei que passei parametros corretos para o construtor
            supervisoraDeConexao.start();
        }
    }
}
