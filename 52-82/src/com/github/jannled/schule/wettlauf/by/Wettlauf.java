package com.github.jannled.schule.wettlauf.by;

public class Wettlauf 
{
	private Laeufer[] laeuferfeld;
	private int anzahlBahnen;
	private int anzahlLaeufer;

	/*
	 * Konstruktor für Objekte der Klasse Wettlauf
	 * 
	 * @param pAnzahlBahnen Der Integer-Wert gibt an, wie viele Bahnen genutzt
	 * werden sollen.
	 */
	public Wettlauf(int pAnzahlBahnen) {
		laeuferfeld = new Laeufer[pAnzahlBahnen];
		anzahlBahnen = pAnzahlBahnen;
		anzahlLaeufer = 0;
	}

	/**
	 * Es gilt nicht die Array-Indizierung. Die Bahnen beginnen mit der Nummer 1
	 * 
	 * @param pBahn
	 *            Nummer der Laufbahn(int), bei der überprüft wird, ob sich ein
	 *            Läufer dort befindet.
	 * @return true oder false, Überprüfung, ob die Bahn belegt ist oder nicht.
	 */
	public boolean bahnBelegt(int pBahn) {
		return laeuferfeld[pBahn - 1] != null;
	}

	/**
	 * Die erste Bahn wird mit einer 1 angegeben, nicht mit 0. Die Läufer gehen
	 * an den Start.
	 * 
	 * @param pBahn
	 *            Auf welcher Bahn (int) der Läufer an den Start geht.
	 * @param pLaeufer
	 *            Welches Objekt vom Typ Laeufer an den Start geht.
	 */
	public void anDenStart(int pBahn, Laeufer pLaeufer) {
		if (laeuferfeld[pBahn - 1] == null && pBahn <= anzahlBahnen && pBahn > 0) {
			laeuferfeld[pBahn - 1] = pLaeufer;
			anzahlLaeufer++;
		}
	}

	/**
	 * Generierung einer Zufallszahl. Diese wird (optional) anschließend auf 2
	 * Stellen hinter dem Komma gerundet.
	 * 
	 * @return ergebnis, Rückgabe einer Dezimalzahl mit zwei Nachkomma stellen.
	 */
	public double zeitMessen() {
		double ergebnis;
		double zufallszahl = Math.random() * 10 + 10;
		ergebnis = zufallszahl;
		ergebnis = (int) (ergebnis * 100 + 0.5) / 100.0;
		return ergebnis;
	}

	/**
	 * Implememtierung des eigentlichen Rennens. Jedem Läufer wird dabei eine
	 * Zufallszahl zugewiesen. Ist nur ein Läufer angetreten, findet das Rennen
	 * nicht statt.
	 */
	public void rennenLaufen() {
		if (anzahlLaeufer > 1) {
			for (int i = 0; i < laeuferfeld.length; i++) {
				if (laeuferfeld[i] != null) {
					laeuferfeld[i].setZeit(zeitMessen());
				}
			}
		} else {
			System.out.println("Mindestanzahl von 2 Läufern nicht erreicht.");
		}
	}

	/**
	 * Auswertung entsprechend der Anforderungen
	 *
	 */
	public void auswertung() {
		double schnellsteZeit = laeuferfeld[0].getZeit();
		double langsamsteZeit = laeuferfeld[0].getZeit();
		double durchschnittszeit = 0;
		double summe = 0;
		int anzahl = 1;
		int posSchnellster = 0;
		int posZweitschnellster = 0;
		int posLangsamster = 0;
		for (int i = 1; i < laeuferfeld.length; i++) {
			if (laeuferfeld[i] != null) {
				if (laeuferfeld[i].getZeit() < schnellsteZeit) {
					schnellsteZeit = laeuferfeld[i].getZeit();
					posZweitschnellster = posSchnellster;
					posSchnellster = i;
				}
				if (laeuferfeld[i].getZeit() > langsamsteZeit) {
					langsamsteZeit = laeuferfeld[i].getZeit();
					posLangsamster = i;
				}
				summe = summe + laeuferfeld[i].getZeit();
				anzahl++;
			} // if-Abfrage Bahn belegt
		}

		System.out.println("Der Sieger ist: " + laeuferfeld[posSchnellster].getName() + " mit einer Zeit von "
				+ laeuferfeld[posSchnellster].getZeit() + ".");
		System.out.println("Den 2. Platz belegt: " + laeuferfeld[posZweitschnellster].getName() + " mit einer Zeit von "
				+ laeuferfeld[posZweitschnellster].getZeit() + ".");
		System.out.println("Der Langsamste ist: " + laeuferfeld[posLangsamster].getName() + " miteiner Zeit von "
				+ laeuferfeld[posLangsamster].getZeit() + ". ");
		laeuferfeld[posSchnellster].setQuali(true);
		laeuferfeld[posZweitschnellster].setQuali(true);
		System.out.println();
		System.out.println("Gesamtübersicht");
		for (int i = 0; i < laeuferfeld.length; i++) {
			if (laeuferfeld[i] != null) {
				System.out.println(" Bahn " + (i + 1) + ": " + "Name: " + laeuferfeld[i].getName() + "\t Zeit: "
						+ laeuferfeld[i].getZeit() + " Sek. \t Qualifikation: " + laeuferfeld[i].getQuali());

			}
		}
		System.out.println();
		System.out.println("Die Anzahl der Läufer beträgt:" + anzahl);
		System.out.println(
				"Die Durchschnittsgeschwindigkeit beträgt:" + (double) Math.round(100 * (summe / anzahl) / 100));
	}
}