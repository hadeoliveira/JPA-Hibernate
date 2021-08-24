package br.com.sppvc.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {

    private String nome;
    private String cpf;

    public DadosPessoais(String nome, String cpf) {
        this.setCpf(cpf);
        this.setNome(nome);
    }

    public DadosPessoais(){}

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
