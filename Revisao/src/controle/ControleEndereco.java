package controle;

import modelo.Endereco;
import util.Input;

public class ControleEndereco {
    protected static Endereco setarDadosEndereco(){
        Endereco end = new Endereco();
        System.out.print("Cidade: ");
        end.setCidade(Input.nextLine());
        System.out.print("Rua: ");
        end.setRua(Input.nextLine());
        System.out.print("Número: ");
        end.setNumero(Input.nextLine());
        return end;
    }
}
