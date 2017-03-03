package com.github.jannled.b42;

public class Color 
{
	byte R, G, B, A;
	java.awt.Color color;
	
	public Color()
	{
		
	}
	
	public Color(int R, int G, int B)
	{
		set(R, G, B);
	}
	
	public Color(int R, int G, int B, int A)
	{
		set(R, G, B, A);
	}
	
	public java.awt.Color getColor()
	{
		if(color==null)
		{
			rebuild();
		}
		return color;
	}
	
	public int[] getRGB()
	{
		int color[] = new int[4];
		color[0] = Byte.toUnsignedInt(R);
		color[1] = Byte.toUnsignedInt(G);
		color[2] = Byte.toUnsignedInt(B);
		return color;
	}
	
	public int[] getRGBA()
	{
		int color[] = new int[4];
		color[0] = Byte.toUnsignedInt(R);
		color[1] = Byte.toUnsignedInt(G);
		color[2] = Byte.toUnsignedInt(B);
		color[3] = Byte.toUnsignedInt(A);
		return color;
	}
	
	public void set(int R, int G, int B)
	{
		this.R = (byte) R;
		this.G = (byte) G;
		this.B = (byte) B;
		rebuild();
	}
	
	public void set(int R, int G, int B, int A)
	{
		this.R = (byte) R;
		this.G = (byte) G;
		this.B = (byte) B;
		this.A = (byte) A;
		rebuild();
	}
	
	private final void rebuild()
	{
		color = new java.awt.Color(Byte.toUnsignedInt(R), Byte.toUnsignedInt(G), Byte.toUnsignedInt(B), Byte.toUnsignedInt(A));
	}
}
