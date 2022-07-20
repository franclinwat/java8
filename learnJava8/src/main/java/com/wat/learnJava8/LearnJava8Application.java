package com.wat.learnJava8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SpringBootApplication
public class LearnJava8Application {

	Consumer<String> cons = (a) -> System.out.println(a); // cons.accept(a);
	Supplier<String> supplier = () -> {
		return "tttttt";
	}; // supplier.get();
	Predicate<String> t = (a) -> a.equals("uyuytr"); // t.test(t);
	Function<String, String> funct = (a) -> "re"; // funct.apply("")

	public static void main(String[] args) {
		SpringApplication.run(LearnJava8Application.class, args);
	}

	@Bean
	public CommandLineRunner start() {
		return args -> {
			cons.accept("CECI est un test");
			String resultGet = supplier.get();
			System.out.println("resultGet " + resultGet);
			boolean result = t.test("uyuytr");
			System.out.println("checked: " + result);
			String resultFunc = funct.apply(" ");
			System.out.println("resultFunc " + resultFunc);

			System.out.println(" ############################################## ");
			testStream();

		};
	}

	public void testStream() {

		List<Etudiante> etudiants = List.of(Etudiante.builder().name("a").note(10).build(),
				Etudiante.builder().name("b").note(5).build(), Etudiante.builder().name("c").note(6).build(),
				Etudiante.builder().name("d").note(6).build(), Etudiante.builder().name("e").note(7).build());

		List<Toy> toys = List.of(
				Toy.builder().name("4 Fantastique").price(35).description("figuring of 4 fantastion from marvel")
						.promot(30).build(),
				Toy.builder().name("Elsa").price(50).description("figuring  frozen from disney").promot(40).build(),
				Toy.builder().name("Mikey").price(10).description("figuring Mikey house from disney").promot(9).build(),
				Toy.builder().name("Iory yagami").price(150).description("figuring of KOF from SNK").promot(19).build(),
				Toy.builder().name("Ken").price(7).description("figuring of Street fighters  from capcom").promot(18)
						.build());

		// foreach toys
		toys.stream()
		.forEach(System.out::println);
		System.out.println("-------------------------------------------");

		// map toys and collector.toList
		List<Toy> encreasePromotion = toys.stream()
				.map(toy -> new Toy(toy.getName(), toy
				.getPrice(), toy.getDescription(), toy
				.getPromot() * 2

				))
				.collect(Collectors.toList());

		encreasePromotion.forEach(toy -> System.out.println(toy));

		// filter and increase toy with price <= 10

		List<Toy> filterAndIncrease = toys.stream()
				.filter(toy -> toy.getPrice() <= 10)
				.map(toyss -> new Toy(toyss.getName(), toyss
				.getPrice(), toyss
				.getDescription(), toyss
				.getPromot() * 3))
				.collect(Collectors.toList());

		/*
		 * to find the first toys with prise <= 10
		
		 * .findFirst() 
		 * .orElse(null);
		 */

		System.out.println("***********************************");

		filterAndIncrease.forEach(toy -> System.out.println(toy));

		/*
		 * int sum = 0; List<Integer> notes = new ArrayList<>(); for(Etudiante etudiant
		 * : etudiants) { int note = etudiant.getNote(); if(note > 5) { sum += note;
		 * notes.add(note); } }
		 */

		
		
		// short  circuit opeation , skip, lmit
	List<Toy> shortcircuit=	toys.stream()
				
				.skip(1)
				.limit(3)
				.collect(Collectors.toList());
		
				System.out.println();
		
				System.out.println("***********************************");

				filterAndIncrease.forEach(toy -> System.out.println(toy));
		
		
		//Finate Data
				System.out.println("***********************************");
				Stream.generate(Math::random)
				.limit(7)
				.forEach(value -> System.out.println(value));
		
			//sorting toy by name 
				
				List<Toy> sortToys=	toys.stream()
				    .sorted((o1,o2) -> o1.getName()
					.compareToIgnoreCase(o2.getName()))
				    .collect(Collectors.toList());
				
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

				sortToys.forEach(toy -> System.out.println(toy));
		
		
		//min or max price ,compare price and sort by price
				
				Toy maxPrice= toys.stream()
					.max(Comparator.comparing(Toy::getPrice))
					.orElseThrow(NoSuchElementException::new);
				System.out.println("+***********************************************");
				
				System.out.println(maxPrice);
					
					

					
		
					//reduce operation to accumulate the total of promotion 
					
		double totalPromotion = toys.stream()
						.map(toy ->toy.getPromot())
						.reduce(0.0,Double::sum);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

	         System.out.println(totalPromotion +"%");
					
					
					
					
					
		
		
		
		
		List<Integer> notes = etudiants.stream().filter(etudiant -> etudiant.getNote() > 5)
				// .sorted(Comparator.reverseOrder())
				.map(etudiant -> etudiant.getNote())
				// mapToInt(etudiant -> etudiant.getNote())
				// flatmap
				.distinct() // =>Intermediaire OP
				.skip(1)
				// .limit(1)
				.collect(Collectors.toList()); // =>Final OP
		// .forEach(System.out::println); // =>Final OP

		System.out.println(notes);

	}

	/*
	 * public void toyStream() {
	 * 
	 * //instance de l'objet toys a l'aide de builder de java 8 et a l'aide List.of
	 * de java 11) List<Toy> toys= List.of(
	 * Toy.builder().name("4 Fantastique").price(35).
	 * description("figuring of 4 fantastion from marvel").promot(30).build(),
	 * Toy.builder().name("Elsa").price(50).
	 * description("figuring  frozen from disney").promot(40).build(),
	 * Toy.builder().name("Mikey").price(10).
	 * description("figuring Mikey house from disney").promot(9).build(),
	 * Toy.builder().name("Iory yagami").price(150).
	 * description("figuring of KOF from SNK").promot(120).build(),
	 * Toy.builder().name("Ken").price(85).
	 * description("figuring of Street fighters  from capcom").promot(55).build());
	 * 
	 * 
	 * System.out.println("*****************************************************");
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * public void testclasse() {
	 * 
	 * Toy toyss=new Toy("4 Fantastique",6666," je ne esai",30);
	 * System.out.println(toyss.getDescription());
	 * 
	 * }
	 */

}
