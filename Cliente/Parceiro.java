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

    public Parceiro (Socket             conexao,
                     ObjectInputStream  receptor,
                     ObjectOutputStream transmissor,
                     String nome,
		     char escolha)
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

	if (escolha==null)
            throw new Exception ("Escolha ausente");

        this.conexao     = conexao;
        this.receptor    = receptor;
        this.transmissor = transmissor;
        this.nome        = nome;
	this.escolha     = escolha;
    }

	public String getNome()
	{
		return this.nome;
	}

	public void setNome(String nome)
	{
		 if (nome==null)
            throw new Exception ("Nome ausente");
         this.nome = nome;
	}

	public String getEscolha()
	{
		return this.escolha;
	}

	public void setEscolha(char escolha)
	{
	    if (escolha==null)
                throw new Exception ("Escolha ausente");
         this.escolha = escolha;
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