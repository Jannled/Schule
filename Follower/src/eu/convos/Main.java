package eu.convos;

import java.io.File;

import eu.convos.asozialesNetz.AsozialesNetz;
import eu.convos.asozialesNetz.Person;

public class Main 
{
	public static void main(String[] args) 
	{
		File f = new File("src/Follower3.txt");
		System.out.println(f.getAbsolutePath());
		
		AsozialesNetz assi = new AsozialesNetz(f);
		
		System.out.println("Lel");
	}
	
	public static void findeStar(AsozialesNetz asn)
	{
		for(Person p : asn)
		{
			System.out.println();
		}
	}
}
