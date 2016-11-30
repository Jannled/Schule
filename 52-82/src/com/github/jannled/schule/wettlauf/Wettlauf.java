package com.github.jannled.schule.wettlauf;

import java.util.Arrays;

public class Wettlauf 
{
	Laufer[] laufer;
	
	public Wettlauf(int zahlDerLaufer)
	{
		System.out.println("Beginne neuen Wettlauf!");
		laufer = new Laufer[zahlDerLaufer];
		WindowManager.w.setLaufer(laufer);
	}
	
	public void anDenStart(String[] namen)
	{
		System.out.println("Am Start stehen: " + Arrays.toString(namen));
		for(int i=0; i<namen.length; i++)
		{
			laufer[i] = new Laufer(namen[i]);
			laufer[i].setQualifiziert(true);
		}
	}
	
	public Laufer[] zeitMessen()
	{
		for(Laufer l : laufer)
		{
			if(l.getZeit()<=0)
			{
				l.setZeit(0);
			}
		}
		return laufer;
	}
	
	public void rennenLaufen()
	{
		for(Laufer l : laufer)
		{
			l.setZeit(7 + (Math.random() * ((20 - 3) + 1)));
		}
	}
	
	public Laufer[] getLaufer()
	{
		return laufer;
	}
}
