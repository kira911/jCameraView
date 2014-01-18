package br.com.fox.db;

import br.com.fox.db.Contato;
import br.com.fox.db.SinalRouter;
import br.com.fox.db.Usuario;
import br.com.fox.db.Zona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2014-01-17T12:17:39")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> resposta;
    public static volatile SingularAttribute<Cliente, String> uf;
    public static volatile ListAttribute<Cliente, SinalRouter> sinalRouterList;
    public static volatile SingularAttribute<Cliente, String> foneLocal;
    public static volatile SingularAttribute<Cliente, String> fantasia;
    public static volatile SingularAttribute<Cliente, String> foneLocal2;
    public static volatile SingularAttribute<Cliente, String> tpEquipamento;
    public static volatile SingularAttribute<Cliente, Date> dataExpObs;
    public static volatile SingularAttribute<Cliente, String> pergunta;
    public static volatile SingularAttribute<Cliente, String> referencia;
    public static volatile SingularAttribute<Cliente, Date> dataAtiv;
    public static volatile SingularAttribute<Cliente, Integer> idcliente;
    public static volatile SingularAttribute<Cliente, String> obs;
    public static volatile SingularAttribute<Cliente, String> cidade;
    public static volatile SingularAttribute<Cliente, String> bairro;
    public static volatile SingularAttribute<Cliente, Date> dataCanc;
    public static volatile SingularAttribute<Cliente, String> cep;
    public static volatile ListAttribute<Cliente, Zona> zonaList;
    public static volatile ListAttribute<Cliente, Usuario> usuarioList;
    public static volatile SingularAttribute<Cliente, String> nome;
    public static volatile SingularAttribute<Cliente, Integer> codCli;
    public static volatile ListAttribute<Cliente, Contato> contatoList;
    public static volatile SingularAttribute<Cliente, String> endereco;

}