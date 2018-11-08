package org.sid.web;

import javax.validation.Valid;

import org.sid.dao.ClientRepository;
import org.sid.dao.OperationRepository;
import org.sid.entite.Client;
import org.sid.entite.Compte;
import org.sid.entite.Operation;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {

		@Autowired
		private IBanqueMetier banqueMetier;
		@Autowired
		private ClientRepository clientR;
		@Autowired
		private OperationRepository opR;
		
		
	@RequestMapping("/compte")	
	public String index()
	{
		return "compte";
	
	}
	
	
	@RequestMapping("/consulterCompte")
	public String identifierCompte(Model model,String ccompte,String cpasse,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size)
	{
		model.addAttribute("ccompte", ccompte);
		model.addAttribute("cpasse", cpasse);
		try {
			Compte cp=banqueMetier.identifierCompte(ccompte, cpasse);
			model.addAttribute("compte", cp);
			
			Page<Operation> pOperation=banqueMetier.listeOperation(ccompte,page,size);
			model.addAttribute("pOperation", pOperation.getContent());
			
			int[] pages=new int[pOperation.getTotalPages()];
			model.addAttribute("pages", pages);
		
		}catch(Exception e) {
				model.addAttribute("exception", e);			}
		
		return "compte";
	}
	
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,String ccompte,String ccompte2,double montant)
	{	
		
		
		try {
			
		
		
		if(typeOperation.equals("VERS"))
	{
		banqueMetier.verser(ccompte, montant);
	}
	else if(typeOperation.equals("RETR"))
	{
		banqueMetier.retirer(ccompte, montant);
	}
	if(typeOperation.equals("VIR"))
	{
		banqueMetier.virement(ccompte, ccompte2, montant);;
	}
		
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCompte?ccompte="+ccompte+"&error="+e.getMessage();
		}
		
	
	return "redirect:/consulterCompte?ccompte="+ccompte;
	}
		

/*********/
	@RequestMapping(value="/enregistrement",method=RequestMethod.GET)	
	public String enregistrement(Model model)
	{	
		model.addAttribute("Cl",new Client());
		return "enregistrement";

	}
	
		
	
	
	
/***************************************/
/************Client*********************/
/***************************************/

@RequestMapping(value="/clients",method=RequestMethod.GET)	
public String clients()
{	
	return "clients";

}

@RequestMapping("/infoClient")
public String infoClient(Model model,String cclient,
		@RequestParam(name="page",defaultValue="0") int page,
		@RequestParam(name="size",defaultValue="5") int size)
{
	
	model.addAttribute("cclient", cclient);
	try {
		Client cl=banqueMetier.infoClient(cclient);
		model.addAttribute("client1", cl);
		
		Page<Compte> listeCompte=banqueMetier.listeCompte(cclient, page, size);
		model.addAttribute("listeCompte", listeCompte.getContent());
		
			
	}catch(Exception e) {
			model.addAttribute("exception1", e);			}
	
	return "clients";
}

@RequestMapping(value="/saveClient",method=RequestMethod.POST)	
public String saveClient(Model model ,Client Cl)
{	
	/*if(bindingResult.hasErrors())
		{
			return "clients";
		}*/
	
	clientR.save(Cl);
	banqueMetier.valider(Cl.getCodeClient());
	model.addAttribute("Cl1", Cl);
	return "confirmation";

}


@RequestMapping("/Operation")
public String operation(Model model,String cclient,
		@RequestParam(name="page",defaultValue="0") int page,
		@RequestParam(name="size",defaultValue="5") int size)
{	
	Page<Operation> listeOperation=opR.findAll(new PageRequest(page, size));
	model.addAttribute("listeOperation", listeOperation.getContent());
	
	int[] pages=new int[listeOperation.getTotalPages()];
	model.addAttribute("pages", pages);
	
	return "Operation";
}






}