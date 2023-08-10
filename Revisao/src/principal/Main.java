package principal;

import controle.*;
import util.DialogBoxUtils;

/**
 *
 * @author Andre
 */
public class Main {
    
    public static void main(String[] args) {
        int op;
        do {
            op = ControleMenuCadastro.selecionarOpcaoMenuPrincipal();
            switch (op) {
                case 1 ->
                    ControleAluno.menuControleAluno();
                case 2 ->
                    ControleFuncionario.menuControleFuncionario();
                case 3 ->
                    ControleProfessor.menuControleProfessor();
                case 4 ->
                    ControleCoordenador.menuControleCoordenador();
                case 5 ->
                    ControleCurso.menuControleCurso();
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                }
                default -> DialogBoxUtils.exibirMensagemDeErro("Opção inválida!", "Erro! Opção inválida!");
            }
        } while (op != 0);
    }

}
