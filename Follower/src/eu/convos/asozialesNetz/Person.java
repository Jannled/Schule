package eu.convos.asozialesNetz;

import java.util.ArrayList;

public class Person 
{
	private String name;
	ArrayList<Person> folgt;
	
	public Person(String name)
	{
		this.name = name;
		folgt = new ArrayList<Person>();
	}
	
	public Person folgePerson(Person p)
	{
		folgt.add(p);
		return this;
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString() {
		return "\"" + name + "\" folgt " + folgt.size() + " Personen";
	}
}
