package br.com.fox.db;

import br.com.fox.db.Cliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-01-17T12:17:39")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> codigo;
    public static volatile SingularAttribute<Usuario, Cliente> idcliente;
    public static volatile SingularAttribute<Usuario, Integer> idusuarios;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile SingularAttribute<Usuario, String> cargo;

}