package com.github.jannled.schule.wetterstation;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Datas extends JPanel
{
	private static final long serialVersionUID = -6019018739945122734L;
	ArrayList<Wetterdaten> daten;

	public Datas()
	{
		
	}
	
	public ArrayList<Wetterdaten> getDaten()
	{
		return daten;
	}
}
