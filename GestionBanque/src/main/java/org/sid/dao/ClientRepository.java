package org.sid.dao;

import org.sid.entite.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface ClientRepository  extends JpaRepository<Client, Long>{
	@Query("select c from Client c where identifiantClient like :x")
	public Client chercher(@Param("x")String Code);
	@Modifying
	@Query(nativeQuery=true, value = "insert into users (username, password, active) select :username,MD5(:password),:active")
	public void inserer(@Param("username")String username,@Param("password")String password,@Param("active")Integer active);

	
	@Modifying
	@Query(nativeQuery=true, value = "insert into userRole (usrname,role) select :usrname,:role")
	public void insererRole(@Param("usrname")String username,@Param("role")String role);

	
}	
	

