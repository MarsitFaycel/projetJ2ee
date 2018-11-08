package org.sid.dao;

import org.sid.entite.Compte;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompteRepository extends JpaRepository<Compte, Long>{
	@Query("select c from Compte c where codeCompte like :x")
	public Compte chercher(@Param("x")String Code);
	@Query("select c from Compte c where c.client.identifiantClient=:x order by c.dateCreation desc")
	public Page<Compte> listeC(@Param("x")String idC,Pageable pageable);
	@Query("select c from Compte c where codeCompte like :x and passCompte like :y")
	public Compte chercher(@Param("x")String Code,@Param("y")String pass);
}
