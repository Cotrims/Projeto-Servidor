public class ComunicadoIniciar extends Comunicado
{
	private boolean iniciar;
	public ComunicadoIniciar(boolean iniciar)
	{
		this.iniciar = iniciar;
	}

	public boolean getIniciar()
	{
		return this.iniciar;
	}
}