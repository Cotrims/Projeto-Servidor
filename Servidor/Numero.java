/**
 * A classe Numero � uma heran�a de Comunicado que serve para passar o atributo
 * numero entre o servidor e os clientes, atraves do instancia de Numero. Essa
 * classe possui o construtor e um getter.
 * 
 * @author Giovanna Pavani Martelli.
 * @author Maria Luiza Sperancin Mancebo.
 * @author Rodrigo Smith Rodrigues.
 * @author Vin�cius Martins Cotrim.
 * @since 2019.
 */
public class Numero extends Comunicado {
	/** Armazena um numero int */
	private int numero;

	/**
	 * Constroi uma nova inst�ncia da classe Numero. Para tanto, deve ser fornecido
	 * um int que ser� atribuido � variavel numero.
	 * 
	 * @param numero o int que ser� atribuido.
	 */
	public Numero(int numero) {
		this.numero = numero;
	}

	/**
	 * Retorna atributo numero Retorna o atributo numero da inst�ncia � qual este
	 * m�todo for aplicado.
	 * 
	 * @return o int numero
	 */
	public int getNumero() {
		return this.numero;
	}

	/**
	 * M�todo que retorna o hash code da inst�ncia da classe Calcula o hashcode da
	 * escolha representada pela inst�ncia � qual o m�todo for aplicado.
	 * 
	 * @return o hashcode de quem chamou o m�todo
	 */
	public int hashCode() {
		int ret = 1;
		ret = 3 * ret + new Integer(this.numero).hashCode();

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

		Numero num = (Numero) obj;

		if (this.numero != num.numero)
			return false;

		return true;
	}

	/**
	 * Gera um String com toda a informa��o presente na classe Numero. � feito um
	 * String que recebe o int numero
	 * 
	 * @return um String com o numero.
	 */
	public String toString() {
		String saida = "" + numero;
		return saida;
	}

}
