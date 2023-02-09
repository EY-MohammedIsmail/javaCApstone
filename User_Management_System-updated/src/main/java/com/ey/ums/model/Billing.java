package com.ey.ums.model;


import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "billing")
public class Billing {
	@Id
	private int phone;
	private String firstName;
	private String lastName;
	private String state;
	private String streetAddress;
	private String town;
	private String postCode;
	
	private String email;

}
