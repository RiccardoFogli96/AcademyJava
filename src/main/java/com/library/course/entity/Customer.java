package com.library.course.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Customer {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	private String lastName;
	private String email;

	@OneToMany(mappedBy = "customer")
	private List<Rental> rentals;

}
