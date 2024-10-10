package com.library.course.model;

import com.library.course.entity.Rental;
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
}
