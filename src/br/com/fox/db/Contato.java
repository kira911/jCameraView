/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dvr
 */
@Entity
@Table(name = "contato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contato.findAll", query = "SELECT c FROM Contato c"),
    @NamedQuery(name = "Contato.findByIdcontatos", query = "SELECT c FROM Contato c WHERE c.idcontatos = :idcontatos"),
    @NamedQuery(name = "Contato.findByNome", query = "SELECT c FROM Contato c WHERE c.nome = :nome"),
    @NamedQuery(name = "Contato.findByFone", query = "SELECT c FROM Contato c WHERE c.fone = :fone"),
    @NamedQuery(name = "Contato.findByFone2", query = "SELECT c FROM Contato c WHERE c.fone2 = :fone2"),
    @NamedQuery(name = "Contato.findByPrioridade", query = "SELECT c FROM Contato c WHERE c.prioridade = :prioridade"),
    @NamedQuery(name = "Contato.findByFuncao", query = "SELECT c FROM Contato c WHERE c.funcao = :funcao")})
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontatos")
    private Integer idcontatos;
    @Column(name = "nome")
    private String nome;
    @Column(name = "fone")
    private String fone;
    @Column(name = "fone2")
    private String fone2;
    @Column(name = "prioridade")
    private Integer prioridade;
    @Column(name = "funcao")
    private String funcao;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente idcliente;

    public Contato() {
    }

    public Contato(Integer idcontatos) {
        this.idcontatos = idcontatos;
    }

    public Integer getIdcontatos() {
        return idcontatos;
    }

    public void setIdcontatos(Integer idcontatos) {
        this.idcontatos = idcontatos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontatos != null ? idcontatos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contato)) {
            return false;
        }
        Contato other = (Contato) object;
        if ((this.idcontatos == null && other.idcontatos != null) || (this.idcontatos != null && !this.idcontatos.equals(other.idcontatos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fox.db.Contato[ idcontatos=" + idcontatos + " ]";
    }
    
}
