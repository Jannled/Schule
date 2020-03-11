package eu.convos;

import java.util.LinkedList;

public class Szene 
{
	Polygon[] polygone;
	
	//Wird ben�tigt um sp�ter die Szene Bildf�llend darzustellen
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
	
		LinkedList<Polygon> imweg = new LinkedList<Polygon>();
		
		
		
		//1. Einfachster Fall: Keine Objekte im Weg
		if(imweg.size() == 0)
		{
			
		}
		
		while(imweg.size() > 0)
		{
			//Look for polygons, that are intersecting with the shortest way to the target 
			for(Polygon p : getPolygone())
			{
				if(p.intersects(viewPoint, targetPoint))
					imweg.add(p);
			}
			
			//Find a way to bypass these
		}
		
		//2. Ein Objekt ist im Weg
		else if(imweg.size() > 0)
		{
			Point entrance = viewPoint;
			Point exit = targetPoint;

			Point x = null;
			double distance = Double.MAX_VALUE;
			
			//For every Polygon
			for(Polygon p : imweg)
			{	
				//Find Coordinate with minimal distance to exit, but is in sight of Entrance
				for(int i=0; i<p.anzahlKoordinaten(); i++)
				{
					if(!intersects(p.getKoordinate(i), entrance))
					{
						double dist = p.getKoordinate(i).abstand(exit);
						if(dist < distance)
						{
							x = p.getKoordinate(i);
							distance = dist;
						}
					}
				}
			}
			if(x != null)
				shortestPath.add(x);
		}
		
		else return null;
		
		shortestPath.add(targetPoint);
		
		//Return result or error
		return shortestPath;
	}
	
	public boolean intersects(Point a, Point b)
	{
		for(Polygon p : polygone)
		{
			if(p.intersects(a, b))
				return true;
		}
		return false;
	}
	
	public Polygon[] getPolygone()
	{
		return polygone;
	}
}
