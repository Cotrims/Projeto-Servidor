/**
A classe Resultado � uma heran�a de Comunicado que serve para
passar o atributo vencedor entre o servidor e os clientes, atrav�s do
instancia de Resultado.
Essa classe possui o construtor e um getter.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vin�cius Martins Cotrim.
@since 2019.
*/
public class Resultado extends Comunicado
{
	/**Armazena o vencedor em um String*/
    private String vencedor;

	 /**
	    Constroi uma nova inst�ncia da classe Resultado.
	    Para tanto, deve ser fornecido uma String que ser� atribuido �
	    variavel vencedor.
	    @param vencedor a String que ser� atribuido.
    */
    public Resultado (String vencedor)
    {
        this.vencedor = vencedor;
    }

	/**
		Retorna atributo vencedor
	    Retorna o atributo vencedor da inst�ncia a qual este m�todo for aplicado.
		@return a String vencedor
	*/
    public String getVencedor ()
    {
        return this.vencedor;
    }

    /**
	 M�todo que retorna o hash code da inst�ncia da classe
	 Calcula o hashcode do vencedor representado pela inst�ncia a qual o m�todo for aplicado.
	 @return o hashcode de quem chamou o m�todo
	 */
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + this.vencedor.hashCode();
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

		Resultado res = (Resultado)obj;

		if(this.vencedor != res.vencedor)
			return false;

		return true;
	}

	/**
	Gera um String com toda a informa��o presente na classe Escolha.
	� feito um String que recebe o char escolha.
	@return um String com a escolha.
	*/
	public String toString()
	{
		return vencedor;
	}
}
