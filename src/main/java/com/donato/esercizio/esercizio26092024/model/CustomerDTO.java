package com.donato.esercizio.esercizio26092024.model;

import com.donato.esercizio.esercizio26092024.entity.Rental;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CustomerDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;

	private List < Rental > rentals;
}
