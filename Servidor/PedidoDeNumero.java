/**A classe PedidoDeNumero herda de Comunicado e serve para
indicar ao servidor o pedido de um valor que o cliente colocou no jogo.
Nela encontramos o construtor e um getter de número.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.*/
public class PedidoDeNumero extends Comunicado
{
	/**Armazena o número do jogador em um int*/
    private int numeroJogador;

	/**
	Constroi uma nova instância da classe PedidoDeNumero.
	Para tanto, deve ser fornecido um int que será atribuido à
	variavel numeroJogador.
	@param numeroJogador o int que será atribuido.
	*/
    public PedidoDeNumero (int numeroJogador)
    {
        this.numeroJogador = numeroJogador;
    }

	 /**
		Retorna atributo numeroJogador
	    Retorna o atributo numeroJogador da instância a qual este método for aplicado.
		@return o int numeroJogador
	*/
    public int getNumeroJogador()
    {
        return this.numeroJogador;
    }

    /**
	 Método que retorna o hash code da instância da classe
	 Calcula o hashcode do numeroJogador representada pela instância a qual o método for aplicado.
	 @return o hashcode de quem chamou o método
	 */
		public int hashCode()
		{
			int ret = 1;
			ret = 3 * ret + new Integer (this.numeroJogador).hashCode();
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

			PedidoDeNumero ped = (PedidoDeNumero)obj;

			if(this.numeroJogador != ped.numeroJogador)
				return false;

			return true;
		}

	  /**
	  Gera um String com toda a informação presente na classe PedidoDeNumero.
	  É feito um String que recebe o int numeroJogador.
	  @return um String com a escolha.
	  */
		public String toString()
		{
			String saida = ""+numeroJogador;
			return saida;
		}
}
