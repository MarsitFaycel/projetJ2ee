package org.sid.metier;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entite.Client;
import org.sid.entite.Compte;
import org.sid.entite.CompteCourant;
import org.sid.entite.Operation;
import org.sid.entite.Retrait;
import org.sid.entite.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
	@Autowired
	private CompteRepository compteR;
	@Autowired
	private OperationRepository OpR;
	@Autowired
	private ClientRepository clientR;
	

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp=consulterCompte(codeCpte);
		Versement v=new Versement(new Date(), montant, cp);
		OpR.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteR.save(cp);
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp=consulterCompte(codeCpte);
		double i=0;
		if(cp instanceof CompteCourant)
		{
			i=((CompteCourant) cp).getDecouvet();
		}
		if(i+cp.getSolde()<montant)
		{
			throw new RuntimeException("Solde Insuffisant");
		}	
		Retrait R=new Retrait(new Date(), montant, cp);
		OpR.save(R);
		cp.setSolde(cp.getSolde()-montant);
		compteR.save(cp);

		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
	
		if (codeCpte1.equals(codeCpte2)) {
			throw new RuntimeException("Operation impossible sur le meme compte");
		}
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
		
	}

	@Override
	public Page<Operation> listeOperation(String codeCpte, int page, int size) {
		 
		return OpR.listeOp(codeCpte, new PageRequest(page, size));
	}

	@Override
	public Client infoClient(String id) {
		Client cp=clientR.chercher(id);
		if (cp==null) throw new RuntimeException("identifiant Client Introuvable");
		return cp;
	}

	@Override
	public Page<Compte> listeCompte(String idClient, int page, int size) {
		return compteR.listeC(idClient, new PageRequest(page, size));
		
	}

	

	@Override
	public Compte consulterCompte(String CodeCompte) {
		Compte cp=compteR.chercher(CodeCompte);
		if (cp==null) throw new RuntimeException("Compte Introuvable");
		return cp;
		
	}

	@Override
	public Compte identifierCompte(String CodeCompte, String cpasse) {
		Compte cp=compteR.chercher(CodeCompte,cpasse);
		if (cp==null) throw new RuntimeException("Compte Introuvable");
		return cp;
	}



static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
static SecureRandom rnd = new SecureRandom();
@Transactional
String randomString( int len ){
   StringBuilder sb = new StringBuilder( len );
   for( int i = 0; i < len; i++ ) 
      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
   return sb.toString();
}


	@Override
	public void valider(Long id) {
		Client c=clientR.getOne(id);
		
	    String idc=randomString(10);
	    
	    String mpc=randomString(10);
		
	    c.setIdentifiantClient(idc);
	    c.setMpClient(mpc);
		clientR.save(c);
		clientR.inserer(idc,mpc , 1);
		clientR.insererRole(idc, "USER");
		
	}
 
}
