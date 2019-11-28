import java.io.*;
import java.net.*;
import java.util.*;

/**A classe SupervisoraDeConexao é responsável por observar o que acontece em uma conexão usuario x servidor.
Ela verifica a todo instante o que está sendo passado, e dependendo de quais pedidos o usuário enviar,
é dada a resposta correta ao usuário.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.*/
public class SupervisoraDeConexao extends Thread
{
    private static final PedidoDeNome Comunicado = null;
    private Parceiro jogador;
    private ArrayList<Parceiro> jogadores;
    private Socket conexao;
    private static int numInseridos = 0;

	/**Constrói uma nova instância da classe SupervisoraDeConexao.
	@param conexao Conexao estabelecida entre o usuário e o servidor.
	@param jogadores ArrayList contendo os jogadores presentes na conexão.
	@throws Exception É lançada uma exceção se conexão ou jogadores for nulo.*/
    public SupervisoraDeConexao(Socket conexao, ArrayList<Parceiro> jogadores) throws Exception {
        if (conexao == null)
            throw new Exception("Conexao ausente");

        if (jogadores == null)
            throw new Exception("Usuarios ausentes");

        this.conexao = conexao;
        this.jogadores = jogadores;
    }

	/**Ponto inicial da thread. É executado em segundo plano.
	O run da SupervisoraDeConexao é responsável por verificar o que está passando entre o usuário que ela cuida
	e o servidor. Verifica os pedidos e envia as respostas necessárias.*/
    public void run()
    {
        ObjectInputStream receptor = null;
        try
        {
            receptor =
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
                receptor.close();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }

        try
        {
            this.jogador =
            new Parceiro(this.conexao,
                         receptor,
                         transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        try
        {

            synchronized (this.jogadores)
            {
                this.jogadores.add(this.jogador);
                if (this.jogadores.size() == 2)
                {
                    for (Parceiro jogador : this.jogadores)
                        jogador.receba(new ComunicadoDeIniciar(true));

                        int escolhedor = (int)(Math.random() * 2);
                        System.out.println(escolhedor);
                        this.jogadores.get(escolhedor).setEscolhedor(true);
                }
            }
            for (;;)
            {
                Comunicado comunicado = this.jogador.envie();

                if (comunicado == null)
                    return;

                if (comunicado instanceof PedidoDeNome)
                {
                    String nome = ((PedidoDeNome) comunicado).getNome();
                    this.jogador.setNome(nome);
                }
                else if (comunicado instanceof PedidoDeTipo)
                {
                    PedidoDeTipo pedido = (PedidoDeTipo)comunicado;
                    char tipo = pedido.getTipo().getTipo();
                    this.jogador.setTipo(tipo);

                    if(tipo == 'P' && this.jogadores.get(0) == this.jogador)
                        this.jogadores.get(1).setTipo('I');
                    else
                        this.jogadores.get(0).setTipo('I');

                    if(tipo == 'I' && this.jogadores.get(0) == this.jogador)
                        this.jogadores.get(1).setTipo('P');
                    else
                        this.jogadores.get(0).setTipo('P');

                    if(this.jogadores.get(0) == this.jogador)
                        this.jogadores.get(1).receba(new Tipo(this.jogadores.get(1).getTipo()));
                    else
                        this.jogadores.get(1).receba(new Tipo(this.jogadores.get(0).getTipo()));
                }
                else if (comunicado instanceof PedidoDeNumero)
                {
                    PedidoDeNumero pedido = (PedidoDeNumero) comunicado;
                    int numero = pedido.getNumeroJogador();
                    this.jogador.setNumero(numero);
                    numInseridos++;
                }
                else if (comunicado instanceof PedidoDeEscolha)
                {
                    jogador.receba(new PedidoDeEscolha(this.jogador.getEscolhedor()));
                }
                else if (comunicado instanceof PedidoParaSair)
                {
                    synchronized (this.jogadores)
                    {
                        this.jogadores.remove(this.jogador);
                    }
                    this.jogador.adeus();
                }

                if (numInseridos == 2)
				{
					for(Parceiro jogador : jogadores)
                    	jogador.receba(new Resultado(vencedor()));
                    numInseridos = 0;
				}
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close();
                receptor.close();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }
    }

	/**Método responsável por retornar uma String contendo o nome do
	vencedor do jogo.
	@return Retorna uma String contendo o nome do vencedor.*/
    private String vencedor()
    {
        String vencedor = "";
        double soma = this.jogadores.get(0).getNumero() + this.jogadores.get(1).getNumero();

        if ((this.jogadores.get(0).getTipo() == 'I' && soma % 2 != 0) || (this.jogadores.get(0).getTipo() == 'P' && soma % 2 == 0))
            vencedor = this.jogadores.get(0).getNome();
        else
            vencedor = this.jogadores.get(1).getNome();

        return vencedor;
    }
}
