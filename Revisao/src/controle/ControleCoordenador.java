package controle;

import modelo.Coordenador;
import util.Input;

import java.util.ArrayList;
import java.util.Collections;
import util.DialogBoxUtils;

public class ControleCoordenador {
    protected static ArrayList<Coordenador> listaCoordenadores = new ArrayList<>();
    
    public static void menuControleCoordenador(){
        int op = ControleMenuCadastro.selecionarOpcaoMenuCadastro("Coordenador");
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
    
    public static void setarDados(Coordenador coord){
        ControleProfessor.setarDados(coord);
        System.out.print("Formação: ");
        coord.setFormacao(Input.nextLine());
    }
    
    public static void cadastrar() {
        try {
            Coordenador coord = new Coordenador();
            setarDados(coord);
            listaCoordenadores.add(coord);
            DialogBoxUtils.exibirMensagem("Cadastro realizado", "O cadastro realizado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao cadastrar dados!\n" + e.getMessage());
        }
    }

    public static void alterar() {
        Coordenador coord = pesquisa(listaCoordenadores);
        if (coord == null) {
            DialogBoxUtils.exibirMensagem("Coordenador não encontrado", "Nenhum coordenador foi encontrada!");
            return;
        }
        try {
            setarDados(coord);
            DialogBoxUtils.exibirMensagem("Cadastro alterado", "O cadastro alterado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao alterar cadastro!\n" + e.getMessage());
        }
    }

    public static Coordenador pesquisa(ArrayList<Coordenador> listaCoordenadors) {
        System.out.println("Informe o Cpf: ");
        String dadoBusca = Input.nextLine();
        return pesquisa(listaCoordenadors, dadoBusca.toLowerCase());
    }

    public static Coordenador pesquisa(ArrayList<Coordenador> listaCoordenadors, String dadoBusca) {
        for (Coordenador listaCoordenador : listaCoordenadors) {
            if (listaCoordenador.getNome().toLowerCase().equals(dadoBusca) || listaCoordenador.getCpf().equals(dadoBusca)) {
                return listaCoordenador;
            }
        }
        return null;
    }

    public static void buscar() {
        ArrayList<Coordenador> resultado = pesquisaContains(listaCoordenadores);

        if (resultado.isEmpty()) {
            DialogBoxUtils.exibirMensagem("Coordenador não encontrado", "Nenhum coordenador foi encontrada!");
        } else {
            listar(resultado);
        }
    }

    public static ArrayList<Coordenador> pesquisaContains(ArrayList<Coordenador> listaCoordenadors) {
        System.out.println("Informe o nome ou cpf: ");
        String dadoBusca = Input.nextLine();
        return pesquisaContains(listaCoordenadors, dadoBusca.toLowerCase());
    }

    public static ArrayList<Coordenador> pesquisaContains(ArrayList<Coordenador> listaCoordenadors, String dadoBusca) {
        ArrayList<Coordenador> resultado = new ArrayList<>();
        for (Coordenador listaCoordenador : listaCoordenadors) {
            if (listaCoordenador.getNome().toLowerCase().contains(dadoBusca) || listaCoordenador.getCpf().contains(dadoBusca)) {
                resultado.add(listaCoordenador);
            }
        }
        return resultado;
    }

    public static void remover() {
        Coordenador professor = pesquisa(listaCoordenadores);
        if (professor == null) {
            DialogBoxUtils.exibirMensagem("Coordenador não encotrado", "Nenhum coordenador foi encontrada!");
        } else if(listaCoordenadores.remove(professor)) {
            DialogBoxUtils.exibirMensagem("Coordenador removido", "Coordenador removido com sucesso !");
        }
    }

    public static void listar() {
        int resp = menuListar();
        switch (resp) {
            case 1 ->
                ordenarLista(listaCoordenadores, true);
            case 2 ->
                ordenarLista(listaCoordenadores, false);
            default ->
                System.out.println("\nEscolha Inválida !!\n");
        }
        listar(listaCoordenadores);
    }
    
    public static void listar(ArrayList<Coordenador> listaPessoas) {
        System.out.println("\nCoordenadores: ");
        for (Coordenador p : listaPessoas) {
            p.exibirInformacoes();
        }
    }

    public static int menuListar() {
        System.out.println("Informe a forma de ordenação");
        System.out.println("\n1 - Crescente \n2 - Decrescente");
        System.out.print("opção: ");
        return Input.nextInt();
    }

    public static void ordenarLista(ArrayList<Coordenador> lista, boolean ordemCrescente) {
        if (ordemCrescente) {
            Collections.sort(lista);
        } else {
            Collections.sort(lista, Collections.reverseOrder());
        }
    }
        
     
}
