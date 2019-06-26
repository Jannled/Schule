package eu.convos.bank;

import java.util.Scanner;

public class Main 
{
	static Scanner scanner = new Scanner(System.in);
	static Kunde[] kunden = new Kunde[100];
	static int kundenAnzahl;
	
	public Main()
	{
		boolean running = true;
		while(running) {
			System.out.println();
			System.out.println(" ------Hauptmenü------");
			System.out.println("1 Kunde Wählen");
			System.out.println("2 Kunde anlegen");
			System.out.println("  Sonstige Eingaben beenden das Programm");
			
			String eingabe = scanner.next();
			
			switch (eingabe) {
			//Kundenmenü
			case "1":
				kundenMenue();
				break;
				
			//Neuen Kunden anlegen
			case "2":
				System.out.println("------Neuen Kunden anlegen------");
				System.out.print("Bitte geben sie den Vornamen ein: ");
				String vorname = scanner.next();
				System.out.print("Bitte geben sie den Nachnamen ein: ");
				kunden[kundenAnzahl++] = new Kunde(vorname, scanner.next());
				break;

			//Hauptmenü beenden
			default:
				running = false;
			}
		} 
		
	}
	
	public void kundenMenue()
	{
		boolean kundenSchleife = true;
		
		while(kundenSchleife)
		{
			System.out.println("  ------Kunden Wählen------");
			for(int i=0; i<kundenAnzahl; i++)
			{
				System.out.printf("%2d %s %n", i, kunden[i].getName());
			}
			
			int eingabe = -1;
			while(eingabe < 0 || eingabe > kundenAnzahl)
			{
				eingabe = readInt();
			}
			
			System.out.println(" ------" + kunden[eingabe].getName() + "------");
			System.out.println("1 Konto wählen");
			System.out.println("2 Konto eröffnen");
			System.out.println("3 Konten anzeigen");
			System.out.println("  Sonstige Eingaben verlassen das Kundenmenü.");
			
			switch (scanner.next()) {
			//Kontomenü
			case "1":
				kontoMenue(kunden[eingabe]);
				break;
	
			//Neues Konto erstellen
			case "2":
				System.out.println("Neues Konto erstellen!");
				System.out.print("Bitte geben sie eine Kontonummer ein: ");
				kunden[eingabe].kontoAnlegen(readInt());
				break;
				
			//Alle Konten anzeigen
			case "3":
				kunden[eingabe].kontenAnzeigen();
				break;
				
			//Konto Menü beenden
			default:
				kundenSchleife = false;
			}
		}
	}
	
	public void kontoMenue(Kunde k)
	{
		System.out.printf("%" + (Kunde.maxKonten/10 + 1) + "d------%s------%n", k.getName());
		k.kontenAnzeigen();
		int wahlKonto = readInt();
		while(wahlKonto < 0 || wahlKonto > k.anzKonten())
		{
			System.out.println("Ungültige Eingabe");
		}
		
		boolean kontoSchleife = true;
		while(kontoSchleife)
		{
			System.out.println(" ------Konto Menü------");
			System.out.println("1 Einzahlung");
			System.out.println("2 Abbuchung");
			System.out.println("  Sonstige Eingaben beenden das Konto Menü");
			
			switch (scanner.next()) {
			//Betrag einzahlen
			case "1":
				System.out.print("Geben sie den Betrag an, den sie einzahlen möchten: ");
				k.getKonto(wahlKonto).einzahlen(readInt());
				break;
				
			//Betrag auszahlen
			case "2":
				System.out.print("Geben sie den Betrag an, den sie abheben möchten: ");
				k.getKonto(wahlKonto).abheben(readInt());
				break;

			//Konto Menü beenden
			default:
				kontoSchleife = false;
			}
		}
	}
	
	public static void main(String[] args)
	{
		kunden[0]=new Kunde("Willi", "Winkler");
        kunden[1]=new Kunde("Heinz", "Erwinkel");
        kunden[2]=new Kunde("Peter", "Peters");
        kundenAnzahl++;
        kundenAnzahl++;
        kundenAnzahl++;
        
        kunden[0].kontoAnlegen(7777);
        kunden[1].kontoAnlegen(8888);
        kunden[0].kontoAnlegen(9999);
        
        kunden[0].getKonto(0).einzahlen(100);
        kunden[1].getKonto(0).abheben(100);
        
        new Main();
        System.out.println("Beende Programm!");
        scanner.close();
	}
	
	/**
	 * Frage so lange den Nutzer nach einer Eingabe bis dieser eine Zahl eintippt
	 * @return Die eingegebene Zahl
	 */
	public static int readInt()
	{
		while(scanner.hasNext() && !scanner.hasNextInt())
		{
			System.out.println("\"" + scanner.next() + "\" ist keine gültige Zahl! ");
		}
		return scanner.nextInt();
	}
}
