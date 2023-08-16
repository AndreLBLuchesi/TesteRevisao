package controle;

import modelo.Aluno;
import modelo.Curso;
import util.Input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import util.DialogBoxUtils;

public class CadastroAluno {

    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();

    public static ArrayList<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public static void menuControleAluno() {
        int op;
        do {
            op = MenuCadastro.selecionarOpcaoMenuCadastro("Alunos");
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

    public static void cadastrar() {
        try {
            Aluno aluno = new Aluno();
            setarDados(aluno);
            listaAlunos.add(aluno);
            DialogBoxUtils.exibirMensagem("Cadastro realizado", "O cadastro realizado com sucesso!");
        } catch (Exception e) {
            DialogBoxUtils.exibirMensagemDeErro("Falha no cadastro", "Erro! falha ao cadastrar dados!\n" + e.getMessage());
        }
    }

    public static void setarDados(Aluno aluno) {
        CadastroPessoa.setarDados(aluno);
        System.out.print("RA: ");
        aluno.setRa(Input.nextLine());
        aluno.setDataMatricula(LocalDate.now());

        int op = DialogBoxUtils.exibirCaixaConfirmacao("Adicionar curso", "Deseja adicionar o curso? ");
        if (op == 0) {
            Curso cursoPesquisa;
            do {
                cursoPesquisa = CadastroCurso.pesquisa();

                if (cursoPesquisa == null) {
                    if (DialogBoxUtils.exibirCaixaConfirmacao("Curso não encontrado!", "Curso não encontrado! \nDeseja pesquisar novamente?") == 1) {
                        break;
                    }
                } else {
                    aluno.setCurso(cursoPesquisa);
                    System.out.print("Situação: ");
                    aluno.setSituacao(escolhaSituacao());
                }
            } while (cursoPesquisa == null);
        }
    }

    protected static String escolhaSituacao() {
        do {
            System.out.println("\n1 - Em andamento\n2 - Concluido\n3 - Trancada\n4 - Desistente");
            System.out.print("R: ");
            int op = Input.nextInt();
            switch (op) {
                case 1 -> {
                    return "Em andamento";
                }
                case 2 -> {
                    return "Concluido";
                }
                case 3 -> {
                    return "Trancada";
                }
                case 4 -> {
                    return "Desistente";
                }
                default -> {
                    System.out.println("\nValor inválido !!\n");
                }
            }
        } while (true);
    }

    public static void buscar() {
        ArrayList<Aluno> resultado = pesquisaContains();

        if (resultado.isEmpty()) {
            System.out.println("Nenhum aluno foi encontrado !!");
        } else {
            listar(resultado);
        }
    }

    public static ArrayList<Aluno> pesquisaContains() {
        System.out.println("Informe o nome ou cpf do aluno: ");
        String dadoBusca = Input.nextLine();
        return pesquisaContains(dadoBusca.toLowerCase());
    }

    public static ArrayList<Aluno> pesquisaContains(String busca) {
        ArrayList<Aluno> resultadoPesquisa = new ArrayList<>();

        for (Aluno listaAluno : listaAlunos) {
            if (listaAluno.getNome().toLowerCase().contains(busca) || listaAluno.getCpf().contains(busca) || listaAluno.getRa().contains(busca)) {
                resultadoPesquisa.add(listaAluno);
            }
        }
        return resultadoPesquisa;
    }

    public static void alterar() {
        Aluno alunoNovo = pesquisa();
        setarDados(alunoNovo);
        System.out.println("Aluno alterado com sucesso !!");
    }

    public static void remover() {
        Aluno aluno = pesquisa();
        if (aluno == null) {
            DialogBoxUtils.exibirMensagem("Pessoa não encontrada", "Nenhuma pessoa foi encontrada!");
        } else if (listaAlunos.remove(aluno)) {
            DialogBoxUtils.exibirMensagem("Aluno removido", "Aluno removido com sucesso !");
        }
    }

    protected static Aluno pesquisa() {
        System.out.println("Informe o Nome ou Cpf do aluno: ");
        String dadoBusca = Input.nextLine();
        return pesquisa(dadoBusca.toLowerCase());
    }

    protected static Aluno pesquisa(String busca) {
        for (Aluno listaAluno : listaAlunos) {
            if (listaAluno.getNome().toLowerCase().equals(busca) || listaAluno.getCpf().equals(busca) || listaAluno.getRa().equals(busca)) {
                return listaAluno;
            }
        }
        return null;
    }

    public static void listar() {
        int resp = menuListar();
        switch (resp) {
            case 1 ->
                ordenarLista(listaAlunos, true);
            case 2 ->
                ordenarLista(listaAlunos, false);
            default ->
                System.out.println("\nEscolha Inválida !!\n");
        }
        listar(listaAlunos);
    }

    public static void listar(ArrayList<Aluno> listaAlunos) {
        System.out.println("\nAlunos: ");

        for (Aluno listaAluno : listaAlunos) {
            listaAluno.exibirInformacoes();
        }
    }

    public static int menuListar() {
        System.out.println("Informe a forma de ordenação");
        System.out.println("\n1 - Crescente \n2 - Decrescente");
        System.out.print("opção: ");
        return Input.nextInt();
    }

    public static void ordenarLista(ArrayList<Aluno> lista, boolean ordemCrescente) {
        if (ordemCrescente) {
            Collections.sort(lista);
        } else {
            Collections.sort(lista, Collections.reverseOrder());
        }
    }

    public static int calcAlunosCurso(Curso curso) {
        int contAlunos = 0;

        for (Aluno listaAluno : listaAlunos) {
            if (listaAluno.getSituacao().equals("Em andamento") && curso.equals(listaAluno.getCurso())) {
                contAlunos++;
            }
        }
        return contAlunos;
    }
}
