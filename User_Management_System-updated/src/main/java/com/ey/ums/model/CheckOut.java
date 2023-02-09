package com.ey.ums.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CheckOut {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String firstName;
	private String lastName;
	private String state;
	private String streetAddress;
	private String town;
	private String postCode;
	private String phone;
	private String email;
	

}
