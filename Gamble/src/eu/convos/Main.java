package eu.convos;

import java.util.Arrays;
import java.util.Scanner;

public class Main 
{
	public static boolean quick = true;
	public static final String[] quickNames = {"MeyerW", "TheKlammt", "Karsten", "DDRSipp", "Mr. K", "Heiopei"};
	public static final int max = 1000;
	
	public static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		in.useDelimiter("\r?\n|\r"); //Akzeptiere nur Zeilenumbrüche als Bestätigung der Eingabe
		
		System.out.println(">>>>       Gamble       <<<<");
		
		//Soll nach Namen gefragt werden?
		System.out.print("Soll jeder Spieler seinen Namen eingeben? (j/n)");
		if(in.next().trim().toCharArray()[0] == 'j')
			quick = false;
		
		//Hole Anzahl der Spieler
		System.out.print("Bitte gib die Zahl der Spieler ein: ");
		Spieler[] spieler = new Spieler[readInt()];
		System.out.println();
		for(int i=1; i<spieler.length+1; i++)
		{
			String name;
			
			//Wenn quick == false muss jeder Spieler seinen Namen eingeben, sonst wird einer vorgenerierte Liste genutzt
			if(!quick)
			{
				name = "Spieler "+ i;
				System.out.print(name + ", bitte gib deinen Namen ein: ");
				name = in.next();
			}
			else name = quickNames[i % quickNames.length] + ((i>quickNames.length) ? i/quickNames.length : "");
			
			//Frage den Spieler nach seiner Glückszahl
			System.out.print(name + ", Bitte gib deinen Tipp ab: ");
			int gluecksZahl = 0;
			do { //Solange bis der Spieler eine Zahl von 0 bis 1000 eingibt
				gluecksZahl = readInt();
				if(gluecksZahl < 0 || gluecksZahl > max)
					System.out.println("Bitte gib eine Zahl von 0 bis " + max + " ein! ");
			} while (gluecksZahl < 0 || gluecksZahl > max);
			
			//Erstelle neuen Spieler
			spieler[i-1] = new Spieler(name, gluecksZahl);
			System.out.println();
		}
		
		//Fülle Array mit Dees Zufallszahlen
		int[] dee = new int[10];
		for(int i=0; i<dee.length; i++)
		{
			dee[i] = zufallsZahl();
		}
		
		//Beginne mit der Auswertung
		System.out.println();
		System.out.println("Dee hat gewählt: " + Arrays.toString(dee));
		
		for(Spieler s : spieler)
		{
			int minAbstand = Integer.MAX_VALUE;
			
			//Berechne den kleinsten Abstand der Zahl
			for(int i=0; i<dee.length; i++)
			{
				int zahl = Math.abs(dee[i] - s.getZahl());
				if(zahl < minAbstand)
					minAbstand = zahl;
			}
			
			//Gib Ergebniss für den Spieler aus
			System.out.println(s.getName() + " bekommt " + minAbstand + "€ ausgezahlt!");
		}
		
		System.out.println("<<<<       Gamble       >>>>");
	}
	
	public static int readInt()
	{
		while(in.hasNext() && !in.hasNextInt())
		{
			System.out.println("\"" + in.next() + "\" ist keine gültige Zahl! ");
		}
		return in.nextInt();
	}
	
	public static int zufallsZahl()
	{
		return (int) (Math.random() * (max+2) - 1);
	}
}
