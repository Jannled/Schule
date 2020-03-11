package eu.convos;

public class Point
{
	/** Only there to avoid magic numbers, DO NOT CHANGE. The software is hardcoded to 2D! */
	public static final int DIMENSION = 2;
	
	public int x;
	public int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double abstand(Point p)
	{
		if(p == null)
			return Double.NaN;
		
		int a = x - p.x;
		int b = y - p.y;
		
		return Math.sqrt(a*a + b*b);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Point))
			return false;
		Point p = (Point) o;
		return (x == p.x && y == p.y);
	}
	
	@Override
	public String toString() 
	{
		return String.format("(%d; %d)", x, y);
	}
}
