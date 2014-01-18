/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dvr
 */
@Entity
@Table(name = "sinal_router")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SinalRouter.findAll", query = "SELECT s FROM SinalRouter s"),
    @NamedQuery(name = "SinalRouter.findByIdsinalRouter", query = "SELECT s FROM SinalRouter s WHERE s.idsinalRouter = :idsinalRouter"),
    @NamedQuery(name = "SinalRouter.findByOri", query = "SELECT s FROM SinalRouter s WHERE s.ori = :ori"),
    @NamedQuery(name = "SinalRouter.findByNor", query = "SELECT s FROM SinalRouter s WHERE s.nor = :nor"),
    @NamedQuery(name = "SinalRouter.findByCod", query = "SELECT s FROM SinalRouter s WHERE s.cod = :cod"),
    @NamedQuery(name = "SinalRouter.findByDat", query = "SELECT s FROM SinalRouter s WHERE s.dat = :dat"),
    @NamedQuery(name = "SinalRouter.findByNuc", query = "SELECT s FROM SinalRouter s WHERE s.nuc = :nuc"),
    @NamedQuery(name = "SinalRouter.findByApl", query = "SELECT s FROM SinalRouter s WHERE s.apl = :apl"),
    @NamedQuery(name = "SinalRouter.findByIns", query = "SELECT s FROM SinalRouter s WHERE s.ins = :ins"),
    @NamedQuery(name = "SinalRouter.findByOrg", query = "SELECT s FROM SinalRouter s WHERE s.org = :org"),
    @NamedQuery(name = "SinalRouter.findBySub", query = "SELECT s FROM SinalRouter s WHERE s.sub = :sub"),
    @NamedQuery(name = "SinalRouter.findByNsb", query = "SELECT s FROM SinalRouter s WHERE s.nsb = :nsb"),
    @NamedQuery(name = "SinalRouter.findBySbn", query = "SELECT s FROM SinalRouter s WHERE s.sbn = :sbn"),
    @NamedQuery(name = "SinalRouter.findByCor", query = "SELECT s FROM SinalRouter s WHERE s.cor = :cor"),
    @NamedQuery(name = "SinalRouter.findByIps", query = "SELECT s FROM SinalRouter s WHERE s.ips = :ips"),
    @NamedQuery(name = "SinalRouter.findByPos", query = "SELECT s FROM SinalRouter s WHERE s.pos = :pos"),
    @NamedQuery(name = "SinalRouter.findByStatus", query = "SELECT s FROM SinalRouter s WHERE s.status = :status"),
    @NamedQuery(name = "SinalRouter.findByDuracao", query = "SELECT s FROM SinalRouter s WHERE s.duracao = :duracao")})
public class SinalRouter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsinal_router")
    private Integer idsinalRouter;
    @Column(name = "ori")
    private String ori;
    @Column(name = "nor")
    private String nor;
    @Column(name = "cod")
    private Integer cod;
    @Column(name = "dat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dat;
    @Column(name = "nuc")
    private Integer nuc;
    @Column(name = "apl")
    private String apl;
    @Column(name = "ins")
    private Integer ins;
    @Column(name = "org")
    private String org;
    @Column(name = "sub")
    private String sub;
    @Column(name = "nsb")
    private Integer nsb;
    @Column(name = "sbn")
    private Integer sbn;
    @Column(name = "cor")
    private String cor;
    @Column(name = "ips")
    private String ips;
    @Column(name = "pos")
    private Integer pos;
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Lob
    @Column(name = "log")
    private String log;
    @Column(name = "duracao")
    private String duracao;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Users username;
    @JoinColumn(name = "cod_cli", referencedColumnName = "cod_cli")
    @ManyToOne
    private Cliente codCli;

    public SinalRouter() {
    }

    public SinalRouter(Integer idsinalRouter) {
        this.idsinalRouter = idsinalRouter;
    }

    public SinalRouter(Integer idsinalRouter, String log) {
        this.idsinalRouter = idsinalRouter;
        this.log = log;
    }

    public Integer getIdsinalRouter() {
        return idsinalRouter;
    }

    public void setIdsinalRouter(Integer idsinalRouter) {
        this.idsinalRouter = idsinalRouter;
    }

    public String getOri() {
        return ori;
    }

    public void setOri(String ori) {
        this.ori = ori;
    }

    public String getNor() {
        return nor;
    }

    public void setNor(String nor) {
        this.nor = nor;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public Integer getNuc() {
        return nuc;
    }

    public void setNuc(Integer nuc) {
        this.nuc = nuc;
    }

    public String getApl() {
        return apl;
    }

    public void setApl(String apl) {
        this.apl = apl;
    }

    public Integer getIns() {
        return ins;
    }

    public void setIns(Integer ins) {
        this.ins = ins;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Integer getNsb() {
        return nsb;
    }

    public void setNsb(Integer nsb) {
        this.nsb = nsb;
    }

    public Integer getSbn() {
        return sbn;
    }

    public void setSbn(Integer sbn) {
        this.sbn = sbn;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    public Cliente getCodCli() {
        return codCli;
    }

    public void setCodCli(Cliente codCli) {
        this.codCli = codCli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsinalRouter != null ? idsinalRouter.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SinalRouter)) {
            return false;
        }
        SinalRouter other = (SinalRouter) object;
        if ((this.idsinalRouter == null && other.idsinalRouter != null) || (this.idsinalRouter != null && !this.idsinalRouter.equals(other.idsinalRouter))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fox.db.SinalRouter[ idsinalRouter=" + idsinalRouter + " ]";
    }
    
}
