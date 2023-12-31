package com.ticdoguinho.model.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author guthemberg.souza
 */
@Entity
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int codigo;
    @Column
    private String usuario;
    @Column
    private String senha;
    @Column
    private String email;
    @Column
    private boolean mamae;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tutor_pet", joinColumns = {
        @JoinColumn(name = "codigotutor", referencedColumnName = "codigo", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "codigopet", referencedColumnName = "codigo", nullable = false)
            }
    )
    public List<Pet> pets = new ArrayList<Pet>();
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMamae() {
        return mamae;
    }

    public void setMamae(boolean mamae) {
        this.mamae = mamae;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
