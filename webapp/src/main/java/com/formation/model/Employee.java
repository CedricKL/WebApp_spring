package com.formation.model;

import lombok.Data;

// Cette classe représente notre employé dans l'API, elle n'est pas obligée de lui ressembler!!

@Data
public class Employee {

	private Integer id;
	
	private String firstname;
	
	private String lastname;
	
	private String password;
	
	private String mail;

}
