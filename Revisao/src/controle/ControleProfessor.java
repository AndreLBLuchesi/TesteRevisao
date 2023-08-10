package controle;

import modelo.Professor;
import util.Input;
import java.util.ArrayList;
import java.util.Collections;
import util.DialogBoxUtils;

public class ControleProfessor {
    protected static ArrayList<Professor> listaProfessores = new ArrayList<>();

    public static ArrayList<Professor> getListaProfessores() {
        return listaProfessores;
    }
    
    public static void menuControleProfessor(){
        int op = ControleMenuCadastro.selecionarOpcaoMenuCadastro("Professores");
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
    
    public static void setarDados(Professor prof){
        ControleFuncionario.setarDados(prof);
        System.out.print("Formação: ");
        prof.setFormacao(Input.nextLine());
    }
    
    public static void cadastrar() {
        try {
            Professor func = new Professor();
            setarDados(func);
            listaProfessores.add(func);
            DialogBoxUtils.exibirMensagem("Cadastro realizado", "O cadastro realizado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao cadastrar dados!\n" + e.getMessage());
        }
    }

    public static void alterar() {
        Professor professor = pesquisa(listaProfessores);
        if (professor == null) {
            DialogBoxUtils.exibirMensagem("Professor não encontrado", "Nenhum professor foi encontrada!");
            return;
        }
        try {
            setarDados(professor);
            DialogBoxUtils.exibirMensagem("Cadastro alterado", "O cadastro alterado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao alterar cadastro!\n" + e.getMessage());
        }
    }

    public static Professor pesquisa(ArrayList<Professor> listaProfessors) {
        System.out.println("Informe o Cpf: ");
        String dadoBusca = Input.nextLine();
        return pesquisa(listaProfessors, dadoBusca.toLowerCase());
    }

    public static Professor pesquisa(ArrayList<Professor> listaProfessors, String dadoBusca) {
        for (Professor listaProfessor : listaProfessors) {
            if (listaProfessor.getNome().toLowerCase().equals(dadoBusca) || listaProfessor.getCpf().equals(dadoBusca)) {
                return listaProfessor;
            }
        }
        return null;
    }

    public static void buscar() {
        ArrayList<Professor> resultado = pesquisaContains(listaProfessores);

        if (resultado.isEmpty()) {
            DialogBoxUtils.exibirMensagem("Professor não encontrado", "Nenhum professor foi encontrada!");
        } else {
            listar(resultado);
        }
    }

    public static ArrayList<Professor> pesquisaContains(ArrayList<Professor> listaProfessors) {
        System.out.println("Informe o nome ou cpf: ");
        String dadoBusca = Input.nextLine();
        return pesquisaContains(listaProfessors, dadoBusca.toLowerCase());
    }

    public static ArrayList<Professor> pesquisaContains(ArrayList<Professor> listaProfessors, String dadoBusca) {
        ArrayList<Professor> resultado = new ArrayList<>();
        for (Professor listaProfessor : listaProfessors) {
            if (listaProfessor.getNome().toLowerCase().contains(dadoBusca) || listaProfessor.getCpf().contains(dadoBusca)) {
                resultado.add(listaProfessor);
            }
        }
        return resultado;
    }

    public static void remover() {
        Professor professor = pesquisa(listaProfessores);
        if (professor == null) {
            DialogBoxUtils.exibirMensagem("Professor não encotrado", "Nenhum professor foi encontrada!");
        } else if(listaProfessores.remove(professor)) {
            DialogBoxUtils.exibirMensagem("Professor removido", "Funcionário removido com sucesso !");
        }
    }

    public static void listar() {
        int resp = menuListar();
        switch (resp) {
            case 1 ->
                ordenarLista(listaProfessores, true);
            case 2 ->
                ordenarLista(listaProfessores, false);
            default ->
                System.out.println("\nEscolha Inválida !!\n");
        }
        listar(listaProfessores);
    }
    
    public static void listar(ArrayList<Professor> listaPessoas) {
        System.out.println("\nProfessores: ");
        for (Professor p : listaPessoas) {
            p.exibirInformacoes();
        }
    }

    public static int menuListar() {
        System.out.println("Informe a forma de ordenação");
        System.out.println("\n1 - Crescente \n2 - Decrescente");
        System.out.print("opção: ");
        return Input.nextInt();
    }

    public static void ordenarLista(ArrayList<Professor> lista, boolean ordemCrescente) {
        if (ordemCrescente) {
            Collections.sort(lista);
        } else {
            Collections.sort(lista, Collections.reverseOrder());
        }
    }

}
