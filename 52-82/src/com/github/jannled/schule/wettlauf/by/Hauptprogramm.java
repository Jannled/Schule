package com.github.jannled.schule.wettlauf.by;

public class Hauptprogramm 
{
	public static void main(String[] args) {
		System.out.println("Start");

		Laeufer l1 = new Laeufer("Noah");
		Laeufer l2 = new Laeufer("Jana");
		Laeufer l3 = new Laeufer("Moritz");
		Laeufer l4 = new Laeufer("Tom");
		Laeufer l5 = new Laeufer("Jan");
		Laeufer l6 = new Laeufer("Jannik");
		Laeufer l7 = new Laeufer("Titus");
		Laeufer l8 = new Laeufer("Halil");

		Wettlauf wettlauf = new Wettlauf(8);

		wettlauf.anDenStart(1, l1);
		wettlauf.anDenStart(2, l2);
		wettlauf.anDenStart(3, l3);
		wettlauf.anDenStart(4, l4);
		wettlauf.anDenStart(5, l5);
		wettlauf.anDenStart(6, l6);
		wettlauf.anDenStart(7, l7);
		wettlauf.anDenStart(8, l8);

		wettlauf.rennenLaufen();

		wettlauf.auswertung();
	}
}