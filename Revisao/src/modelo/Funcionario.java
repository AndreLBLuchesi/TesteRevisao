package modelo;

import java.time.LocalDate;

public class Funcionario extends Pessoa{
    protected String ctps;
    protected double salario;

    public Funcionario() {
    }

    public Funcionario(String ctps, double salario, String nome, String cpf, LocalDate dataNascimento, Endereco endereco) {
        super(nome, cpf, dataNascimento, endereco);
        this.ctps = ctps;
        this.salario = salario;
    }
    
    

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void exibirInformacoes(){
        System.out.println("Nome: " + nome + " | Cpf: " + cpf + " | Idade: " + calcularIdade() + " anos " +
                " | Cidade: "+ endereco.getCidade() + " | Rua : " + endereco.getRua() + " | Número: " + endereco.getNumero() + " | "
                + "Ctps: " + ctps + " |  Salario: " + salario
        );
    }
}
