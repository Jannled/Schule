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
		
		Point entrance = viewPoint;
		Point exit = targetPoint;
		
		do {
			imweg.clear();
			
			//Look for polygons, that are intersecting with the shortest way to the target 
			for(Polygon p : getPolygone())
			{
				if(p.intersects(entrance, exit))
					imweg.add(p);
			}
			
			//1. Easy case: No Polygons are in the way, shortest path is the direct one
			if(imweg.size() < 1)
				break;
			
			//2. Hard case: Find a way to bypass these
			//Select closest Polygon blocking the way
			Polygon closest = imweg.getFirst();
			Point closestPoint = closest.getKoordinate(0);
			double closestDistance = closestPoint.abstand(entrance);
			for(Polygon p : imweg)
			{
				for(Point point : p.ecken)
				{
					if(point.abstand(entrance) < closestDistance)
					{
						closest = p;
						closestPoint = point;
						closestDistance = point.abstand(entrance);
					}
				}
			}
			
			//Select closest vertice to target
			Point x = closestPoint;
			double closestX = x.abstand(exit);
			for(Point p : closest.ecken)
			{
				if(p.abstand(exit) < closestX)
				{
					x = p;
					closestX = x.abstand(exit);
				}
			}
				
			//Pick shortest way around current polygon
			float aLength = 0, bLength = 0;
			LinkedList<Point> aPath = new LinkedList<Point>();
			LinkedList<Point> bPath = new LinkedList<Point>();
			int index = 0;
			for(Point p : closest)
			{
				if(p == closestPoint) break;
				index++;
			}
			
			for(int i=index, j=0; j<closest.anzahlKoordinaten(); i++, j++)
			{
				if(closest.getKoordinate(Mathe.mod(i, closest.anzahlKoordinaten())) == x) break;
				aPath.add(closest.getKoordinate(Mathe.mod(i, closest.anzahlKoordinaten())));
				aLength += closest.getKoordinate(Mathe.mod(i, closest.anzahlKoordinaten()))
						.abstand(closest.getKoordinate(Mathe.mod(i+1, closest.anzahlKoordinaten())));
			}
			
			for(int i=index, j=0; j<closest.anzahlKoordinaten(); i--, j++)
			{
				if(closest.getKoordinate(Mathe.mod(i, closest.anzahlKoordinaten())) == x) break;
				bPath.add(closest.getKoordinate(Mathe.mod(i, closest.anzahlKoordinaten())));
				bLength += closest.getKoordinate(Mathe.mod(i, closest.anzahlKoordinaten()))
						.abstand(closest.getKoordinate(Mathe.mod(i+1, closest.anzahlKoordinaten())));
			}
			
			if(aLength < bLength)
				shortestPath.addAll(aPath);
			else
				shortestPath.addAll(bPath);
			
			//Append x to the path
			if(x != null)
				shortestPath.add(x);
			
			//New entrance will be the vertice with shortest distance to target of current polygon
			entrance = x;
		} while(imweg.size() > 0);
		
		shortestPath.add(targetPoint);
		
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
