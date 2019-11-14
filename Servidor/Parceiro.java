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
    boolean escolher;
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

		//if (escolha=='')
         //   throw new Exception ("Escolha ausente");

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

	public void setNome(String nome) throws Exception
	{
		 if (nome==null)
            throw new Exception ("Nome ausente");
         this.nome = nome;
	}

	public char getEscolha()
	{
		return this.escolha;
	}

	public void setEscolha(char escolha)
	{
		//if (escolha=='')
			//	throw new Exception ("Escolha ausente");
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
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();
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
}