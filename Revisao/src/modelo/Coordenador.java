package modelo;

public class Coordenador extends Professor{

    public Coordenador() {
    }

    @Override
    public void exibirInformacoes(){
        System.out.println("Nome: " + nome + " | Cpf: " + cpf + " | Idade: " + calcularIdade() + " anos " +
                " | Cidade: "+ endereco.getCidade() + " | Rua : " + endereco.getRua() + " | Número: " + endereco.getNumero() + " | "
                + "Ctps: " + ctps + " |  Salario: " + salario + " | Formação: " + formacao
        );
    }
}
