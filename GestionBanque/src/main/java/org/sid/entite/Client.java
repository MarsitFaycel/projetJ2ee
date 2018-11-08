package org.sid.entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
@Entity
public class Client implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codeClient;
	
	private String identifiantClient;
	private String mpClient;
	
	private String nomClient;
	
	private String prenomClient;
	private String adresse;
	private Date dateDeNaissanse;
	private String ville;
	private String codePostale;
	private String email;
	
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
	private Collection<Compte> 	comptes;
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Client(String identifiantClient, String nomClient, String prenomClient, String adresse, Date dateDeNaissanse,
			String ville, String codePostale, String email) {
		super();
		this.identifiantClient = identifiantClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse = adresse;
		this.dateDeNaissanse = dateDeNaissanse;
		this.ville = ville;
		this.codePostale = codePostale;
		this.email = email;
	}
	public Long getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(Long codeClient) {
		this.codeClient = codeClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getEmail() {
		return email;
	
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	
	
	
	public String getIdentifiantClient() {
		return identifiantClient;
	}


	public void setIdentifiantClient(String identifiantClient) {
		this.identifiantClient = identifiantClient;
	}


	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Date getDateDeNaissanse() {
		return dateDeNaissanse;
	}
	public void setDateDeNaissanse(Date dateDeNaissanse) {
		this.dateDeNaissanse = dateDeNaissanse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostale() {
		return codePostale;
	}
	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}


	public String getMpClient() {
		return mpClient;
	}


	public void setMpClient(String mpClient) {
		this.mpClient = mpClient;
	}
	
	
	

}
