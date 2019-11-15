/**
A classe PedidoDeEscolha herda de Comunicado e serve para
indicar ao servidor a escolha do cliente.
Nela encontramos o construtor e um getter.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.
*/
public class PedidoDeEscolha extends Comunicado
{
	/*/**Armazena a escolha em um char
    private char escolha;

    /**
    Constroi uma nova instância da classe PedidoEscolha.
    Para tanto, deve ser fornecido um char que será atribuido à
    variavel escolha.
    @param escolha o char que será atribuido.
    
    public PedidoDeEscolha(char escolha)
    {
        this.escolha = escolha;
    }

    /**
	Retorna atributo escolha
    Retorna o atributo escolha da instância à qual este método for aplicado.
	@return o char escolha
	
    public char getEscolha ()
    {
        return this.escolha;
    }


 /**
 Método que retorna o hash code da instância da classe
 Calcula o hashcode da escolha representada pela instância à qual o método for aplicado.
 @return o hashcode de quem chamou o método
 
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + new Character (this.escolha).hashCode();

		return ret;
	}


  /**
  Método que retorna se o this é igual ao parametro obj do tipo objeto
  Verifica se o Object fornecido como parâmetro é o mesmo da instância, resultando true em caso afirmativo,
  ou false, caso não forem iguais.
  @param obj do tipo Object é  o objeto com o qual this será comparado
  @return boolean se this é igual a obj
  
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		PedidoDeEscolha esc = (PedidoDeEscolha)obj;

		if(this.escolha != esc.escolha)
			return false;

		return true;
	}

  /**
  Gera um String com toda a informação presente na classe PedidoDeEscolha.
  É feito um String que recebe o char escolha.
  @return um String com a escolha.
  
	public String toString()
	{
		String saida = ""+escolha;
		return saida;
	}
*/

}
