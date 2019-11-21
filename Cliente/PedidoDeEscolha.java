/**
A classe PedidoDeEscolha herda de Comunicado e serve para
indicar quem sera o parceiro que selecionara impar ou par.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinicius Martins Cotrim.
@since 2019.
*/
public class PedidoDeEscolha extends Comunicado
{   
    private boolean escolha;

    public PedidoDeEscolha(boolean esc)
    {
        this.escolha = esc;
    }

    public boolean getEscolha()
    {
        return this.escolha;
    }
}
