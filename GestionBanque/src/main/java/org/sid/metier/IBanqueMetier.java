package org.sid.metier;

import org.sid.entite.Client;
import org.sid.entite.Compte;
import org.sid.entite.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {
	public Compte identifierCompte(String CodeCompte,String cpasse);
	public Compte consulterCompte(String CodeCompte);
	public void verser(String codeCpte ,double montant);
	public void retirer(String codeCpte ,double montant);
	public void virement(String codeCpte1,String codeCpte2 ,double montant);
	public Page<Operation> listeOperation(String codeCpte,int page,int size);
	public Client infoClient(String id);
	public Page<Compte> listeCompte(String idClient,int page,int size);
	public void valider(Long id);
}
