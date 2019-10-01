package eu.convos;

public class Szene 
{
	Polygon[] polygone;
	int xmin, xmax, ymin, ymax; //Wird benötigt um später die Szene Bildfüllend darzustellen
	
	int viewX, viewY;
	
	public Szene(Polygon[] polygone, int viewX, int viewY)
	{
		xmin = xmax = viewX;
		ymin = ymax = viewY;
		
		for(Polygon p : polygone)
		{
			for(int x=0; x<p.anzahlKoordinaten(); x+= Polygon.DIMENSION)
			{
				if(p.getKoordinate(x) < xmin)
					xmin = p.getKoordinate(x);
				if(p.getKoordinate(x) > xmax)
					xmax = p.getKoordinate(x);
			}
			
			for(int y=1; y<p.anzahlKoordinaten(); y+= Polygon.DIMENSION)
			{
				if(p.getKoordinate(y) < ymin)
					ymin = p.getKoordinate(y);
				if(p.getKoordinate(y) > ymax)
					ymax = p.getKoordinate(y);
			}
		}
		
		this.polygone = polygone;
		this.viewX = viewX;
		this.viewY = viewY;
	}
	
	public Polygon[] getPolygone()
	{
		return polygone;
	}
}
