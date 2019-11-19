/**
 * A classe PedidoDeNumero herda de Comunicado e serve para indicar ao servidor
 * o pedido de um valor que o cliente colocou no jogo. Nela encontramos o
 * construtor e um getter de n�mero.
 * 
 * @author Giovanna Pavani Martelli.
 * @author Maria Luiza Sperancin Mancebo.
 * @author Rodrigo Smith Rodrigues.
 * @author Vin�cius Martins Cotrim.
 * @since 2019.
 */
public class PedidoDeNumero extends Comunicado {
	/** Armazena o n�mero do jogador em um int */
	private int numeroJogador;

	/**
	 * Constroi uma nova inst�ncia da classe PedidoDeNumero. Para tanto, deve ser
	 * fornecido um int que ser� atribuido � variavel numeroJogador.
	 * 
	 * @param numeroJogador o int que ser� atribuido.
	 */
	public PedidoDeNumero(int numeroJogador) {
		this.numeroJogador = numeroJogador;
	}

	/**
	 * Retorna atributo numeroJogador Retorna o atributo numeroJogador da inst�ncia
	 * a qual este m�todo for aplicado.
	 * 
	 * @return o int numeroJogador
	 */
	public int getNumeroJogador() {
		return this.numeroJogador;
	}

	/**
	 * M�todo que retorna o hash code da inst�ncia da classe Calcula o hashcode do
	 * numeroJogador representada pela inst�ncia a qual o m�todo for aplicado.
	 * 
	 * @return o hashcode de quem chamou o m�todo
	 */
	public int hashCode() {
		int ret = 1;
		ret = 3 * ret + new Integer(this.numeroJogador).hashCode();
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

		PedidoDeNumero ped = (PedidoDeNumero) obj;

		if (this.numeroJogador != ped.numeroJogador)
			return false;

		return true;
	}

	/**
	 * Gera um String com toda a informa��o presente na classe PedidoDeNumero. �
	 * feito um String que recebe o int numeroJogador.
	 * 
	 * @return um String com a escolha.
	 */
	public String toString() {
		String saida = "" + numeroJogador;
		return saida;
	}
}
