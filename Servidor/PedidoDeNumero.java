public class PedidoDeNumero extends Comunicado
{
    private int numeroJogador1;

    public PedidoDeNumero (int numeroJogador1)
    {
        this.numeroJogador1 = numeroJogador1;
    }

    public int getNumeroJogador1 ()
    {
        return this.numeroJogador1;
    }
}
