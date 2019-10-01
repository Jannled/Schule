package eu.convos;

public class Polygon 
{
	//Nicht ändern sonst wirds buggy
	public static final int DIMENSION = 2;
	
	private int[] ecken;
	
	public Polygon(int[] ecken)
	{
		this.ecken = ecken;
	}
	
	public int anzahlKoordinaten()
	{
		return ecken.length;
	}
	
	public int getKoordinate(int pos)
	{
		if(pos < 0 || pos >= ecken.length)
			throw new IllegalArgumentException("Die angegebene Koordinate an Index " + pos + " konnte nicht gefunden werden!");
		
		return ecken[pos];
	}
}
