package eu.convos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class OutputPane extends Canvas
{
	private static final long serialVersionUID = 1L;
	private static int SPACING = 10;
	
	Szene szene;
	
	public OutputPane()
	{
		setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) 
	{	
		g.setColor(Color.WHITE);
		
		//Prüfe ob die Szene schon gesetzt wurde
		if(szene == null)
			return;
		
		for(Polygon p : szene.getPolygone())
		{
			//Verbinde 2 Koordinaten
			for(int i=0; i<p.anzahlKoordinaten(); i += 2)
			{
				g.drawLine(
					map(p.getKoordinate((i)   % p.anzahlKoordinaten()),   szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()),
					map(p.getKoordinate((i+1) % p.anzahlKoordinaten()), szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight()),
					map(p.getKoordinate((i+2) % p.anzahlKoordinaten()), szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()),
					map(p.getKoordinate((i+3) % p.anzahlKoordinaten()), szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight())
				);
			}
		}
	}
	
	public void setSzene(Szene s)
	{
		this.szene = s;
		repaint();
	}
	
	int map(int x, int in_min, int in_max, int out_min, int out_max) 
	{
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
