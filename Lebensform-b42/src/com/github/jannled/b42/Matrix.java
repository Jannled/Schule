package com.github.jannled.b42;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Matrix extends JPanel
{
	private static final long serialVersionUID = 7069089311797258569L;
	
	private Color[][] matrix;
	
	public Matrix(int width, int height)
	{
		matrix = new Color[width][height];
	}
	
	@Override
	public Dimension getPreferredSize() 
	{
		return new Dimension(matrix.length*10, matrix[0].length*10);
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		int cellWidth = getWidth() / matrix.length;
		int cellHeight = getHeight() / matrix[0].length;
		
		for(int x=0; x<matrix.length; x++)
		{
			for(int y=0; y<matrix[0].length; y++)
			{
				g.setColor(matrix[x][y]);
				g.fillRect(cellWidth*x, cellHeight*y, cellWidth, cellHeight);
			}
		}
	}
}
