package eu.convos.bank;

public class Kunde 
{
	public static int maxKonten = 4;
	
	private String vorname;
	private String nachname;
	private Konto[] konten = new Konto[maxKonten];
	
	public Kunde(String vorname, String nachname)
	{
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
	public Kunde(String vorname, String nachname, Konto konto)
	{
		this.vorname = vorname;
		this.nachname = nachname;
		konten[0] = konto;
	}
	
	public Kunde(String vorname, String nachname, Konto[] konten)
	{
		this.vorname = vorname;
		this.nachname = nachname;
		this.konten[0] = (konten.length > 0) ? konten[0] : null;
		this.konten[1] = (konten.length > 1) ? konten[1] : null;
		this.konten[2] = (konten.length > 2) ? konten[2] : null;
		this.konten[3] = (konten.length > 3) ? konten[3] : null; //TODO Hardgecodet
		
		if(konten.length > 4)
			System.err.printf("Der Kunde %s wurde mit %d Konten erstellt, nur die ersten vier wurden genommen!\n", getName(), konten.length);
	}
	
	public Konto kontoAnlegen()
	{
		return kontoAnlegen(0);
	}
	
	public Konto kontoAnlegen(float startkapital)
	{
		for(int i=0; i<konten.length; i++)
		{
			if(konten[i] != null) continue;
			
			Konto k = new Konto(this, startkapital);
			konten[i] = k;
			System.out.printf("Erstelle neues %s\n", k.toString());
			return k;
		}
		
		System.err.printf("Konnte kein Konto erstellen, die Maximale Anzahl von %d Konten wurde für %s erreicht\n", maxKonten, getName());
		return null;
	}
	
	public Konto kontoAnlegen(int kontoNummer)
	{
		for(int i=0; i<konten.length; i++)
		{
			if(konten[i] != null) continue;
			
			Konto k = new Konto(this, kontoNummer);
			konten[i] = k;
			System.out.printf("Erstelle neues %s\n", k.toString());
			return k;
		}
		
		System.err.printf("Konnte kein Konto erstellen, die Maximale Anzahl von %d Konten wurde für %s erreicht\n", maxKonten, getName());
		return null;
	}
	
	public int anzKonten()
	{
		int anz = 0;
		for(Konto k : konten)
			if(k != null) anz++;
		
		return anz;
	}
	
	public Konto getKonto(int index)
	{
		if(index <= anzKonten())
			return konten[index];
		
		System.err.printf("%s besitzt kein Konto mit dem Index %d, er besitzt nur %d!", getName(), index, anzKonten());
		return null;
	}
	
	public void kontenAnzeigen()
	{
		for(int i=0; i<anzKonten(); i++)
		{
			System.out.printf("%" + (maxKonten/10 + 1) + "d %s %n", i, konten[i].toString());
		}
		System.out.println();
	}
	
	public String getName()
	{
		return vorname + " " + nachname;
	}
}
