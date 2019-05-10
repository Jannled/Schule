package eu.convos.bank;

public class Transaktion 
{
	private Konto start, ziel;
	private float betrag;
	
	/**
	 * Eine Transaktion
	 * @param start
	 * @param ziel Wenn null, dann wurde eingezahlt/abgehoben
	 * @param betrag Die Veränderung des Kontos, das in start angegeben ist.
	 */
	public Transaktion(Konto start, Konto ziel, float betrag)
	{
		this.start = start;
		this.ziel = ziel;
		this.betrag = betrag;
	}

	public Konto getStart() {
		return start;
	}

	public Konto getZiel() {
		return ziel;
	}

	public float getBetrag() {
		return betrag;
	}
	
	@Override
	public String toString() 
	{
		if(ziel != null)
		{
			if(betrag > 0)
				return String.format("Empfange Überweisung von %s Konto[%d] mit dem Betrag %.2f€", start.getBesitzer().getName(), start.getKontoNummer(), betrag);
			else if(betrag < 0)
				return String.format("Überweise %.2f€ an %s Konto[%d]", -betrag, ziel.getBesitzer().getName(), ziel.getKontoNummer());	
		}
		else if(betrag < 0)
			return String.format("%.2f€ wurden vom Konto abgehoben.", -betrag);
		else if(betrag > 0)
			return String.format("%.2f€ wurden auf das Konto eingezahlt.", betrag);
		
		return String.format("Komische Transaktion: %.2f€", betrag);
	}
}
