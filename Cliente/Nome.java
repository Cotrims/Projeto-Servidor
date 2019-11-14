/**
A classe Nome é uma herança de Comunicado que serve para
passar o atributo nome entre o servidor e os clientes, atraves do
instancia de Nome.
Essa classe possui o construtor e um getter.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.
*/
public class Nome extends Comunicado
{
	/**Armazena o nome*/
    private String nome;

    /**
    Constroi uma nova instância da classe Nome.
    Para tanto, deve ser fornecido uma String que será atribuido à
    variavel nome.
    @param nome a String que será atribuido.
    @throws Exception se a String for nula.
    */
    public Nome (String nome)throws Exception
    {
		if(nome == null)
			throw new Exception("Nome inválido");
        this.nome = nome;
    }

   /**
	   Retorna atributo nome
       Retorna o atributo nome da instância à qual este método for aplicado.
	   @return a String nome
	*/
    public String getNome()
    {
        return this.nome;
    }

	 /**
	 Método que retorna o hash code da instância da classe
	 Calcula o hashcode da escolha representada pela instância à qual o método for aplicado.
	 @return o hashcode de quem chamou o método
	 */
		public int hashCode()
		{
			int ret = 1;
			ret = 3 * ret + this.nome.hashCode();

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

			Nome nom =(Nome)obj;

			if(this.nome != nom.nome)
				return false;

			return true;
		}

	  /**
	  Gera um String com toda a informação presente na classe Nome.
	  @return um String com o nome.
	  */
		public String toString()
		{
			return nome;
		}

}
