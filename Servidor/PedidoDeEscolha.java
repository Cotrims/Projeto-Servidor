public class PedidoDeEscolha extends Comunicado
{
    private char escolha;

    public PedidoDeEscolha(char escolha)
    {
        this.escolha = escolha;
    }

    public int getEscolha ()
    {
        return this.escolha;
    }
}
