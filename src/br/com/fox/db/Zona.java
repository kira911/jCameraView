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
@Table(name = "zona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zona.findAll", query = "SELECT z FROM Zona z"),
    @NamedQuery(name = "Zona.findByIdzonas", query = "SELECT z FROM Zona z WHERE z.idzonas = :idzonas"),
    @NamedQuery(name = "Zona.findByAreas", query = "SELECT z FROM Zona z WHERE z.areas = :areas"),
    @NamedQuery(name = "Zona.findByCamera", query = "SELECT z FROM Zona z WHERE z.camera = :camera"),
    @NamedQuery(name = "Zona.findByNumZona", query = "SELECT z FROM Zona z WHERE z.numZona = :numZona")})
public class Zona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idzonas")
    private Integer idzonas;
    @Column(name = "areas")
    private String areas;
    @Column(name = "camera")
    private Integer camera;
    @Column(name = "num_zona")
    private Integer numZona;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente idcliente;

    public Zona() {
    }

    public Zona(Integer idzonas) {
        this.idzonas = idzonas;
    }

    public Integer getIdzonas() {
        return idzonas;
    }

    public void setIdzonas(Integer idzonas) {
        this.idzonas = idzonas;
    }

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public Integer getCamera() {
        return camera;
    }

    public void setCamera(Integer camera) {
        this.camera = camera;
    }

    public Integer getNumZona() {
        return numZona;
    }

    public void setNumZona(Integer numZona) {
        this.numZona = numZona;
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
        hash += (idzonas != null ? idzonas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zona)) {
            return false;
        }
        Zona other = (Zona) object;
        if ((this.idzonas == null && other.idzonas != null) || (this.idzonas != null && !this.idzonas.equals(other.idzonas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fox.db.Zona[ idzonas=" + idzonas + " ]";
    }
    
}
