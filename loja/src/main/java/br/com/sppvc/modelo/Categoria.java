package br.com.sppvc.modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name ="categorias")
public class Categoria {
    @EmbeddedId
    private CategoriaId id;

    public Categoria(){
    }

    public Categoria(String nome){
        this.id = new CategoriaId(nome, "XPTO");
    }

    public String getNome() {
        return this.id.getNome();
    }

}
