package eu.convos.bank;

public class Main 
{
	
	public static void main(String[] args)
	{
		Kunde k = new Kunde("Lol");
		Konto konto = k.kontoAnlegen(1000);
		System.out.println(konto.toString());
		konto.einzahlen(150);
		konto.abheben(200);
		konto.abheben(20);
		System.out.println(konto.toString());
		
		
		k.kontoAnlegen();
		k.kontoAnlegen(100.225f);
		k.kontoAnlegen();
		
		k.kontoAnlegen();
		
		Kunde k2 = new Kunde("Lel");
		Konto konto2 = k2.kontoAnlegen(10.10f);
		
		konto.ueberweisen(konto2, 0);
		konto.ueberweisen(konto2, 10);
		konto.ueberweisen(konto2, 20);
		konto.ueberweisen(konto2, 30);
		konto.ueberweisen(konto2, 40);
		konto.ueberweisen(konto2, 50);
		konto.ueberweisen(konto2, 60);
		konto.ueberweisen(konto2, 70);
		konto.ueberweisen(konto2, 80);
		konto.ueberweisen(konto2, 90);
		konto.ueberweisen(konto2, 100);
		konto.ueberweisen(konto2, 0);
		
		konto2.abheben(666.66f);
		konto2.abheben(66.66f);
		konto2.einzahlen(10);
		
		System.out.println("\n\n");
		
		Bankangestellter.letzteTransaktionen(konto);
		Bankangestellter.letzteTransaktionen(konto2);
	}
}
