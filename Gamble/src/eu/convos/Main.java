package eu.convos;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main 
{
	public static final String[] quickNames = {"MeyerW", "TheKlammt", "Karsten", "DDRSipp", "Mr. K", "Heiopei"};
	public static final int max = 1000;
	public static final int einsatz = 25;
	
	public static Scanner in = new Scanner(System.in);
	public static File workDir = new File("");
	public static JFileChooser fileChooser;
	
	public static int[] dee;
	public static int deesGewinn;
	public static Spieler[] spieler;
	
	public static void main(String[] args) throws IOException
	{
		//Nutze das Design des Ausführenden Betriebssystems
		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		in.useDelimiter("\r?\n|\r"); //Akzeptiere nur Zeilenumbrüche als Bestätigung der Eingabe
		
		//Setze Dateiauswahldialog auf das Verzeichniss der Ausführbaren Datei
		System.out.println("Arbeitsverzeichniss: " + workDir.getAbsolutePath());
		fileChooser = new JFileChooser(workDir.getCanonicalFile()); //GetCanonicalFile wird benötigt da Java sonst rumbuggt...
				
		//Starte die Runde
		gambleRunde();
	}
	
	public static void gambleRunde()
	{
		System.out.println(">>>>       Gamble       <<<<");
		
		//Wenn ja wird nach einer Datei gefragt, wenn nein, wird nach der Anzahl der Spieler, den Namen und Tipps in der Konsole gefragt.
		System.out.print("Sollen die Spieler aus einer Datei eingelesen werden? (j/n): ");
		if(in.next().trim().toCharArray()[0] == 'j')
		{
			//Führe so lange aus bis die Datei erfolgreich gelesen wurde
			boolean successfull = false;
			do {
				//Lese Datei ein
				int returnVal = fileChooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					successfull = true;
					File file = fileChooser.getSelectedFile();
					System.out.println("Öffne: " + file.getAbsolutePath());
					
					int[] tipps = Einlesen.read(file);
					if(tipps == null)
					{	
						System.err.println("Die Datei \"" + file.getAbsolutePath() + "\" enthält ungültige Werte!");
						successfull = false;
					}
					else
					{
						spieler = new Spieler[tipps.length];
						for(int i=0; i<tipps.length; i++)
							spieler[i] = new Spieler((quickNames[i % quickNames.length] + ((i>quickNames.length) ? i/quickNames.length : "")), tipps[i]);
					}
				}
				else System.err.println("Bitte wähle eine Datei aus!");
			} while (!successfull);
		}
		else
		{
			//Hole Anzahl der Spieler
			int anzSpieler = 1;
			do {
				System.out.print("Bitte gib die Zahl der Spieler ein" + (anzSpieler < 1 ? " (die Zahl muss größer 0 sein!): " : ": "));
				anzSpieler = readInt();
			} while (anzSpieler < 1);
			
			System.out.println();
			spieler = new Spieler[anzSpieler];
			for(int i=1; i<spieler.length+1; i++)
			{
				//Frage den Spieler nach seinem Namen
				String name = "Spieler "+ i;
				System.out.print(name + ", bitte gib deinen Namen ein: ");
				name = in.next();
				
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
		}
		
		//Berechne den gesamten Einsatz den Dee bekommt
		deesGewinn = spieler.length * einsatz;
		
		//Fülle Array mit Dees Zufallszahlen
		dee = new int[10];
		for(int i=0; i<dee.length; i++)
		{
			dee[i] = zufallsZahl();
		}
		
		//Beginne mit der Auswertung
		System.out.println();
		System.out.println("Dee hat gewählt: " + Arrays.toString(dee) + ".\n");
		
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
			
			//Ziehe das Ergebniss von Dees Gewinn ab
			deesGewinn = deesGewinn - minAbstand;
		}
		
		System.out.println("\nDees Kontostand beträgt: " + deesGewinn + "€\n");
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
