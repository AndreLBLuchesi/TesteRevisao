package modelo;

public class Professor extends Funcionario{
    protected String formacao;

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public void exibirInformacoes(){
        System.out.println("Nome: " + nome + " | Cpf: " + cpf + " | Idade: " + calcularIdade() + " anos " +
                " | Cidade: "+ endereco.getCidade() + " | Rua : " + endereco.getRua() + " | Número: " + endereco.getNumero() + " | "
                + "Ctps: " + ctps + " |  Salario: " + salario + " | Formação: " + formacao
        );
    }
}
