package eu.convos;

public class Szene 
{
	Polygon[] polygone;
	
	//Wird benötigt um später die Szene Bildfüllend darzustellen
	int xmin, xmax, ymin, ymax; 
	
	/** Position of the virtual spectator */
	Point viewPoint; 
	
	public Szene(Polygon[] polygone, Point viewPoint)
	{
		this.viewPoint = viewPoint;
		this.polygone = polygone;
		
		xmin = xmax = viewPoint.x;
		ymin = ymax = viewPoint.y;
		
		for(Polygon p : polygone)
		{
			for(int i=0; i<p.anzahlKoordinaten(); i++)
			{
				if(p.getKoordinate(i).x < xmin)
					xmin = p.getKoordinate(i).x;
				if(p.getKoordinate(i).x > xmax)
					xmax = p.getKoordinate(i).x;
				if(p.getKoordinate(i).y < ymin)
					ymin = p.getKoordinate(i).y;
				if(p.getKoordinate(i).y > ymax)
					ymax = p.getKoordinate(i).y;
			}
		}
		
		System.out.printf("xmin: %d, xmax: %d, ymin: %d, ymax %d %n", xmin, xmax, ymin, ymax);
	}
	
	public Polygon[] getPolygone()
	{
		return polygone;
	}
}
