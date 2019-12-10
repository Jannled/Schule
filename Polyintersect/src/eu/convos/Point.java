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
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Point))
			return false;
		Point p = (Point) o;
		return (x == p.x && y == p.y);
	}
}
