import java.io.*;
import java.net.*;
import java.util.*;

public class Parceiro
{
    private Socket             conexao;
    private ObjectInputStream  receptor;
    private ObjectOutputStream transmissor;
    private String nome;
    private char escolha;
    private boolean escolher;
    private int numeroOponente;

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

    public Parceiro (Socket             conexao,
                     ObjectInputStream  receptor,
                     ObjectOutputStream transmissor,
                     String nome,
		     		 char escolha,
		     		 boolean escolher,
		     		 int numeroOponente)
                     throws Exception // se parametro nulos
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (receptor==null)
            throw new Exception ("Receptor ausente");

        if (transmissor==null)
            throw new Exception ("Transmissor ausente");

        if (nome==null)
            throw new Exception ("Nome ausente");

        if (escolha != 'P' && escolha != 'I' && escolha!= ' ')
			throw new Exception ("Escolha inv�lida");

        this.conexao        = conexao;
        this.receptor       = receptor;
        this.transmissor    = transmissor;
        this.nome           = nome;
		this.escolha        = escolha;
		this.escolher       = escolher;
		this.numeroOponente = numeroOponente;
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

	public char getEscolha()
	{
		return this.escolha;
	}

	public void setEscolha(char escolha) throws Exception
	{
		if (escolha != 'P' && escolha != 'I' && escolha!= ' ')
				throw new Exception ("Escolha inv�lida");
		 this.escolha = escolha;
	}

	public void setEscolher(boolean escolher)
	{
         this.escolher = escolher;
	}

	public boolean getEscolher()
	{
		return this.escolher;
	}

	public void setNumeroOponente(int numeroOponente)
	{
		 this.numeroOponente = numeroOponente;
	}

	public int getNumeroOponente()
	{
		return this.numeroOponente;
	}

    public void receba (Comunicado x) throws Exception
    {
        try
        {

		System.out.println("bbbb");
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();

		System.out.println("cccc");
        }
        catch (IOException erro)
        {
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
		  Produz e retorna uma c�pia da inst�ncia this de Parceiro.
		  @return a c�pia do this
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
		  Gera um String com toda a informa��o presente na classe Parceiro.
		  � feito um String que recebe todos os valores de atributos da classe.
		  @return um String com todos os dados.
		 */
		public String toString()
		{
			String string = "nome:"+nome+"\n escolha:"+escolha+"\n numero do oponente:"+numeroOponente;
			return string;
		}

		/**
	     Calula e retorna o hashcode da inst�ncia do tipo Parceiro.
	     Calcula o hashcode da classe Parceiro representada pela inst�ncia � qual o m�todo for aplicado.
	     @return o hashcode de quem chamou o m�todo
	    */
		public int hashCode()
		{
			int ret = 1;
			ret = 3 * ret + this.nome.hashCode();
			ret = 3 * ret + conexao.hashCode();
			ret = 3 * ret + receptor.hashCode();
			ret = 3 * ret + transmissor.hashCode();
			ret = 3 * ret + new Character(this.escolha).hashCode();
			ret = 3 * ret + new Boolean(this.escolher).hashCode();
			ret = 3 * ret + new Integer(this.numeroOponente).hashCode();

			return ret;
		}

		/**
		 Verifica se duas inst�ncias de Parceiro s�o iguais.
	     Verifica se o Object fornecido como par�metro � a mesma Parceiro da inst�ncia, resultando true em caso afirmativo,
	     ou false, caso n�o forem iguais.
	     @param  obj o objeto que vai ser comparado com a inst�ncia
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

			if(this.escolha != par.escolha || this.escolher != par.escolher || this.numeroOponente != par.numeroOponente || this.nome != par.nome)
				return false;

			if(!this.conexao.equals(par.conexao) || !this.receptor.equals(par.receptor) || !this.transmissor.equals(par.transmissor))
				return false;

			return true;
		}

		/**
	     Constroi uma c�pia da inst�ncia da classe Parceiro.
	     Deve ser passado no parametro uma inst�ncia de Parceiro para ser
		 usada como modelo para criar uma nova.
	     @param par inst�ncia de Parceiro que ser� usada como molde.
	     @throws Exception caso o molde for nulo.
	    */
		public Parceiro (Parceiro par) throws Exception
		{
			if(par == null)
				throw new Exception();

			this.escolha = par.escolha;
			this.escolher = par.escolher;
			this.numeroOponente = par.numeroOponente;
			this.nome = par.nome;
			this.conexao = (Socket)par.conexao;
			this.receptor = (ObjectInputStream)par.receptor;
			this.transmissor = (ObjectOutputStream)par.transmissor;
	}
}
