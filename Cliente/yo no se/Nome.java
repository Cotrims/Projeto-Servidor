public class Nome extends Comunicado
{
    private String nome;

    public Nome (String nome)throws Exception
    {
		if(nome == null)
			throw new Exception("Nome inv�lido");
        this.nome = nome;
    }

    public String getNome()
    {
        return this.nome;
    }
}
