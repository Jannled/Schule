package eu.convos;

import java.util.Arrays;
import java.util.Iterator;

public class Polygon implements Iterable<Point> 
{
	Point[] ecken;
	
	public Polygon(Point[] ecken)
	{
		this.ecken = ecken;
	}
	
	public Polygon(int[] ecken)
	{
		if((ecken.length % 2) != 0)
			throw new IllegalArgumentException("Uneven amount of coordinates for a 2D construct, got " + ecken.length + "!");
		
		this.ecken = new Point[ecken.length/2];
		
		for(int i=0; i<ecken.length; i+=Point.DIMENSION)
			this.ecken[i/2] = new Point(ecken[i], ecken[i+1]);
	}
	
	public int anzahlKoordinaten()
	{
		return ecken.length;
	}
	
	public Point getKoordinate(int pos)
	{
		if(pos < 0 || pos >= ecken.length)
			throw new IllegalArgumentException("The given coordinate at index " + pos + " could not be found!");
		
		return ecken[pos];
	}
	
	/**
	 * Check if the line segment given by the two points intersect with the polygon.
	 * @param begin The beginnign of the line segment.
	 * @param end The end of the line segment.
	 * @return True if the line intersects with one of the edges, false if not.
	 */
	public boolean intersects(Point begin, Point end)
	{
		for(int i=0; i<anzahlKoordinaten(); i++)
		{
			Point p2 = getKoordinate((i)   % anzahlKoordinaten());
			Point q2 = getKoordinate((i+1) % anzahlKoordinaten());
			
			if(Mathe.intersectingSegments(begin, end, p2, q2))
				return true;
		}
		
		return false;
	}

	@Override
	public Iterator<Point> iterator() {
		return Arrays.stream(ecken).iterator();
	}
}
