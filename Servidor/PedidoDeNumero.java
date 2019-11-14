/**A classe PedidoDeNumero herda de Comunicado e serve para
indicar ao servidor o pedido de um valor que o cliente colocou no jogo.
Nela encontramos o construtor e um getter de número.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.*/
public class PedidoDeNumero extends Comunicado
{
    private int numeroJogador;

    public PedidoDeNumero (int numeroJogador)
    {
        this.numeroJogador = numeroJogador;
    }

    public int getNumeroJogador()
    {
        return this.numeroJogador;
    }
}
