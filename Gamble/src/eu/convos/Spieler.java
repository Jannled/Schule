package eu.convos;

public class Spieler 
{
	private String name;
	private int zahl;
	
	public Spieler(String name, int zahl)
	{
		this.name = name;
		this.zahl = zahl;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getZahl()
	{
		return zahl;
	}
}
