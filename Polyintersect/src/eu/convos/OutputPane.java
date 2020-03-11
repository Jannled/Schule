package eu.convos;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
	private Color COLOR_TARGET = Color.MAGENTA;
	
	Szene szene;
	
	public OutputPane()
	{
		setBackground(BACKGROUND);
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					int x = Mathe.map(e.getX(), 0, getWidth(), szene.xmin - SPACING, szene.xmax + SPACING);
					int y = Mathe.map(e.getY(), 0, getHeight(), szene.ymin - SPACING, szene.ymax + SPACING);
					
					System.out.println("Viewpoint: " + x + "x" + y);
					
					szene.viewPoint.x = x;
					szene.viewPoint.y = y;
				}
				else if(e.getButton() == MouseEvent.BUTTON3)
				{
					int x = Mathe.map(e.getX(), 0, getWidth(), szene.xmin - SPACING, szene.xmax + SPACING);
					int y = Mathe.map(e.getY(), 0, getHeight(), szene.ymin - SPACING, szene.ymax + SPACING);
					
					System.out.println("Target point: " + x + "x" + y);
					
					szene.targetPoint.x = x;
					szene.targetPoint.y = y;
				}
				
				double minWeg = szene.minimalerWeg();
				
				System.out.printf("Kürzester Weg von A%s nach B%s: %.2f!%n", 
						szene.viewPoint, szene.targetPoint, minWeg);
				
				Main.lblDistance.setText(String.format("Kürzester Weg: %.2f", minWeg));
				
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
		
		//Antialiasing ON
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		
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
			Mathe.map(szene.viewPoint.x, szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()) - VPUNKT_SIZE/2,
			Mathe.map(szene.viewPoint.y, szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight()) - VPUNKT_SIZE/2,
			VPUNKT_SIZE, 
			VPUNKT_SIZE
		);
		
		//Zeichne Zielpunkt
				g.setColor(COLOR_TARGET);
				g.drawOval(
					Mathe.map(szene.targetPoint.x, szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()) - VPUNKT_SIZE/2,
					Mathe.map(szene.targetPoint.y, szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight()) - VPUNKT_SIZE/2,
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
	 * @param g
	 */
	public void drawLine(int xstart, int ystart, int xend, int yend, Graphics g)
	{
		g.drawLine(
			Mathe.map(xstart, szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()),
			Mathe.map(ystart, szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight()),
			Mathe.map(xend, szene.xmin - SPACING, szene.xmax + SPACING, 0, getWidth()),
			Mathe.map(yend, szene.ymin - SPACING, szene.ymax + SPACING, 0, getHeight())
		);
	}
	
	/**
	 * Check if the line intersects one of the lines in the polygon
	 * @param begin
	 * @param end
	 * @param polys
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
}
