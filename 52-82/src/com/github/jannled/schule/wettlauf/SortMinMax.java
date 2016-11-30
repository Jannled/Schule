package com.github.jannled.schule.wettlauf;

public class SortMinMax 
{	
	/**
	 * 
	 * @param list The list to get the max and min value
	 * @return An double array with the length of 2. It contains the min and the max value
	 */
	public static Laufer[] minMax(Laufer[] list)
	{
		Laufer min = list[0];
		Laufer max = list[0];
		for(Laufer l : list)
		{
			if(l.getZeit()<min.getZeit())
			{
				min = l;
			}
			if(l.getZeit()>max.getZeit())
			{
				max = l;
			}
		}
		return new Laufer[] {min, max};
	}
}
