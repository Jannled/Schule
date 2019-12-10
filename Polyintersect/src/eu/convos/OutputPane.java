package eu.convos;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

public class OutputPane extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	/** Empty space around the polygons */
	private static int SPACING = 10;
	
	/** Size of the virtual point */
	private static final int VPUNKT_SIZE = 10;
	
	private Color BACKGROUND   = Color.BLACK;
	private Color COLOR_LINE   = Color.WHITE;
	private Color COLOR_VIEW   = Color.DARK_GRAY;
	private Color COLOR_VPUNKT = Color.ORANGE;
	
	Szene szene;
	
	public OutputPane()
	{
		setBackground(BACKGROUND);
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int x = map(e.getX(), 0, getWidth(), szene.xmin - SPACING, szene.xmax + SPACING);
				int y = map(e.getY(), 0, getHeight(), szene.ymin - SPACING, szene.ymax + SPACING);
				
				System.out.println("Viewpoint: " + x + "x" + y);
				
				szene.viewPoint.x = x;
				szene.viewPoint.y = y;
				
				repaint();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{	
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//Prüfe ob die Szene schon gesetzt wurde
		if(szene == null)
			return;
		
		//Draw Polygon Lines
		g.setColor(COLOR_LINE);
		for(Polygon p : szene.getPolygone())
		{
			//Verbinde 2 Koordinaten
			for(int i=0; i<p.anzahlKoordinaten(); i++)
			{
				//Modulo damit der Knoten am Ende des Arrays mit dem Anfang des Arrays verbindet
				drawLine(
					p.getKoordinate((i)   % p.anzahlKoordinaten()).x,
					p.getKoordinate((i)   % p.anzahlKoordinaten()).y,
					p.getKoordinate((i+1) % p.anzahlKoordinaten()).x,
					p.getKoordinate((i+1) % p.anzahlKoordinaten()).y,
					g
				);
			}
		}
		
		//Draw Lines from View point to each polygon
		g.setColor(COLOR_VIEW);
		for(Polygon p : szene.getPolygone())
		{
			for(int i=0; i<p.anzahlKoordinaten(); i++)
			{
				//Calculate intersection for each line to draw
				if(!intersectingPolygons(szene.viewPoint, p.getKoordinate(i), szene.getPolygone()))
					drawLine(szene.viewPoint.x , szene.viewPoint.y, p.getKoordinate(i).x, p.getKoordinate(i).y, g);
			}
		}
		
		//Zeichen Standpunkt
		g.setColor(COLOR_VPUNKT);
		g.drawOval(
			map(szene.viewPoint.x, szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()) - VPUNKT_SIZE/2,
			map(szene.viewPoint.y, szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight()) - VPUNKT_SIZE/2,
			VPUNKT_SIZE, 
			VPUNKT_SIZE
		);
	}
	
	/**
	 * Draw a line on the canvas. Will convert from polygon coordinates to screenspace coordinates.
	 * @param xstart X coordinate of the line beginning.
	 * @param ystart Y coordinate of the line beginning.
	 * @param xend X coordinate of the line ending.
	 * @param yend Y coordinate of the line beginning.
	 */
	public void drawLine(int xstart, int ystart, int xend, int yend, Graphics g)
	{
		g.drawLine(
			map(xstart, szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()),
			map(ystart, szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight()),
			map(xend, szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()),
			map(yend, szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight())
		);
	}
	
	/**
	 * Check if the line intersects one of the lines in the polygon
	 * @param xstart
	 * @param ystart
	 * @param xend
	 * @param yend
	 * @param p
	 * @return
	 */
	public boolean intersectingPolygons(Point begin, Point end, Polygon[] polys)
	{
		//Für jeden Eckpunkt tue
		for(Polygon p : polys)
		{
			if(p.intersects(begin, end))
				return true;
		}
			
		return false;
	}
	
	public void setSzene(Szene s)
	{
		this.szene = s;
		repaint();
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
	int map(int x, int in_min, int in_max, int out_min, int out_max) 
	{
		return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
