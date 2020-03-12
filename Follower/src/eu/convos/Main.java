package eu.convos;

import java.io.File;

import eu.convos.asozialesNetz.AsozialesNetz;
import eu.convos.asozialesNetz.Gruppe;
import eu.convos.asozialesNetz.Person; 

public class Main 
{
	public static final int PING = 300; // Aufgabenstellung besagt 3000ms = 3 Sekunden
	
	static int anfragen = 0;
	
	public static void main(String[] args) 
	{
		//Wähle Datei von der Kommandozeile aus, oder wähle das Beispiel
		File f = new File(args.length>1 ? args[0] : "src/Follower3.txt");
		System.out.println(f.getAbsolutePath());
		System.out.println();
		
		AsozialesNetz zwitscher = new AsozialesNetz(f);
		findeStar(zwitscher.getGruppe(), zwitscher);
		
		System.out.printf("Habe %d Anfragen in %.2f Sekunden gestellt.%n", anfragen, (float) (anfragen*PING)/1000);
	}
	
	public static Person findeStar(Gruppe gruppe, AsozialesNetz asn)
	{
		kandidat:
		for(Person kandidat : gruppe)
		{
			System.out.println("Potenzieller Kandidat ist: " + kandidat.getName());
			if(kandidat != null)
			{
				//Überprüfe, ob alle Personen in der Gruppe der einen Person folgen
				for(Person follower : gruppe)
				{
					if(follower == kandidat)
						continue;
					
					if(!folgt(asn, follower, kandidat))
						continue kandidat; //Die Person wird nicht von allen gefolgt, die Person ist damit kein Star. Fahre fort...
				}
				
				//Überprüfe, ob die Person niemandem in der Gruppe folgt
				for(Person follower : gruppe)
				{
					if(follower == kandidat)
						continue;
					
					if(folgt(asn, kandidat, follower))
						continue kandidat; //Die Person folgt einer anderen Person in der Gruppe und ist damit kein Star. Fahre fort...
				}
				
				System.out.println();
				System.out.println("Der Star der Gruppe ist: \"" + kandidat.getName() + "\"!");
				return kandidat;
			}
		}
		
		System.out.println();
		System.out.println("In der Gruppe gibt es keinen Star!");
		return null;
	}
	
	private static boolean folgt(AsozialesNetz asn, Person a, Person b)
	{
		System.out.printf("Folgt %10s %10s? ", a.getName(), b.getName());
		try {
			Thread.sleep(PING);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		anfragen++;
		
		boolean antwort = asn.folgt(a, b);
		System.out.println(antwort ? "JA!" : "NEIN!");
		return antwort;
	}
}
