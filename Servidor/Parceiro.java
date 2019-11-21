import java.io.*;
import java.net.*;

public class Parceiro
{
    private Socket             conexao;
    private ObjectInputStream  receptor;
    private ObjectOutputStream transmissor;
    private String nome;
    private char tipo;
	private int numero;
	private boolean escolhedor;

	public Parceiro (Socket             conexao,
                     ObjectInputStream  receptor,
                     ObjectOutputStream transmissor)
                     throws Exception // se parametro nulos
	    {
	        if (conexao==null)
	            throw new Exception ("Conexao ausente");

	        if (receptor==null)
	            throw new Exception ("Receptor ausente");

	        if (transmissor==null)
	            throw new Exception ("Transmissor ausente");

	        this.conexao     = conexao;
	        this.receptor    = receptor;
	        this.transmissor = transmissor;
    }

	public String getNome()
	{
		return this.nome;
	}

	public void setNome(String nome)throws Exception
	{
		 if (nome==null)
            throw new Exception ("Nome ausente");
         this.nome = nome;
	}

	public char getTipo()
	{
		return this.tipo;
	}

	public void setTipo(char tipo) throws Exception
	{
		if (tipo != 'P' && tipo != 'I')
				throw new Exception ("Tipo invalido");
		 this.tipo = tipo;
	}

	public void setNumero(int numero)
	{
		 this.numero = numero;
	}

	public int getNumero()
	{
		return this.numero;
	}

	public void setEscolhedor(boolean esc)
	{
		this.escolhedor = esc;
	}

	public boolean getEscolhedor()
	{
		return this.escolhedor;
	}

    public void receba (Comunicado x) throws Exception
    {
        try
        {
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();
        }
        catch (IOException erro)
        {
			erro.printStackTrace();
            throw new Exception ("Erro de transmissao");
        }
    }

    public Comunicado envie () throws Exception
    {
        try
        {
			return (Comunicado)this.receptor.readObject();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
    }

    public void adeus () throws Exception
    {
        try
        {
            this.transmissor.close();
            this.receptor   .close();
            this.conexao    .close();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de desconexao");
        }
    }

          /**
		  Clona Parceiro
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
