package br.com.fox.db;

import br.com.fox.db.SinalRouter;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-01-17T12:17:39")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> username;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile ListAttribute<Users, SinalRouter> sinalRouterList;
    public static volatile SingularAttribute<Users, String> password;

}