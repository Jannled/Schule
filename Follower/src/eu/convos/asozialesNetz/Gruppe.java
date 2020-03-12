package eu.convos.asozialesNetz;

import java.util.ArrayList;
import java.util.Iterator;

public class Gruppe implements Iterable<Person>
{
	Person star = null;
	ArrayList<Person> mitglieder;
	
	public Gruppe()
	{
		mitglieder = new ArrayList<Person>();
	}
	
	public void addMitglied(Person p)
	{
		mitglieder.add(p);
	}
	
	@Override
	public String toString() {
		return "Die Gruppe hat " + mitglieder.size() + " Mitglieder, der Star ist: " + (star==null ? "-" : star.toString()) + ".";
	}

	@Override
	public Iterator<Person> iterator() {
		return mitglieder.iterator();
	}
}
