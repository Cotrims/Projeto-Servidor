/**
 * A classe ComunicadoDeIniciar herda de Comunicado e serve para indicar ao
 * servidor que ele ir� iniciar. Nela encontramos o construtor e um getter.
 * 
 * @author Giovanna Pavani Martelli.
 * @author Maria Luiza Sperancin Mancebo.
 * @author Rodrigo Smith Rodrigues.
 * @author Vin�cius Martins Cotrim.
 * @since 2019.
 */
public class ComunicadoDeIniciar extends Comunicado {
	/** Armazena se pode iniciar ou n�o em um boolean */
	private boolean iniciar;

	/**
	 * Constroi uma nova inst�ncia da classe ComunicadoDeIniciar. Para tanto, deve
	 * ser fornecido um boolean que ser� atribuido � variavel iniciar.
	 * 
	 * @param iniciar diz se pode ou n�o iniciar.
	 */
	public ComunicadoDeIniciar(boolean iniciar) {
		this.iniciar = iniciar;
	}

	/**
	 * Retorna atributo iniciar Retorna o atributo iniciar da inst�ncia � qual este
	 * m�todo for aplicado.
	 * 
	 * @return o boolean iniciar
	 */
	public boolean getIniciar() {
		return this.iniciar;
	}

	/**
	 * M�todo que retorna o hash code da inst�ncia da classe Calcula o hashcode da
	 * escolha representada pela inst�ncia � qual o m�todo for aplicado.
	 * 
	 * @return o hashcode de quem chamou o m�todo
	 */
	public int hashCode() {
		int ret = 1;
		ret = 3 * ret + new Boolean(this.iniciar).hashCode();

		return ret;
	}

	/**
	 * M�todo que retorna se o this � igual ao parametro obj do tipo objeto Verifica
	 * se o Object fornecido como par�metro � o mesmo da inst�ncia, resultando true
	 * em caso afirmativo, ou false, caso n�o forem iguais.
	 * 
	 * @param obj do tipo Object � o objeto com o qual this ser� comparado
	 * @return boolean se this � igual a obj
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		ComunicadoDeIniciar com = (ComunicadoDeIniciar) obj;

		if (this.iniciar != com.iniciar)
			return false;

		return true;
	}

	/**
	 * Gera um String de acordo com o atributo iniciar se pode ou n�o iniciar.
	 * 
	 * @return um String de pode ou n�o pode.
	 */
	public String toString() {
		if (this.iniciar == true)
			return "pode iniciar";
		else
			return "n�o pode iniciar";
	}
}