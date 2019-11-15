/**
A classe PedidoDeEscolha herda de Comunicado e serve para
indicar ao servidor a escolha do cliente.
Nela encontramos o construtor e um getter.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vin�cius Martins Cotrim.
@since 2019.
*/
public class PedidoDeEscolha extends Comunicado
{
	/*/**Armazena a escolha em um char
    private char escolha;

    /**
    Constroi uma nova inst�ncia da classe PedidoEscolha.
    Para tanto, deve ser fornecido um char que ser� atribuido �
    variavel escolha.
    @param escolha o char que ser� atribuido.
    
    public PedidoDeEscolha(char escolha)
    {
        this.escolha = escolha;
    }

    /**
	Retorna atributo escolha
    Retorna o atributo escolha da inst�ncia � qual este m�todo for aplicado.
	@return o char escolha
	
    public char getEscolha ()
    {
        return this.escolha;
    }


 /**
 M�todo que retorna o hash code da inst�ncia da classe
 Calcula o hashcode da escolha representada pela inst�ncia � qual o m�todo for aplicado.
 @return o hashcode de quem chamou o m�todo
 
	public int hashCode()
	{
		int ret = 1;
		ret = 3 * ret + new Character (this.escolha).hashCode();

		return ret;
	}


  /**
  M�todo que retorna se o this � igual ao parametro obj do tipo objeto
  Verifica se o Object fornecido como par�metro � o mesmo da inst�ncia, resultando true em caso afirmativo,
  ou false, caso n�o forem iguais.
  @param obj do tipo Object �  o objeto com o qual this ser� comparado
  @return boolean se this � igual a obj
  
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
  Gera um String com toda a informa��o presente na classe PedidoDeEscolha.
  � feito um String que recebe o char escolha.
  @return um String com a escolha.
  
	public String toString()
	{
		String saida = ""+escolha;
		return saida;
	}
*/

}
