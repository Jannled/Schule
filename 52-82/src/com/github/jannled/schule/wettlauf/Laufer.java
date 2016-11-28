package com.github.jannled.schule.wettlauf;

public class Laufer 
{
	private String name;
	float zeit;
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

	public float getZeit() {
		return zeit;
	}

	public void setZeit(float zeit) {
		this.zeit = zeit;
	}

	public boolean isQualifiziert() {
		return qualifiziert;
	}

	public void setQualifiziert(boolean qualifiziert) {
		this.qualifiziert = qualifiziert;
	}
}
