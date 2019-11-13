public class PedidoDeSubtracao extends Comunicado
{
    private double valorParaSubtrair;
    
    public PedidoDeSubtracao (double valorParaSubtrair)
    {
        this.valorParaSubtrair = valorParaSubtrair;
    }
    
    public double getValorParaSubtrair ()
    {
        return this.valorParaSubtrair;
    }
}
