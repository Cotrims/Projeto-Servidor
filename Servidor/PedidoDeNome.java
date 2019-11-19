/**A classe PedidoDeNome herda de Comunicado e serve para
indicar ao servidor o pedido do nome de um cliente que se conectou.
Nela encontramos o construtor e um getter de nome.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vin�cius Martins Cotrim.
@since 2019.*/
public class PedidoDeNome extends Comunicado
{
	/**Armazena o nome do jogador em uma String*/
    private String nome;

	/**
	Constroi uma nova inst�ncia da classe PedidoDeNome.
	Para tanto, deve ser fornecido uma String que ser� atribuida �
	variavel nome.
	@param nome a String que ser� atribuida.
	*/
    public PedidoDeNome(String nome)
    {
        this.nome = nome;
    }

	/**
	Retorna atributo nome
	Retorna o atributo nome da inst�ncia a qual este m�todo for aplicado.
	@return a String nome
	*/
    public String getNome ()
    {
        return this.nome;
    }

	/**
	 M�todo que retorna o hash code da inst�ncia da classe
	 Calcula o hashcode do nome representada pela inst�ncia a qual o m�todo for aplicado.
	 @return o hashcode de quem chamou o m�todo
	 */
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + new String(this.nome).hashCode();
		return ret;
	}


    /**
    M�todo que retorna se o this � igual ao parametro obj do tipo objeto
    Verifica se o Object fornecido como par�metro � o mesmo da inst�ncia, resultando true em caso afirmativo,
    ou false, caso n�o forem iguais.
    @param obj do tipo Object �  o objeto com o qual this ser� comparado
    @return boolean se this � igual a obj
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
    Gera um String com toda a informa��o presente na classe PedidoDeNome.
    � feito um String que recebe a String nome.
    @return um String com o nome do Jogador.
     */
	public String toString()
	{
		return nome;
	}
}
