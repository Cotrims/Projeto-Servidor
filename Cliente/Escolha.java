public class Escolha extends Comunicado
{
    private char escolha;

    public Escolha (char escolha)throws Exception
    {
		//if(escolha == '') // se escolha for nula
		//	throw new Exception("Escolha inválida");
		if(escolha !='P' && escolha !='I' && escolha !=' ')
			throw new Exception("Escolha inválida");
        this.escolha = escolha;
    }

    public char getEscolha()
    {
        return this.escolha;
    }
}
