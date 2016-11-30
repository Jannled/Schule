package com.github.jannled.schule.wettlauf.by;

public class Laeufer {
	private String name;
	private double zeit;
	private boolean quali;

	public Laeufer(String pName) {
		name = pName;
		zeit = 0;
		quali = false;
	}

	public double getZeit() {
		return zeit;
	}

	public void setZeit(double pZeit) {
		zeit = pZeit;
	}

	public String getName() {
		return name;
	}

	public void setQuali(boolean pQuali) {
		quali = pQuali;
	}

	public boolean getQuali() {
		return quali;
	}
}