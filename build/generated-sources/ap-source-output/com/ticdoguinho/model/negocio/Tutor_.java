package com.ticdoguinho.model.negocio;

import com.ticdoguinho.model.negocio.Pet;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-11-29T12:06:26")
@StaticMetamodel(Tutor.class)
public class Tutor_ { 

    public static volatile SingularAttribute<Tutor, Boolean> mamae;
    public static volatile ListAttribute<Tutor, Pet> pets;
    public static volatile SingularAttribute<Tutor, String> senha;
    public static volatile SingularAttribute<Tutor, Integer> codigo;
    public static volatile SingularAttribute<Tutor, String> usuario;
    public static volatile SingularAttribute<Tutor, String> email;

}