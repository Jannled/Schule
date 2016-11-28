package com.github.jannled.schule.wettlauf;

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
		for(int i=0; i<namen.length; i++)
		{
			laufer[i] = new Laufer(namen[i]);
			laufer[i].setQualifiziert(true);
		}
	}
	
	public void zeitMessen()
	{
		
	}
	
	public void rennenLaufen()
	{
		
	}
}
