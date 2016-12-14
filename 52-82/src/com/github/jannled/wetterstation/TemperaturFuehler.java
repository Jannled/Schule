package com.github.jannled.wetterstation;

import java.util.Date;

public class TemperaturFühler 
{
	public Wetterdaten messen()
	{
		return new Wetterdaten(getTime(), getTemperatur());
	}
	
	public Date getTime()
	{
		return new Date(System.currentTimeMillis());
	}
	
	public float getTemperatur()
	{
		float time = (float) Math.random() * 100;
		int buffer = (int) time*100;
		time = buffer / 100;
		return time;
	}
}
