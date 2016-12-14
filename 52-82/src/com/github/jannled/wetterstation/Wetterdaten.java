package com.github.jannled.wetterstation;

import java.util.Date;

public class Wetterdaten 
{
	Date date;
	float temperatur;
	
	public Wetterdaten(Date date, float temperatur)
	{
		this.date = date;
		this.temperatur = temperatur;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public float getTemperatur()
	{
		return temperatur;
	}
	
	@Override
	public String toString()
	{
		return date.toString() + ": " + temperatur + "°C";
	}
}
