package service_center.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import tel_ran.persons.dao.Address;
import tel_ran.persons.dao.Person;
import tel_ran.persons.interfaces.IPersonsRepository;

public class GeneratorPerson {
	private String[] names = { "Jacob", "Tyler", "Lauren", "Sophia", "Alexander", "Alyssa", "David", "Grace",
			"Nicholas", "Sarah", "Ryan", "Alexis", "Anthony", "Elizabeth", "Christopher", "Joseph", "Samantha",
			"Samantha", "Ashley", "Daniel", "Isabella", "Andrew", "Abigail", "Hannah", "Ethan", "Olivia", "Matthew",
			"Madison", "Joshua", "Emma", "Michael", "Emily" };
	private Map<String, String[]> countries = new HashMap<String, String[]>();
	private String[] streets = { "Lenina", "Rothschild", "Yerushalayim", "Ben Gurion", "Allenby", "Dizengoff",
			"HaArbaa", "HaMasger", "HaYarkon", "Ibn Gabirol" };
	private Random gen = new Random();

	public GeneratorPerson(int count, IPersonsRepository prs) {
		String[] cities = { "Sydney", "Albury", "Armidale", "Bathurst", "Blue Mountains", "Broken Hill", "Campbelltown",
				"Dubbo" };
		countries.put("Australia", cities);
		String[] cities1 = { "Minsk", "Barysaw", "Salihorsk", "Maladzyechna", "Zhodzina", "Vitebsk" };
		countries.put("Belarus", cities1);
		String[] cities2 = { "Haifa", "Tel Aviv", "Eilat", "Jerusalem", "Beer Sheva", "Rehovot" };
		countries.put("Israel", cities2);
		for(int i=0; i < count; i++)
			prs.addPerson(genPerson());
	}
	
	public Person genPerson() {		
		String country=genCountry();
		String city=genFromArray(countries.get(country));
		String street=genFromArray(streets);
		String building = Integer.toString(genInt(50));
		int app=genInt(255);	
		String name = genFromArray(names);
		Address address = new Address();
		address.setApp(app);
		address.setCountry(country);
		address.setCity(city);
		address.setBld(building);
		address.setStreet(street);
		Person person = new Person();
		person.setName(name);
		person.setAddress(address);
		return person;
	}

	private String genCountry() {
		String[] country = countries.keySet().toArray(new String[countries.keySet().size()]);
		return genFromArray(country);
	}

	private String genFromArray(String[] array) {
		return array != null ? array[gen.nextInt(array.length)] : null;
	}

	private int genInt(int max) {
		return max > 0 ? 1 + gen.nextInt(max) : 1 + gen.nextInt(255);
	}
}
