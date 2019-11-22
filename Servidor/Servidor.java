import java.util.*;

public class Servidor //programa de inicio do servidor.
{
    public static void main(String[] args) //o vetor de String "args" recebe a porta que sera utilizada.
    {
        if (args.length > 1) //se o usuário passar mais de um parametro, ele iniciou o programa de forma errada.
        {
            System.err.println("Uso esperado: java Servidor [PORTA]\n");
            return; //o programa para a execucao
        }

        String porta = null;

        if (args.length == 1) //se foi passado um parâmetro, a variavel porta o recebe.
            porta = args[0];

        ArrayList<Parceiro> usuarios = new ArrayList<Parceiro>(); //é declarado um ArrayList do tipo Parceiro.

        AceitadoraDeConexao aceitadoraDeConexao = null;

        try //a classe AceitadoraDeConexao tentara iniciar uma conexao na porta passada.
        {
            aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
            aceitadoraDeConexao.start();
            System.out.println(aceitadoraDeConexao);
        }
        catch (Exception erro) //caso não consiga, e passada uma mensagem para o usuario e o programa para.
        {
            System.err.println("Escolha uma porta apropriada e liberada para uso!\n");
            return;
        }

        for (;;) //verifica se o usuario deu o comando de desligamento do servidor
        {
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

            if (comando.toLowerCase().equals("desativar")) //se o usuário escreveu "desativar",
            {
                synchronized (usuarios)
                {
                    for (Parceiro usuario : usuarios) //para cada usuário...
                    {
                        ComunicadoDeDesligamento comunicadoDeDesligamento = new ComunicadoDeDesligamento();

                        try
                        {
                            usuario.receba(comunicadoDeDesligamento); //envia um comunicado de desligamento,
                            usuario.adeus(); //desligando as conexoes e fechando as portas.
                        }
                        catch (Exception erro)
                        {}
                    }
                }
                System.out.println("O servidor foi desativado!\n");
                System.exit(0); //fecha o cmd.
            }
            else
                System.err.println("Comando invalido!\n");
        }
    }
}