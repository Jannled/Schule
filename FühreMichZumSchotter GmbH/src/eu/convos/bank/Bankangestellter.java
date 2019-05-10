package eu.convos.bank;

public class Bankangestellter 
{
	static void letzteTransaktionen(Konto k)
	{
		System.out.printf("Kontoverlauf für das Konto[%d] von %s, aktueller Kontostand: %.2f€\n", k.getKontoNummer(), k.getBesitzer().getName(), k.getKontoStand());
		for(int i=10; i>0; i--)
		{
			System.out.printf("%5d: ", k.getTransaktionen().size() - i);
			
			if(k.getTransaktionen().get(k.getTransaktionen().size() - i) != null)
				System.out.printf("%s\n", k.getTransaktionen().get(k.getTransaktionen().size() - i));
		}
	}
}
