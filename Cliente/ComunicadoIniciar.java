public class ComunicadoDeIniciar extends Comunicado
{
	private boolean iniciar;
	public ComunicadoDeIniciar(boolean iniciar)
	{
		this.iniciar = iniciar;
	}

	public boolean getIniciar()
	{
		return this.iniciar;
	}
}