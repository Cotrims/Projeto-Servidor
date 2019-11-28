import java.io.*;
import java.net.*;

/**A classe Parceiro pode representar um usuário ou o servidor.
Possui os parâmetros necessários para tal representação, tal como o receptor e o transmissor
de objetos, a conexão e dados pessoais dos usuários, como nome, numero etc.
@author Giovanna Pavani Martelli.
@author Maria Luiza Sperancin Mancebo.
@author Rodrigo Smith Rodrigues.
@author Vinícius Martins Cotrim.
@since 2019.*/
public class Parceiro
{
    private Socket conexao; //Socket: classe responsável pelas conexões em si
    private ObjectInputStream  receptor;
    private ObjectOutputStream transmissor;
    private String nome;
    private char tipo;
	private int numero;
	private boolean escolhedor;

	/**Constrói uma nova instância da classe Parceiro.
	@param conexao Conexão estabelecida entre o usuário e o servidor.
	@param receptor Receptor das mensagens transmitidas.
	@param transmissor Transmissor das mensagens transmitidas.
	@throws Exception É lançada uma exceção se algum dos parâmetros for nulo.*/
	public Parceiro (Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception // se parametro nulos
	{
		if (conexao==null)
			throw new Exception ("Conexao ausente");

		if (receptor==null)
			throw new Exception ("Receptor ausente");

		if (transmissor==null)
			throw new Exception ("Transmissor ausente");

		this.conexao = conexao;
		this.receptor = receptor;
		this.transmissor = transmissor;
    }

    //GETTERs

	/**Retrorna o nome do jogador.
	@return Retorna uma String que representa o nome do jogador*/
	public String getNome()
	{
		return this.nome;
	}

	/**Retrorna o tipo do número.
	@return Retorna um char que representa o tipo do número: I ou P*/
	public char getTipo()
	{
		return this.tipo;
	}

	/**Retrorna o numero escolhido.
	@return Retorna um inteiro que representa o número escolhido pelo jogador.*/
	public int getNumero()
	{
		return this.numero;
	}

	/**Retrorna o escolhedor.
	@return Retorna um boolean que representa o escolhedor.*/
	public boolean getEscolhedor()
	{
		return this.escolhedor;
	}

	//SETTERs

	/**Seta um novo nome para o jogador.
	@param nome Nome do jogador.
	@throws Exception É lançada uma exceção se o nome for nulo*/
	public void setNome(String nome)throws Exception
	{
		 if (nome==null)
			throw new Exception ("Nome ausente");
		 this.nome = nome;
	}

	/**Seta um novo tipo de numero.
	@param tipo Char que representa o tipo do número: I ou P.
	@throws Exception É lançada uma exceção se o tipo for nulo*/
	public void setTipo(char tipo) throws Exception
	{
		if (tipo != 'P' && tipo != 'I')
				throw new Exception ("Tipo invalido");
		 this.tipo = tipo;
	}

	/**Seta um novo numero para o jogador.
	@param numero Numero escolhido do jogador.*/
	public void setNumero(int numero)
	{
		 this.numero = numero;
	}

	/**Seta um novo escolhedor.
	@param esc Escolhedor do tipo.*/
	public void setEscolhedor(boolean esc)
	{
		this.escolhedor = esc;
	}

	/**Método responsável por enviar alguma coisa ao outro lado da conexão.
	@param x Comunicado que o usuario ou a conexao vai receber
	@throws Exception É lançada uma exceção se houver erro na transmissão do comunicado.*/
    public void receba (Comunicado x) throws Exception
    {
        try
        {
            this.transmissor.writeObject (x);
            this.transmissor.flush ();
        }
        catch (IOException erro)
        {
			erro.printStackTrace();
            throw new Exception ("Erro de transmissao");
        }
    }

	/**Método responsável por receber alguma coisa do outro lado da conexão.
	@return Comunicado que o usuario ou a conexao vai receber.
	@throws Exception É lançada uma exceção se houver erro na recepção do comunicado.*/
    public Comunicado envie () throws Exception
    {
        try
        {
			return (Comunicado)this.receptor.readObject();
        }
        catch (Exception erro)
        {
			erro.printStackTrace();
            throw new Exception ("Erro de recepcao");
        }
    }

	/**Fecha a conexão, assim como o transmissor e o receptor.
	@throws Exception É lançada uma exceção se houver erro na desconexão.*/
    public void adeus () throws Exception //fecha as conexões
    {
        try
        {
            this.transmissor.close();
            this.receptor.close();
            this.conexao.close();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de desconexao");
        }
    }

	  /**
	  Clona Parceiro.
	  Produz e retorna uma copia da instancia this de Parceiro.
	  @return a copia do this
	 */
	public Object clone()
	{
		Parceiro ret = null;
		try
		{
			ret = new Parceiro(this);
		}
		catch(Exception erro)
		{}
		return ret;
	}

	/**
	  Gera um String com toda a informacao presente na classe Parceiro.
	  O feito um String que recebe todos os valores de atributos da classe.
	  @return um String com todos os dados.
	 */
	public String toString()
	{
		String string = "Nome: " + nome +
						"\nTipo: "+tipo+
						"\nNumero: "+numero+
						"\nEscolhedor: "+escolhedor+
						"\nTransmissor: " + transmissor.toString() +
						"\nReceptor: " + receptor.toString() +
						"\nConexao: " + conexao.toString();
		return string;
	}

	/**
	 Calula e retorna o hashcode da instancia do tipo Parceiro.
	 Calcula o hashcode da classe Parceiro representada pela instancia a qual o metodo for aplicado.
	 @return o hashcode de quem chamou o metodo
	*/
	public int hashCode()
	{
		int ret = 1;

		ret = 3 * ret + this.nome.hashCode();
		ret = 3 * ret + conexao.hashCode();
		ret = 3 * ret + receptor.hashCode();
		ret = 3 * ret + transmissor.hashCode();
		ret = 3 * ret + new Boolean(this.escolhedor).hashCode();
		ret = 3 * ret + new Character(this.tipo).hashCode();
		ret = 3 * ret + new Integer(this.numero).hashCode();

		return ret;
	}

	/**
	 Verifica se duas instancias de Parceiro sao iguais.
	 Verifica se o Object fornecido como parametro eh a mesma Parceiro da instancia, resultando true em caso afirmativo,
	 ou false, caso nao forem iguais.
	 @param  obj o objeto que vai ser comparado com a instancia
	 @return true caso forem iguais, e false caso forem diferentes
	*/
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;

		if(obj == null)
			return false;

		if(this.getClass() != obj.getClass())
			return false;

		Parceiro par = (Parceiro)obj;

		if(this.tipo != par.tipo || this.numero != par.numero || !this.nome.equals(par.nome) || this.escolhedor != par.escolhedor)
			return false;

		if(!this.conexao.equals(par.conexao) || !this.receptor.equals(par.receptor) || !this.transmissor.equals(par.transmissor))
			return false;

		return true;
	}

	/**
	 Constroi uma copia da instancia da classe Parceiro.
	 Deve ser passado no parametro uma instancia de Parceiro para ser
	 usada como modelo para criar uma nova.
	 @param par instancia de Parceiro que sera usada como molde.
	 @throws Exception caso o molde for nulo.
	*/
	public Parceiro (Parceiro par) throws Exception
	{
		if(par == null)
			throw new Exception();

		this.tipo = par.tipo;
		this.numero = par.numero;
		this.nome = par.nome;
		this.escolhedor = par.escolhedor;
		this.conexao = (Socket)par.conexao;
		this.receptor = (ObjectInputStream)par.receptor;
		this.transmissor = (ObjectOutputStream)par.transmissor;
	}
}
