package eu.convos.bank;

public class Kunde 
{
	public static int maxKonten = 4;
	
	private String name;
	private Konto[] konten = new Konto[maxKonten];
	
	public Kunde(String name)
	{
		this.name = name;
	}
	
	public Kunde(String name, Konto konto)
	{
		this.name = name;
		konten[0] = konto;
	}
	
	public Kunde(String name, Konto[] konten)
	{
		this.name = name;
		this.konten[0] = (konten.length > 0) ? konten[0] : null;
		this.konten[1] = (konten.length > 1) ? konten[1] : null;
		this.konten[2] = (konten.length > 2) ? konten[2] : null;
		this.konten[3] = (konten.length > 3) ? konten[3] : null; //TODO Hardgecodet
		
		if(konten.length > 4)
			System.err.printf("Der Kunde %s wurde mit %d Konten erstellt, nur die ersten vier wurden genommen!\n", name, konten.length);
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
		
		System.err.printf("Konnte kein Konto erstellen, die Maximale Anzahl von %d Konten wurde für %s erreicht\n", maxKonten, name);
		return null;
	}
	
	public int anzKonten()
	{
		int anz = 0;
		for(Konto k : konten)
			if(k != null) anz++;
		
		return anz;
	}
	
	public String getName()
	{
		return name;
	}
}
