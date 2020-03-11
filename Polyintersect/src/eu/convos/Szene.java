package eu.convos;

import java.util.LinkedList;

public class Szene 
{
	Polygon[] polygone;
	
	//Wird benötigt um später die Szene Bildfüllend darzustellen
	int xmin, xmax, ymin, ymax; 
	
	/** Position of the virtual spectator */
	Point viewPoint; 
	
	/** Position of the target */
	Point targetPoint;
	
	public Szene(Polygon[] polygone, Point viewPoint)
	{
		this.viewPoint = viewPoint;
		this.polygone = polygone;
		
		this.targetPoint = new Point(0, 0);
		
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
	
	public Route minimalerWeg()
	{
		Route shortestPath = new Route();
		
		shortestPath.add(viewPoint);
		
		//Sammle die Polygone, die sich auf direktem Wege zum Ziel befinden
		LinkedList<Polygon> imweg = new LinkedList<Polygon>();
		
		for(Polygon p : getPolygone())
		{
			if(p.intersects(viewPoint, targetPoint))
				imweg.add(p);
		}
		
		//1. Einfachster Fall: Keine Objekte im Weg
		
		//2. Ein Objekt ist im Weg
		if(imweg.size() > 0)
		{
			
		}
		
		else return null;
		
		shortestPath.add(targetPoint);
		
		//Return result or error
		return shortestPath;
	}
	
	public Polygon[] getPolygone()
	{
		return polygone;
	}
}
