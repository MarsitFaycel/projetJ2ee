package org.sid.entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING,length=2)
public abstract class Compte implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCompte;
	private String codeCompte;
	private String passCompte;
	private Date dateCreation;
	private double solde;
	@ManyToOne
	private Client client;
	@OneToMany(mappedBy="compte")
	
	private Collection<Operation> operations;
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	


	public Compte(String codeCompte, Date dateCreation, double solde, Client client) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}





	public Long getIdCompte() {
		return idCompte;
	}





	public void setIdCompte(Long idCompte) {
		this.idCompte = idCompte;
	}





	public String getCodeCompte() {
		return codeCompte;
	}





	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}





	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}





	public String getPassCompte() {
		return passCompte;
	}





	public void setPassCompte(String passCompte) {
		this.passCompte = passCompte;
	}
	
	
}
