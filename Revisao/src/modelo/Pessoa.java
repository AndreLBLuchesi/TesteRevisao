package modelo;

import java.time.LocalDate;
import util.DateUtils;

/**
 *
 * @author Andre
 */
public class Pessoa implements Comparable<Pessoa>{

    protected String nome;
    protected String cpf;
    protected LocalDate dataNascimento;
    protected Endereco endereco;


    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, LocalDate dataNascimento, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public long calcularIdade(){
        return DateUtils.quantidadeAnosEntreDatas(dataNascimento, LocalDate.now());
    }

    @Override
    public int compareTo(Pessoa o) {
        return this.nome.compareToIgnoreCase(o.getNome());
    }

    public void exibirInformacoes(){
        System.out.println("Nome: " + nome + "| Cpf: " + cpf + " | Idade: " + calcularIdade() + " anos " +
                " | Cidade: "+ endereco.getCidade() + "| Rua : " + endereco.getRua() + ", " + endereco.getNumero());
    }

}
