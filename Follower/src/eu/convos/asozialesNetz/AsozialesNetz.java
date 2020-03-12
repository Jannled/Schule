package eu.convos.asozialesNetz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AsozialesNetz implements Iterable<Person>
{
	ArrayList<Person> nutzer;
	ArrayList<Gruppe> gruppen;
	
	public AsozialesNetz(File f)
	{
		nutzer = new ArrayList<Person>();
		gruppen = new ArrayList<Gruppe>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(f)))
		{
			String line;
			
			while((line = br.readLine()) != null)
			{
				//Teile Zeile in Wörter
				String[] woerter = line.split(" ");
				
				//Die Zeile ist ungültig
				if(woerter.length < 1)
				{
					System.err.println("Invalid line!");
					continue;
				}
				
				//Die Zeile enthält eine Gruppe
				else if(woerter.length > 2)
				{
					Gruppe g = new Gruppe();
					gruppen.add(g);
					
					for(String s : woerter)
						g.addMitglied(getPerson(s));
				}
					
				//Die Zeile enthält eine Person mit der zu folgenden Person
				else
				{
					getPerson(woerter[0]).folgePerson(getPerson(woerter[1]));
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void addPerson(Person p)
	{
		nutzer.add(p);
	}
	
	//Gibt die Person mit angegebenen Namen zurück oder null, wenn die Person nicht gefunden wurde
	public Person findPerson(String name)
	{
		for(Person p : nutzer)
		{
			if(p.getName().equals(name))
				return p;
		}
					
		return null;
	}
	
	//Gibt die Person mit angegebenen Namen zurück oder erstellt diese, wenn noch nicht vorhanden
	public Person getPerson(String name)
	{
		Person p = findPerson(name);
		if(p == null)
		{
			p = new Person(name);
			addPerson(p);
			return p;
		}
		else return p;
	}
	
	// Folgt die Person A Person B
	public boolean folgt(Person a, Person b)
	{
		if(a == null || b == null)
			return false;
		
		for(Person p : a.folgt)
		{
			if(b.equals(p))
				return true;
		}
		
		return false;
	}
	
	public Gruppe getGruppe()
	{
		if(gruppen.size() > 0)
			return getGruppe(0);
		return null;
	}
	
	public Gruppe getGruppe(int index)
	{
		return gruppen.get(index);
	}

	@Override
	public Iterator<Person> iterator() 
	{
		return nutzer.iterator();
	}
}
