package com.wat.learnJava8;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Toy {

	private String name;
	private double price;
	private String description;
	private double  promot;
}
