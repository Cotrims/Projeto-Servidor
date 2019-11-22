/**
 * A classe Tipo e uma heranca de Comunicado que serve para passar o atributo
 * tipo entre o servidor e os clientes, atraves do instancia de Tipo. Essa
 * classe possui o construtor e um getter.
 *
 * @author Giovanna Pavani Martelli.
 * @author Maria Luiza Sperancin Mancebo.
 * @author Rodrigo Smith Rodrigues.
 * @author Vinicius Martins Cotrim.
 * @since 2019.
 */
public class Tipo extends Comunicado
 {
	/**
	 * Armazena a escolha em um char
	 */
	private char tipo;

	/**
	 * Constroi uma nova instancia da classe Tipo. Para tanto, deve ser fornecido
	 * um char que sera atribuido da variavel tipo.
	 *
	 * @param tipo o char que sera tribuido.
	 * @throws Exception se o char enviado for diferente de 'P','I'.
	 */
	public Tipo(char tipo) throws Exception
	{
		if (tipo != 'P' && tipo != 'I')
			throw new Exception("Escolha invalida");
		this.tipo = tipo;
	}

	/**
	 * Retorna atributo escolha tipo o atributo escolha da instancia a qual este
	 * metodo for aplicado.
	 *
	 * @return o char escolha
	 */
	public char getTipo()
	{
		return this.tipo;
	}

	/**
	 * Metodo que retorna o hash code da instancia da classe calcula o hashcode do
	 * tipo representada pela instancia o qual o metodo for aplicado.
	 *
	 * @return o hashcode de quem chamou o metodo
	 */
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + new Character(this.tipo).hashCode();

		return ret;
	}

	/**
	 * Metodo que retorna se o this eh igual ao parametro obj do tipo objeto Verifica
	 * se o Object fornecido como parametro eh o mesmo da instancia, resultando true
	 * em caso afirmativo, ou false, caso nao forem iguais.
	 *
	 * @param obj do tipo Object eh o objeto com o qual this sera comparado
	 * @return boolean se this eh igual a obj
	 */
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Tipo esc = (Tipo) obj;

		if (this.tipo != esc.tipo)
			return false;

		return true;
	}

	/**
	 * Gera um String com toda a informacao presente na classe Tipo. Eh feito um
	 * String que recebe o char escolha.
	 *
	 * @return um String com a escolha.
	 */
	public String toString()
	{
		String saida = "";

		if(tipo == 'P')
			saida = "Par";
		else
			saida = "Impar";

		return saida;
	}
}
