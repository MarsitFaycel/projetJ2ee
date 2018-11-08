package org.sid.entite;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private double decouvet;

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	

	public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client) {
		super(codeCompte, dateCreation, solde, client);
		// TODO Auto-generated constructor stub
	}





	public double getDecouvet() {
		return decouvet;
	}

	public void setDecouvet(double decouvet) {
		this.decouvet = decouvet;
	}
	
}
