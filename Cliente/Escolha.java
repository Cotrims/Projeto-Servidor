public class Escolha extends Comunicado
{
    private char escolha;

    public Escolha (char escolha)throws Exception
    {
		//if(escolha == '') // se escolha for nula
		//	throw new Exception("Escolha inv�lida");
		if(escolha !='P' && escolha !='I' && escolha !=' ')
			throw new Exception("Escolha inv�lida");
        this.escolha = escolha;
    }

    public char getEscolha()
    {
        return this.escolha;
    }
}
