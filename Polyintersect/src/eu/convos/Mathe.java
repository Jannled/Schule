package eu.convos;

public class Mathe
{
	/**
	 * Check if two lines given by two points on each line intersect. 
	 * Source: http://www.ambrsoft.com/MathCalc/Line/TwoLinesIntersection/TwoLinesIntersection.htm
	 * 
	 * @param x1 x-Coord of line 1 begin
	 * @param y1 y-Coord of line 1 begin
	 * @param x2 x-Coord of line 1 end
	 * @param y2 y-Coord of line 2 end
	 * @param x3 x-Coord of line 2 begin
	 * @param y3 y-Coord of line 2 begin
	 * @param x4 x-Coord of line 2 end
	 * @param y4 y-Coord of line 2 end
	 * @return True if the two lines intersect, false if not
	 */
	public static boolean intersectingLines(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
	{
		// x = ((x1*y1 - x1*y2) * (x4-x3) - (x4*y3 - x3*y4) * (x2-x1)) / ((x2-x1) *
		// (y4-y3) - (x4-x3) * (y2-y1))
		// y = ((x1*y1 - x1*y2) * (y4-y3) - (x4*y3 - x3*y4) * (y2-y1)) / ((x2-x1) *
		// (y4-y3) - (x4-x3) * (y2-y1));
		double x = ((x1 * y1 - x1 * y2) * (x4 - x3) - (x4 * y3 - x3 * y4) * (x2 - x1))
				/ ((x2 - x1) * (y4 - y3) - (x4 - x3) * (y2 - y1));
		double y = ((x1 * y1 - x1 * y2) * (y4 - y3) - (x4 * y3 - x3 * y4) * (y2 - y1))
				/ ((x2 - x1) * (y4 - y3) - (x4 - x3) * (y2 - y1));

		System.out.println("x: " + x + "; y: " + y);
		return false;
	}

	/**
	 * 
	 * Source: https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
	 * 
	 * @param p
	 * @param q
	 * @param r
	 * @return
	 */
	public static boolean onSegment(Point p, Point q, Point r)
	{
		if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
			return true;

		return false;
	}

	/**
	 * 
	 * Source: https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
	 * 
	 * @param p
	 * @param q
	 * @param r
	 * @return 0 if p, q, r are collinear; 1 if clockwise or 2 if counterclockwise
	 */
	public static int orientation(Point p, Point q, Point r)
	{
		// See https://www.geeksforgeeks.org/orientation-3-ordered-points/
		// for details of below formula.
		int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

		if (val == 0)
			return 0; // colinear

		return (val > 0) ? 1 : 2; // clock or counterclock wise
	}

	/**
	 * Source: https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
	 * 
	 * @param p1
	 * @param q1
	 * @param p2
	 * @param q2
	 * @return True if the lines p1-q1 and p2-q2 intersect
	 */
	public static boolean intersectingSegments(Point p1, Point q1, Point p2, Point q2)
	{
		//Special case, overlappint points shall not count
		//Da in dieser Software alle Linien auf einem Eckpunkt Enden, müssen wir diesen Fall raus filtern
		if(p1.equals(p2) || p1.equals(q2) || q1.equals(p2) || q1.equals(q2))
			return false;
		
		// Find the four orientations needed for general and
		// special cases
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);

		// General case
		if (o1 != o2 && o3 != o4)
			return true;

		// Special Cases
		// p1, q1 and p2 are colinear and p2 lies on segment p1q1
		if (o1 == 0 && onSegment(p1, p2, q1))
			return true;

		// p1, q1 and q2 are colinear and q2 lies on segment p1q1
		if (o2 == 0 && onSegment(p1, q2, q1))
			return true;

		// p2, q2 and p1 are colinear and p1 lies on segment p2q2
		if (o3 == 0 && onSegment(p2, p1, q2))
			return true;

		// p2, q2 and q1 are colinear and q1 lies on segment p2q2
		if (o4 == 0 && onSegment(p2, q1, q2))
			return true;

		return false; // Doesn't fall in any of the above cases
	}
	
	/**
	 * Source: https://www.arduino.cc/reference/en/language/functions/math/map/
	 * @param x The number to map.
	 * @param in_min The lower bound of the value’s current range.
	 * @param in_max The upper bound of the value’s current range
	 * @param out_min The lower bound of the value’s target range.
	 * @param out_max The upper bound of the value’s target range.
	 * @return
	 */
	public static int map(int x, int in_min, int in_max, int out_min, int out_max) 
	{
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
