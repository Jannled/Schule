package com.github.jannled.schule.wettlauf;

public class SortMinMax 
{	
	/**
	 * 
	 * @param list The list to get the max and min value
	 * @return An double array with the length of 2. It contains the min and the max value
	 */
	public static double[] minMax(double[] list)
	{
		double min = 0;
		double max = 0;
		for(double d : list)
		{
			if(d<min)
			{
				min = d;
			}
			if(d>max)
			{
				max = d;
			}
		}
		return new double[] {min, max};
	}
}
