import java.net.*;
import java.util.*;

/**A classe AceitadoraDeConexao é responsável por aceitar as conexões.
A classe AceitadoraDeConexao verifica se a porta passada está liberada para uso
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.*/
public class AceitadoraDeConexao extends Thread
{
    private static final int PORTA_PADRAO = 3000;

    private ServerSocket pedido;
    private ArrayList<Parceiro> usuarios;

	/**Constrói uma nova instância da classe AceitadoraDeConexao.
	@param escolha Porta desejada para o servidor estabelecer uma conexão. Se for nula recebe a porta padrão (3000)
	@param usuarios ArrayList contendo os usuários presentes na conexão.
	@throws Exception É lançada uma exceção se a porta for inválida ou "usuarios" for nulo.*/
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

	/**Ponto inicial da thread. É executado em segundo plano.
	O run da AceitadoraDeConexao é responsável por verificar se há novos usuários, por meio do
	método accept(), de Socket. Ao entrar um novo usuário, uma SupervisoraDeConexao é instanciada e passa
	a verificar o usuário enquanto ele está ativo.*/
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
