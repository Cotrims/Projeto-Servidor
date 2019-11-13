public class PedidoDeMultiplicacao extends Comunicado
{
    private double valorParaMultiplicar;
    
    public PedidoDeMultiplicacao (double valorParaMultiplicar)
    {
        this.valorParaMultiplicar = valorParaMultiplicar;
    }
    
    public double getValorParaMultiplicar ()
    {
        return this.valorParaMultiplicar;
    }
}
