/**
A classe Resultado é uma herança de Comunicado que serve para
passar o atributo vencedor entre o servidor e os clientes, através do
instância de Resultado.
Essa classe possui o construtor e um getter.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.
*/
public class Resultado extends Comunicado
{
	/**Armazena o vencedor em um String*/
    private String vencedor;

	 /**
	    Constroi uma nova instância da classe Resultado.
	    Para tanto, deve ser fornecido uma String que será atribuido à
	    variavel vencedor.
	    @param vencedor a String que será atribuido.
    */
    public Resultado (String vencedor)
    {
        this.vencedor = vencedor;
    }

	/**
		Retorna atributo vencedor
	    Retorna o atributo vencedor da instância a qual este método for aplicado.
		@return a String vencedor
	*/
    public String getVencedor ()
    {
        return this.vencedor;
    }

    /**
	 Método que retorna o hash code da instância da classe
	 Calcula o hashcode do vencedor representado pela instância a qual o método for aplicado.
	 @return o hashcode de quem chamou o método
	 */
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + this.vencedor.hashCode();
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

		Resultado res = (Resultado)obj;

		if(this.vencedor != esc.vencedor)
			return false;

		return true;
	}

	/**
	Gera um String com toda a informação presente na classe Escolha.
	É feito um String que recebe o char escolha.
	@return um String com a escolha.
	*/
	public String toString()
	{
		return vencedor;
	}
}
