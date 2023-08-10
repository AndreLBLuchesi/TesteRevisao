package controle;

import modelo.Funcionario;
import util.Input;
import java.util.ArrayList;
import java.util.Collections;
import util.DialogBoxUtils;

public class ControleFuncionario {
    private static ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();

    public static ArrayList<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }
    
    public static void menuControleFuncionario(){
        int op = ControleMenuCadastro.selecionarOpcaoMenuCadastro("Funcionários");
        do {

            switch (op) {
                case 1 ->
                    cadastrar();
                case 2 ->
                    buscar();
                case 3 ->
                    alterar();
                case 4 ->
                    remover();
                case 5 ->
                    menuListar();
                case 0 ->
                    System.out.println("\nRetornando ao menu principal...");
                default ->
                    DialogBoxUtils.exibirMensagemDeErro("Opção inválida!", "Erro! Opção inválida!");
            }
        } while (op != 0);
    }
    
    public static void setarDados(Funcionario fun){
        ControlePessoa.setarDados(fun);
        System.out.print("CTPS: ");
        fun.setCtps(Input.nextLine());
        System.out.print("Salário: ");
        fun.setSalario(Input.nextDouble());
    }
    
    public static void cadastrar() {
        try {
            Funcionario func = new Funcionario();
            setarDados(func);
            listaFuncionarios.add(func);
            DialogBoxUtils.exibirMensagem("Cadastro realizado", "O cadastro realizado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao cadastrar dados!\n" + e.getMessage());
        }
    }

    public static void alterar() {
        Funcionario funcionario = pesquisa(listaFuncionarios);
        if (funcionario == null) {
            DialogBoxUtils.exibirMensagem("Funcionario não encontrado", "Nenhum funcionário foi encontrada!");
            return;
        }
        try {
            setarDados(funcionario);
            DialogBoxUtils.exibirMensagem("Cadastro alterado", "O cadastro alterado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao alterar cadastro!\n" + e.getMessage());
        }
    }

    public static Funcionario pesquisa(ArrayList<Funcionario> listaFuncionarios) {
        System.out.println("Informe o Cpf: ");
        String dadoBusca = Input.nextLine();
        return pesquisa(listaFuncionarios, dadoBusca.toLowerCase());
    }

    public static Funcionario pesquisa(ArrayList<Funcionario> listaFuncionarios, String dadoBusca) {
        for (Funcionario listaFuncionario : listaFuncionarios) {
            if (listaFuncionario.getNome().toLowerCase().equals(dadoBusca) || listaFuncionario.getCpf().equals(dadoBusca)) {
                return listaFuncionario;
            }
        }
        return null;
    }

    public static void buscar() {
        ArrayList<Funcionario> resultado = pesquisaContains(listaFuncionarios);

        if (resultado.isEmpty()) {
            DialogBoxUtils.exibirMensagem("Funcionario não encontrado", "Nenhum funcionário foi encontrada!");
        } else {
            listar(resultado);
        }
    }

    public static ArrayList<Funcionario> pesquisaContains(ArrayList<Funcionario> listaFuncionarios) {
        System.out.println("Informe o nome ou cpf: ");
        String dadoBusca = Input.nextLine();
        return pesquisaContains(listaFuncionarios, dadoBusca.toLowerCase());
    }

    public static ArrayList<Funcionario> pesquisaContains(ArrayList<Funcionario> listaFuncionarios, String dadoBusca) {
        ArrayList<Funcionario> resultado = new ArrayList<>();
        for (Funcionario listaFuncionario : listaFuncionarios) {
            if (listaFuncionario.getNome().toLowerCase().contains(dadoBusca) || listaFuncionario.getCpf().contains(dadoBusca)) {
                resultado.add(listaFuncionario);
            }
        }
        return resultado;
    }

    public static void remover() {
        Funcionario funcionario = pesquisa(listaFuncionarios);
        if (funcionario == null) {
            DialogBoxUtils.exibirMensagem("Funcionario não encotrado", "Nenhum funcionário foi encontrada!");
        } else if(listaFuncionarios.remove(funcionario)) {
            DialogBoxUtils.exibirMensagem("Funcionario removido", "Funcionário removido com sucesso !");
        }
    }

    public static void listar() {
        int resp = menuListar();
        switch (resp) {
            case 1 ->
                ordenarLista(listaFuncionarios, true);
            case 2 ->
                ordenarLista(listaFuncionarios, false);
            default ->
                System.out.println("\nEscolha Inválida !!\n");
        }
    }

    public static int menuListar() {
        System.out.println("Informe a forma de ordenação");
        System.out.println("\n1 - Crescente \n2 - Decrescente");
        System.out.print("opção: ");
        return Input.nextInt();
    }

    public static void ordenarLista(ArrayList<Funcionario> lista, boolean ordemCrescente) {
        if (ordemCrescente) {
            Collections.sort(lista);
        } else {
            Collections.sort(lista, Collections.reverseOrder());
        }
    }

    public static void listar(ArrayList<Funcionario> listaPessoas) {
        System.out.println("\nFuncionários: ");
        for (Funcionario p : listaPessoas) {
            p.exibirInformacoes();
        }
    }
}
