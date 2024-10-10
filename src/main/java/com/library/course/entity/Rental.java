package com.library.course.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Rental {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column(name = "rent_id")
	private Long rentId;

	@Column(name = "start_date", nullable = false)
	private LocalDateTime startDate;
	@Column(name = "end_date", nullable = false)
	private LocalDateTime endDate;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
}
