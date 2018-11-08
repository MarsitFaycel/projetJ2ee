package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.entite.Client;
import org.sid.entite.Compte;
import org.sid.entite.CompteCourant;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionBanqueApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientR;
	@Autowired
	private CompteRepository compteR;
	@Autowired 
	private IBanqueMetier b;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionBanqueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//Client c1=clientR.save(new Client("ali","ali@hotmail.fr"));
		//Compte cp1=compteR.save(new CompteCourant("az124", new Date(), 785, c2) );
		//b.verser(cp1.getCodeCompte(), 500);
		//Client c2=clientR.save(new Client("aaaaa", "Ali", "Ali", "30 Rue tunis", new Date(1990, 5, 6), "tunis", "2005", "ali@gmail.fr"));
		//Compte cp2=compteR.save(new CompteCourant("az125", new Date(), 785, c2) );
		//Compte cp3=compteR.save(new CompteCourant("az126", new Date(), 1000, c2) );
		//Compte cp4=compteR.save(new CompteCourant("az127", new Date(), 20000, c2) );
		//clientR.inserer("a7777", "password", 4); ===>ok :)
		//clientR.insererRole("azzz", "ADMIN");
		//b.valider(new Long(2)); ==>ok
		//b.valider(new Long(4));
		//b.verser(cp2.getCodeCompte(), 500);
		
		
	}
}
