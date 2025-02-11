package src;

import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    // Construtor para inicializar os atributos
    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Getter para obter o nome
    public String getNome() {
        return nome;
    }

    // Setter para definir o nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para obter a data de nascimento
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    // Setter para definir a data de nascimento
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
