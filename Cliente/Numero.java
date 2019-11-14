/**
A classe Numero é uma herança de Comunicado que serve para
passar o atributo numero entre o servidor e os clientes, atraves do
instancia de Numero.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.
*/
public class Numero extends Comunicado
{
	/**Armazena um numero int*/
    private int numero;

    /**
    Constroi uma nova instância da classe Numero.
    Para tanto, deve ser fornecido um int que será atribuido à
    variavel numero.
    @param numero o int que será atribuido.
    */
    public Numero (int numero)
    {
        this.numero = numero;
    }

   /**
	Retorna atributo numero
    Retorna o atributo numero da instância à qual este método for aplicado.
	@return o int numero
	*/
    public int getNumero ()
    {
        return this.numero;
    }

    /**
	     Método que retorna o hash code da instância da classe
	     Calcula o hashcode da escolha representada pela instância à qual o método for aplicado.
	     @return o hashcode de quem chamou o método
	     */
			public int hashCode()
			{
				int ret = 1;
				ret = 3 * ret + new Integer (this.numero).hashCode();

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

				Numero num = (Numero)obj;

				if(this.numero != num.numero)
					return false;

				return true;
			}

		  /**
		  Gera um String com toda a informação presente na classe Numero.
		  É feito um String que recebe o int numero
		  @return um String com o numero.
		  */
		    public String toString()
			{
				String saida = ""+numero;
				return saida;
			}

}
