/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dvr
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByCodCli", query = "SELECT c FROM Cliente c WHERE c.codCli = :codCli"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByFantasia", query = "SELECT c FROM Cliente c WHERE c.fantasia = :fantasia"),
    @NamedQuery(name = "Cliente.findByEndereco", query = "SELECT c FROM Cliente c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "Cliente.findByBairro", query = "SELECT c FROM Cliente c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Cliente.findByCidade", query = "SELECT c FROM Cliente c WHERE c.cidade = :cidade"),
    @NamedQuery(name = "Cliente.findByUf", query = "SELECT c FROM Cliente c WHERE c.uf = :uf"),
    @NamedQuery(name = "Cliente.findByCep", query = "SELECT c FROM Cliente c WHERE c.cep = :cep"),
    @NamedQuery(name = "Cliente.findByReferencia", query = "SELECT c FROM Cliente c WHERE c.referencia = :referencia"),
    @NamedQuery(name = "Cliente.findByPergunta", query = "SELECT c FROM Cliente c WHERE c.pergunta = :pergunta"),
    @NamedQuery(name = "Cliente.findByResposta", query = "SELECT c FROM Cliente c WHERE c.resposta = :resposta"),
    @NamedQuery(name = "Cliente.findByTpEquipamento", query = "SELECT c FROM Cliente c WHERE c.tpEquipamento = :tpEquipamento"),
    @NamedQuery(name = "Cliente.findByDataExpObs", query = "SELECT c FROM Cliente c WHERE c.dataExpObs = :dataExpObs"),
    @NamedQuery(name = "Cliente.findByDataAtiv", query = "SELECT c FROM Cliente c WHERE c.dataAtiv = :dataAtiv"),
    @NamedQuery(name = "Cliente.findByDataCanc", query = "SELECT c FROM Cliente c WHERE c.dataCanc = :dataCanc"),
    @NamedQuery(name = "Cliente.findByFoneLocal", query = "SELECT c FROM Cliente c WHERE c.foneLocal = :foneLocal"),
    @NamedQuery(name = "Cliente.findByFoneLocal2", query = "SELECT c FROM Cliente c WHERE c.foneLocal2 = :foneLocal2")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private Integer idcliente;
    @Basic(optional = false)
    @Column(name = "cod_cli")
    private int codCli;
    @Column(name = "nome")
    private String nome;
    @Column(name = "fantasia")
    private String fantasia;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "cidade")
    private String cidade;
    @Column(name = "uf")
    private String uf;
    @Column(name = "cep")
    private String cep;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "pergunta")
    private String pergunta;
    @Column(name = "resposta")
    private String resposta;
    @Column(name = "tp_equipamento")
    private String tpEquipamento;
    @Lob
    @Column(name = "obs")
    private String obs;
    @Column(name = "data_exp_obs")
    @Temporal(TemporalType.DATE)
    private Date dataExpObs;
    @Column(name = "data_ativ")
    @Temporal(TemporalType.DATE)
    private Date dataAtiv;
    @Column(name = "data_canc")
    @Temporal(TemporalType.DATE)
    private Date dataCanc;
    @Column(name = "fone_local")
    private String foneLocal;
    @Column(name = "fone_local2")
    private String foneLocal2;
    @OneToMany(mappedBy = "idcliente")
    private List<Contato> contatoList;
    @OneToMany(mappedBy = "idcliente")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "codCli")
    private List<SinalRouter> sinalRouterList;
    @OneToMany(mappedBy = "idcliente")
    private List<Zona> zonaList;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(Integer idcliente, int codCli) {
        this.idcliente = idcliente;
        this.codCli = codCli;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getTpEquipamento() {
        return tpEquipamento;
    }

    public void setTpEquipamento(String tpEquipamento) {
        this.tpEquipamento = tpEquipamento;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getDataExpObs() {
        return dataExpObs;
    }

    public void setDataExpObs(Date dataExpObs) {
        this.dataExpObs = dataExpObs;
    }

    public Date getDataAtiv() {
        return dataAtiv;
    }

    public void setDataAtiv(Date dataAtiv) {
        this.dataAtiv = dataAtiv;
    }

    public Date getDataCanc() {
        return dataCanc;
    }

    public void setDataCanc(Date dataCanc) {
        this.dataCanc = dataCanc;
    }

    public String getFoneLocal() {
        return foneLocal;
    }

    public void setFoneLocal(String foneLocal) {
        this.foneLocal = foneLocal;
    }

    public String getFoneLocal2() {
        return foneLocal2;
    }

    public void setFoneLocal2(String foneLocal2) {
        this.foneLocal2 = foneLocal2;
    }

    @XmlTransient
    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<SinalRouter> getSinalRouterList() {
        return sinalRouterList;
    }

    public void setSinalRouterList(List<SinalRouter> sinalRouterList) {
        this.sinalRouterList = sinalRouterList;
    }

    @XmlTransient
    public List<Zona> getZonaList() {
        return zonaList;
    }

    public void setZonaList(List<Zona> zonaList) {
        this.zonaList = zonaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fox.db.Cliente[ idcliente=" + idcliente + " ]";
    }
    
}
