/**A classe ComunicadoDeIniciar herda de Comunicado e serve para
indicar ao servidor que ele irá iniciar.
Nela encontramos o construtor e um getter.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.*/
public class ComunicadoDeIniciar extends Comunicado
{
	/**Armazena se pode iniciar ou não em um boolean*/
	private boolean iniciar;

	  /**
	    Constroi uma nova instância da classe ComunicadoDeIniciar.
	    Para tanto, deve ser fornecido um boolean que será atribuido à
	    variavel iniciar.
	    @param iniciar diz se pode ou não iniciar.
    */
	public ComunicadoDeIniciar(boolean iniciar)
	{
		this.iniciar = iniciar;
	}

	/**
	Retorna atributo iniciar
    Retorna o atributo iniciar da instância à qual este método for aplicado.
	@return o boolean iniciar
	*/
	public boolean getIniciar()
	{
		return this.iniciar;
	}

	 /**
	     Método que retorna o hash code da instância da classe
	     Calcula o hashcode da escolha representada pela instância à qual o método for aplicado.
	     @return o hashcode de quem chamou o método
	     */
			public int hashCode()
			{
				int ret = 1;
				ret = 3 * ret + new Boolean (this.iniciar).hashCode();

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

				ComunicadoDeIniciar com = (ComunicadoDeIniciar)obj;

				if(this.iniciar != com.iniciar)
					return false;

				return true;
			}

		  /**
		  Gera um String de acordo com o atributo iniciar se pode ou não iniciar.
		  @return um String de pode ou não pode.
		  */
			public String toString()
			{
				if(this.iniciar == true)
				return "pode iniciar";
				else
				return "não pode iniciar";
		}
}