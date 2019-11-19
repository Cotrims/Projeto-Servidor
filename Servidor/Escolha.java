/**
 * A classe Escolha � uma heran�a de Comunicado que serve para passar o atributo
 * escolha entre o servidor e os clientes, atraves do instancia de Escolha. Essa
 * classe possui o construtor e um getter.
 * 
 * @author Giovanna Pavani Martelli.
 * @author Maria Luiza Sperancin Mancebo.
 * @author Rodrigo Smith Rodrigues.
 * @author Vin�cius Martins Cotrim.
 * @since 2019.
 */
public class Escolha extends Comunicado {
	/** Armazena a escolha em um char */
	private char escolha;

	/**
	 * Constroi uma nova inst�ncia da classe Escolha. Para tanto, deve ser fornecido
	 * um char que ser� atribuido � variavel escolha.
	 * 
	 * @param escolha o char que ser� atribuido.
	 * @throws Exception se o char enviado for diferente de 'P','I' ou ' '.
	 */
	public Escolha(char escolha) throws Exception {
		if (escolha != 'P' && escolha != 'I' && escolha != ' ')
			throw new Exception("Escolha inv�lida");
		this.escolha = escolha;
	}

	/**
	 * Retorna atributo escolha Retorna o atributo escolha da inst�ncia � qual este
	 * m�todo for aplicado.
	 * 
	 * @return o char escolha
	 */
	public char getEscolha() {
		return this.escolha;
	}

	/**
	 * M�todo que retorna o hash code da inst�ncia da classe Calcula o hashcode da
	 * escolha representada pela inst�ncia � qual o m�todo for aplicado.
	 * 
	 * @return o hashcode de quem chamou o m�todo
	 */
	public int hashCode() {
		int ret = 1;
		ret = 3 * ret + new Character(this.escolha).hashCode();

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

		Escolha esc = (Escolha) obj;

		if (this.escolha != esc.escolha)
			return false;

		return true;
	}

	/**
	 * Gera um String com toda a informa��o presente na classe Escolha. � feito um
	 * String que recebe o char escolha.
	 * 
	 * @return um String com a escolha.
	 */
	public String toString() {
		String saida = "" + escolha;
		return saida;
	}

}
