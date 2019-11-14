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
