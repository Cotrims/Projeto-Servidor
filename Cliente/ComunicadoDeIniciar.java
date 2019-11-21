/**A classe ComunicadoDeIniciar herda de Comunicado e serve para
 * indicar ao servidor que ele ira iniciar.
 * Nela encontramos o construtor e um getter.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinicius Martins Cotrim.
@since 2019.
*/
public class ComunicadoDeIniciar extends Comunicado
{
	/**
	 * Armazena se pode iniciar ou nao em um boolean
	 */
	private boolean iniciar;

	/** 
	 * Constroi uma nova instancia da classe ComunicadoDeIniciar.
	  Para tanto, deve ser fornecido um boolean que sera atribuido a
      variavel iniciar.
	  @param iniciar diz se pode ou nao iniciar.
     */
	public ComunicadoDeIniciar(boolean iniciar)
	{
		this.iniciar = iniciar;
	}

	/**
	 * Retorna atributo iniciar.
	   Retorna o atributo iniciar da instancia a qual este metodo for aplicado.
	   @return o boolean iniciar.
	 */
	public boolean getIniciar()
	{
		return this.iniciar;
	}

	/** 
	 * Metodo que retorna o hash code da instancia da classe.
	   Calcula o hashcode da escolha representada pela instancia a qual o metodo for aplicado.
	   @return o hashcode de quem chamou o metodo
	 */
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + new Boolean (this.iniciar).hashCode();

		return ret;
	}


	/**
	 * Metodo que retorna se o this eh igual ao parametro obj do tipo objeto.
	   Verifica se o Object fornecido como parametro eh o mesmo da instancia, 
	   resultando true em caso afirmativo, ou false, caso nao forem iguais.
	   @param obj do tipo Object com o qual this sere comparado.
	   @return boolean se this eh igual a obj.
	 */
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		ComunicadoDeIniciar com = (ComunicadoDeIniciar)obj;

		if(this.iniciar != com.iniciar)
			return false;

		return true;
	}

	/**
	 * Gera um String de acordo com o atributo iniciar se pode ou n�o iniciar.
	   @return um String de pode ou n�o pode.
	 */
	public String toString()
	{
		if(this.iniciar == true)
			return "pode iniciar";
		else
			return "nao pode iniciar";
	}
}