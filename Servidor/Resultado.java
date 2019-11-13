public class Resultado extends Comunicado
{
    private String vencedor;

    public Resultado (String vencedor)
    {
        this.vencedor = vencedor;
    }

    public double getVencedor ()
    {
        return this.vencedor;
    }
}
