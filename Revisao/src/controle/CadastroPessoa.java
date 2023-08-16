package controle;

import java.util.ArrayList;
import java.util.Collections;
import modelo.Pessoa;
import util.DialogBoxUtils;
import util.Input;

public class CadastroPessoa {

    protected static ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    public static void menuControlePessoa() {
        int op = MenuCadastro.selecionarOpcaoMenuCadastro("Pessoas");
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
                    listar();
                case 0 ->
                    System.out.println("\nRetornando ao menu principal...");
                default ->
                    DialogBoxUtils.exibirMensagemDeErro("Opção inválida!", "Erro! Opção inválida!");
            }
        } while (op != 0);
    }

    public static void cadastrar() {
        try {
            Pessoa p = new Pessoa();
            setarDados(p);
            listaPessoas.add(p);
            DialogBoxUtils.exibirMensagem("Cadastro realizado", "O cadastro realizado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao cadastrar dados!\n" + e.getMessage());
        }
    }

    public static void alterar() {
        Pessoa pessoa = pesquisa(listaPessoas);
        if (pessoa == null) {
            DialogBoxUtils.exibirMensagem("Pessoa não encotrada", "Nenhuma pessoa foi encontrada!");
            return;
        }
        try {
            setarDados(pessoa);
            DialogBoxUtils.exibirMensagem("Cadastro alterado", "O cadastro alterado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao alterar cadastro!\n" + e.getMessage());
        }
    }

    protected static void setarDados(Pessoa pessoa) {
        System.out.print("Nome: ");
        pessoa.setNome(Input.nextLine());
        System.out.print("CPF: ");
        pessoa.setCpf(Input.nextLine());
        System.out.print("Data de nascimento: ");
        pessoa.setDataNascimento(Input.nextLocalDate());
        pessoa.setEndereco(CadastroEndereco.setarDadosEndereco());
    }

    public static Pessoa pesquisa(ArrayList<Pessoa> listaPessoas) {
        System.out.println("Informe o Cpf: ");
        String dadoBusca = Input.nextLine();
        return pesquisa(listaPessoas, dadoBusca.toLowerCase());
    }

    public static Pessoa pesquisa(ArrayList<Pessoa> listaPessoas, String dadoBusca) {
        for (Pessoa listaPessoa : listaPessoas) {
            if (listaPessoa.getNome().toLowerCase().equals(dadoBusca) || listaPessoa.getCpf().equals(dadoBusca)) {
                return listaPessoa;
            }
        }
        return null;
    }

    protected static void buscar() {
        ArrayList<Pessoa> resultado = pesquisaContains(listaPessoas);

        if (resultado.isEmpty()) {
            DialogBoxUtils.exibirMensagem("Pessoa não encotrada", "Nenhuma pessoa foi encontrada!");
        } else {
            listar(resultado);
        }
    }

    protected static ArrayList<Pessoa> pesquisaContains(ArrayList<Pessoa> listaPessoas) {
        System.out.println("Informe o nome ou cpf: ");
        String dadoBusca = Input.nextLine();
        return pesquisaContains(listaPessoas, dadoBusca.toLowerCase());
    }

    protected static ArrayList<Pessoa> pesquisaContains(ArrayList<Pessoa> listaPessoas, String dadoBusca) {
        ArrayList<Pessoa> resultado = new ArrayList<>();
        for (Pessoa listaPessoa : listaPessoas) {
            if (listaPessoa.getNome().toLowerCase().contains(dadoBusca) || listaPessoa.getCpf().contains(dadoBusca)) {
                resultado.add(listaPessoa);
            }
        }
        return resultado;
    }

    public static void remover() {
        Pessoa pessoa = pesquisa(listaPessoas);
        if (pessoa == null) {
            DialogBoxUtils.exibirMensagem("Pessoa não encontrada", "Nenhuma pessoa foi encontrada!");
        } else if(listaPessoas.remove(pessoa)) {
            DialogBoxUtils.exibirMensagem("Pessoa removida", "Pessoa removida com sucesso !");
        }
    }

    public static void listar() {
        int resp = menuListar();
        switch (resp) {
            case 1 ->
                ordenarLista(listaPessoas, true);
            case 2 ->
                ordenarLista(listaPessoas, false);
            default ->
                System.out.println("\nEscolha Inválida !!\n");
        }
        listar(listaPessoas);
    }

    public static int menuListar() {
        System.out.println("Informe a forma de ordenação");
        System.out.println("\n1 - Crescente \n2 - Decrescente");
        System.out.print("opção: ");
        return Input.nextInt();
    }

    public static void ordenarLista(ArrayList<Pessoa> lista, boolean ordemCrescente) {
        if (ordemCrescente) {
            Collections.sort(lista);
        } else {
            Collections.sort(lista, Collections.reverseOrder());
        }
    }

    protected static void listar(ArrayList<Pessoa> listaPessoas) {
        System.out.println("\nPessoas: ");
        for (Pessoa p : listaPessoas) {
            p.exibirInformacoes();
        }
    }
}
