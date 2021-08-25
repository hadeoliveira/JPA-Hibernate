package br.com.sppvc.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CategoriaId implements Serializable {

    private String tipo;
    private String nome;

    public CategoriaId() {}

    public CategoriaId(String nome, String tipo) {
        this.setNome(nome);
        this.setTipo(tipo);
    }

    public String getTipo() {
        return tipo;
    }

    private void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }
}
