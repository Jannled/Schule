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
	
	public AsozialesNetz(File f)
	{
		nutzer = new ArrayList<Person>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(f)))
		{
			String line;
			
			while((line = br.readLine()) != null)
			{
				//Teile Zeile in Wörter
				String[] woerter = line.split(" ");
				if(woerter.length < 1)
				{
					System.err.println("Invalid line!");
					continue;
				}
				
				//Erstes Wort in der Zeile ist die zu bearbeitende Person
				Person person = getPerson(woerter[0]);
				
				//Wenn es die Person noch nicht gibt, füge sie hinzu
				if(person == null)
					person = addPerson(new Person(woerter[0]));
				
				//Bearbeite alle Follower in der Zeile
				for(int i=1; i<woerter.length; i++)
				{
					Person folgt = getPerson(woerter[i]);
					
					//Wenn es die Person noch nicht gibt, der gefolgt werden soll, füge diese hinzu.
					if(folgt == null)
						folgt = addPerson(new Person(woerter[i]));
					
					person.folgePerson(folgt);
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Person addPerson(Person p)
	{
		if(nutzer.add(p))
			return p;
		else
			return null;
	}
	
	public Person getPerson(String name)
	{
		for(Person p : nutzer)
		{
			if(p.getName().equals(name))
				return p;
		}
					
		return null;
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

	@Override
	public Iterator<Person> iterator() 
	{
		return nutzer.iterator();
	}
}
