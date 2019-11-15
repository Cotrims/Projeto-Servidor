/**A classe PedidoDeNome herda de Comunicado e serve para
indicar ao servidor o pedido do nome de um cliente que se conectou.
Nela encontramos o construtor e um getter de nome.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.*/
public class PedidoDeNome extends Comunicado
{
	/**Armazena o nome do jogador em uma String*/
    private String nome;

	/**
	Constroi uma nova instância da classe PedidoDeNome.
	Para tanto, deve ser fornecido uma String que será atribuida à
	variavel nome.
	@param nome a String que será atribuida.
	*/
    public PedidoDeNome(String nome)
    {
        this.nome = nome;
    }

	/**
	Retorna atributo nome
	Retorna o atributo nome da instância a qual este método for aplicado.
	@return a String nome
	*/
    public String getNome ()
    {
        return this.nome;
    }

	/**
	 Método que retorna o hash code da instância da classe
	 Calcula o hashcode do nome representada pela instância a qual o método for aplicado.
	 @return o hashcode de quem chamou o método
	 */
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + new String(this.nome).hashCode();
		return ret;
	}


    /**
    Método que retorna se o this é igual ao parametro obj do tipo objeto
    Verifica se o Object fornecido como parâmetro é o mesmo da instância, resultando true em caso afirmativo,
    ou false, caso não forem iguais.
    @param obj do tipo Object é  o objeto com o qual this será comparado
    @return boolean se this é igual a obj
    */
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		PedidoDeNome ped = (PedidoDeNome)obj;

		if(this.nome != ped.nome)
			return false;

		return true;
	}

    /**
    Gera um String com toda a informação presente na classe PedidoDeNome.
    É feito um String que recebe a String nome.
    @return um String com o nome do Jogador.
     */
	public String toString()
	{
		return nome;
	}
}
