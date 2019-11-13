public class PedidoDeDivisao extends Comunicado
{
    private double valorParaDividir;
    
    public PedidoDeDivisao (double valorParaDividir)
    {
        this.valorParaDividir = valorParaDividir;
    }
    
    public double getValorParaDividir ()
    {
        return this.valorParaDividir;
    }
}
