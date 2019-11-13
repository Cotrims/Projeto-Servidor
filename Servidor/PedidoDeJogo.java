public class PedidoDeJogo extends Comunicado
{
    private char escolha;

    public PedidoDeJogo (char escolha)
    {
        this.escolha = escolha;
    }

    public double getEscolha ()
    {
        return this.escolha;
    }
}
