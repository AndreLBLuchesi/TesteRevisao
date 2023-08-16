package modelo;

import java.time.LocalDate;

public class Coordenador extends Professor{

    public Coordenador() {
    }

    public Coordenador(String formacao, String ctps, double salario, String nome, String cpf, LocalDate dataNascimento, Endereco endereco) {
        super(formacao, ctps, salario, nome, cpf, dataNascimento, endereco);
    }

    @Override
    public void exibirInformacoes(){
        System.out.println("Nome: " + nome + " | Cpf: " + cpf + " | Idade: " + calcularIdade() + " anos " +
                " | Cidade: "+ endereco.getCidade() + " | Rua : " + endereco.getRua() + " | Número: " + endereco.getNumero() + " | "
                + "Ctps: " + ctps + " |  Salario: " + salario + " | Formação: " + formacao
        );
    }
}
