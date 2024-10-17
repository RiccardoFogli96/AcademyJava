package com.library.course.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class CustomerDTO{

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
}
