package com.github.jannled.schule.wettlauf;

public class Laufer 
{
	private String name;
	double zeit;
	boolean qualifiziert;
	
	public Laufer(String name)
	{
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getZeit() {
		return zeit;
	}

	public void setZeit(double d) {
		this.zeit = d;
	}

	public boolean isQualifiziert() {
		return qualifiziert;
	}

	public void setQualifiziert(boolean qualifiziert) {
		this.qualifiziert = qualifiziert;
	}
}
