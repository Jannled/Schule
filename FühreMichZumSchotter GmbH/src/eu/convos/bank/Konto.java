package eu.convos.bank;

import java.util.ArrayList;
import java.util.List;

public class Konto 
{
	private Kunde besitzer;
	private int kontoNummer;
	private float kontoStand;
	
	private List<Transaktion> transaktionen = new ArrayList<Transaktion>();
	
	private static int kontoNummern = 70529000;
	
	public Konto(Kunde kunde)
	{
		this.besitzer = kunde;
		this.kontoStand = 0;
		this.kontoNummer = ++kontoNummern;
	}
	
	public Konto(Kunde kunde, int kontoNummer)
	{
		this.besitzer = kunde;
		this.kontoNummer = kontoNummer;
		this.kontoStand = 0;
	}
	
	public Konto(Kunde kunde, float startkapital)
	{
		this.besitzer = kunde;
		this.kontoStand = startkapital;
		this.kontoNummer = ++kontoNummern;
	}
	
	public Konto(Kunde kunde, float startkapital, int kontoNummer)
	{
		this.besitzer = kunde;
		this.kontoNummer = kontoNummer;
		this.kontoStand = startkapital;
	}

	public void einzahlen(float betrag)
	{
		if(betrag < 0)
		{
			System.err.printf("einzahlen(%.2f) wurde zu einem positiven Wert korrigiert für das Konto: \n\t %s\n", betrag, toString());
			betrag = -betrag;
		}
			
		System.out.printf("Zahle %.2f€ ein.\n", betrag);
		transaktionen.add(new Transaktion(this, null, betrag));
		kontoStand += betrag;
	}
	
	public boolean abheben(float betrag)
	{
		if(betrag < 0)
		{
			System.err.printf("abheben(%f) wurde zu einem positiven Wert korrigiert für das Konto: \n\t %s\n", betrag, toString());
			betrag = -betrag;
		}
		
		if((kontoStand - betrag) < 0)
		{
			System.err.printf("Die %.2f€ konnten nicht vom Konto[%d] abgebucht werden, sie würde das Konto um %.2f€ überziehen!\n", 
					betrag, kontoNummer, betrag-kontoStand);
			return false;
		}
			
		System.out.printf("Hebe %.2f€ ab.\n", betrag);
		transaktionen.add(new Transaktion(this, null, -betrag));
		kontoStand -= betrag;
		return true;
	}
	
	public boolean ueberweisen(Konto ziel, float betrag)
	{
		if(ziel == null)
		{
			System.err.printf("Die %.2f€ konnten nicht vom Konto[%d] überwiesen werden, das Zielkonto ist null!\n", 
					betrag, kontoNummer, betrag-kontoStand);
		}
		
		if(betrag < 0)
		{
			System.err.printf("ueberweisen(%f) wurde zu einem positiven Wert korrigiert für das Konto: \n\t %s\n", betrag, toString());
			betrag = -betrag;
		}
		
		if((kontoStand - betrag) < 0)
		{
			System.err.printf("Die %.2f€ konnten nicht vom Konto[%d] überwiesen werden, sie würde das Konto um %.2f€ überziehen!\n", 
					betrag, kontoNummer, betrag-kontoStand);
			return false;
		}
		
		System.out.printf("Überweise %f von %s Konto[%d] nach %s Konto[%d]\n", 
				betrag, besitzer.getName(), kontoNummer, ziel.getBesitzer().getName(), ziel.getKontoNummer());
		transaktionen.add(new Transaktion(this, ziel, -betrag));
		kontoStand -= betrag;
		ziel.ueberweisungEmpfangen(this, betrag);
		return true;
	}
	
	protected void ueberweisungEmpfangen(Konto start, float betrag)
	{
		transaktionen.add(new Transaktion(start, this, betrag));
		kontoStand += betrag;
	}
	
	public Kunde getBesitzer() {
		return besitzer;
	}

	public int getKontoNummer() {
		return kontoNummer;
	}

	public float getKontoStand() {
		return kontoStand;
	}
	
	public List<Transaktion> getTransaktionen()
	{
		return transaktionen;
	}
	
	@Override
	public String toString() 
	{
		return String.format("Konto[%d] von %s, Kontostand: %.2f€.", kontoNummer, besitzer.getName(), kontoStand);
	}
	
}
