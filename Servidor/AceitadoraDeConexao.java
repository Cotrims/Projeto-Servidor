import java.net.*;
import java.util.*;

/**A classe AceitadoraDeConexao � respons�vel por aceitar as conex�es.
A classe AceitadoraDeConexao verifica se a porta passada est� liberada para uso
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vin�cius Martins Cotrim.
@since 2019.*/
public class AceitadoraDeConexao extends Thread
{
    private static final int PORTA_PADRAO = 3000;

    private ServerSocket pedido;
    private ArrayList<Parceiro> usuarios;

	/**Constr�i uma nova inst�ncia da classe AceitadoraDeConexao.
	@param escolha Porta desejada para o servidor estabelecer uma conex�o. Se for nula recebe a porta padr�o (3000)
	@param usuarios ArrayList contendo os usu�rios presentes na conex�o.
	@throws Exception � lan�ada uma exce��o se a porta for inv�lida ou "usuarios" for nulo.*/
    public AceitadoraDeConexao (String escolha, ArrayList<Parceiro> usuarios) throws Exception
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

	/**Ponto inicial da thread. � executado em segundo plano.
	O run da AceitadoraDeConexao � respons�vel por verificar se h� novos usu�rios, por meio do
	m�todo accept(), de Socket. Ao entrar um novo usu�rio, uma SupervisoraDeConexao � instanciada e passa
	a verificar o usu�rio enquanto ele est� ativo.*/
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
