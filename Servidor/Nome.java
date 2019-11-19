/**
 * A classe Nome � uma heran�a de Comunicado que serve para passar o atributo
 * nome entre o servidor e os clientes, atraves do instancia de Nome. Essa
 * classe possui o construtor e um getter.
 * 
 * @author Giovanna Pavani Martelli.
 * @author Maria Luiza Sperancin Mancebo.
 * @author Rodrigo Smith Rodrigues.
 * @author Vin�cius Martins Cotrim.
 * @since 2019.
 */
public class Nome extends Comunicado {
	/** Armazena o nome */
	private String nome;

	/**
	 * Constroi uma nova inst�ncia da classe Nome. Para tanto, deve ser fornecido
	 * uma String que ser� atribuido � variavel nome.
	 * 
	 * @param nome a String que ser� atribuido.
	 * @throws Exception se a String for nula.
	 */
	public Nome(String nome) throws Exception {
		if (nome == null)
			throw new Exception("Nome inv�lido");
		this.nome = nome;
	}

	/**
	 * Retorna atributo nome Retorna o atributo nome da inst�ncia � qual este m�todo
	 * for aplicado.
	 * 
	 * @return a String nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * M�todo que retorna o hash code da inst�ncia da classe Calcula o hashcode da
	 * escolha representada pela inst�ncia � qual o m�todo for aplicado.
	 * 
	 * @return o hashcode de quem chamou o m�todo
	 */
	public int hashCode() {
		int ret = 1;
		ret = 3 * ret + this.nome.hashCode();

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

		Nome nom = (Nome) obj;

		if (this.nome != nom.nome)
			return false;

		return true;
	}

	/**
	 * Gera um String com toda a informa��o presente na classe Nome.
	 * 
	 * @return um String com o nome.
	 */
	public String toString() {
		return nome;
	}

}
