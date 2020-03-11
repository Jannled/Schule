package eu.convos;

import java.util.Iterator;
import java.util.LinkedList;

public class Route implements Iterable<Point>
{
	private double length;
	private LinkedList<Point> path;
	
	public Route()
	{
		length = 0;
		path = new LinkedList<Point>();
	}
	
	public Route(LinkedList<Point> path)
	{
		this.path = path;
		length = calculateLength();
	}
	
	public void add(Point p)
	{
		path.add(p);
		length = calculateLength();
	}
	
	private double calculateLength()
	{
		Point last = null;
		double len = 0;
		
		for(Point p : path)
		{
			if(last != null)
			{
				len += last.abstand(p);
			}
			last = p;
		}
		
		return len;
	}
	
	public double getDistance()
	{
		return length;
	}
	
	public int size()
	{
		return path.size();
	}

	@Override
	public Iterator<Point> iterator() {
		return path.iterator();
	}
}
