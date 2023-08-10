package modelo;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Andre
 */
public class Aluno extends Pessoa{
    protected String ra;
    protected Curso curso;
    protected LocalDate dataMatricula;
    protected String situacao;
    public Aluno() {
    }
    public Aluno(String nome, String cpf, LocalDate dataNascimento, Endereco endereco, String ra, Curso curso, LocalDate dataMatricula, String situacao) {
        super(nome, cpf, dataNascimento, endereco);
        this.ra = ra;
        this.curso = curso;
        this.dataMatricula = dataMatricula;
        this.situacao = situacao;
    }


    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getAnoMatricula(){
        return getDataMatricula().getYear();
    }
    
    @Override
    public void exibirInformacoes(){
        System.out.println("Nome: " + nome + " | RA: " + ra + " | Idade: " + calcularIdade() + " anos | "
                            + "Curso: " + curso.getNome() + " | Ano matricula: " + getAnoMatricula() + " | Situação: " + situacao);
    }
}

