/**
 * A classe PedidoDeTipo herda de Comunicado e serve para indicar ao servidor
 * o tipo do numero do cliente. //Nela encontramos o construtor e um getter.
 *
 * @author Giovanna Pavani Martelli.
 * @author Maria Luiza Sperancin Mancebo.
 * @author Rodrigo Smith Rodrigues.
 * @author Vinicius Martins Cotrim.
 * @since 2019.
 */
public class PedidoDeTipo extends Comunicado
{
    private Tipo tipo;

    public PedidoDeTipo(char tipo) throws Exception
    {
        try
        {
            this.tipo = new Tipo(tipo);
        }
        catch(Exception err)
        {
            System.out.println(err.getMessage());
        }
    }

    public Tipo getTipo()
    {
        return this.tipo;
    }
}
