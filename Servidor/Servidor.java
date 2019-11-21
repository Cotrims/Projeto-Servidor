import java.util.*;

public class Servidor //programa de in�cio do servidor.
{
    public static void main(String[] args) //o vetor de String "args" recebe a porta que ser� utilizada.
    {
        if (args.length > 1) //se o usu�rio passar mais de um par�metro, ele iniciou o programa de forma errada.
        {
            System.err.println("Uso esperado: java Servidor [PORTA]\n");
            return; //o programa
        }

        String porta = null;

        if (args.length == 1)
            porta = args[0];

        ArrayList<Parceiro> usuarios = new ArrayList<Parceiro>();

        AceitadoraDeConexao aceitadoraDeConexao = null;
        try
        {
            aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
            aceitadoraDeConexao.start();
            System.out.println(aceitadoraDeConexao);
        }
        catch (Exception erro)
        {
            System.err.println("Escolha uma porta apropriada e liberada para uso!\n");
            return;
        }

        for (;;) {
            System.out.println("O servidor esta ativo! Para desativa-lo,");
            System.out.println("use o comando \"desativar\"\n");
            System.out.print("> ");

            String comando = null;
            try
            {
                comando = Teclado.getUmString();
            }
            catch (Exception erro)
            {}

            if (comando.toLowerCase().equals("desativar"))
            {
                synchronized (usuarios)
                {
                    for (Parceiro usuario : usuarios)
                    {
                        ComunicadoDeDesligamento comunicadoDeDesligamento = new ComunicadoDeDesligamento();

                        try
                        {
                            usuario.receba(comunicadoDeDesligamento);
                            usuario.adeus();
                        }
                        catch (Exception erro)
                        {}
                    }
                }
                System.out.println("O servidor foi desativado!\n");
                System.exit(0);
            }
            else
                System.err.println("Comando invalido!\n");
        }
    }
}