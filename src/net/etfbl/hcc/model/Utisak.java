package net.etfbl.hcc.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Utisak implements Serializable,Comparable<Object>{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int idUtiska;
	private String tekst;
	private LocalDateTime datum;
	private Korisnik korisnik;

	public Utisak() {
		// TODO Auto-generated constructor stub
	}
	public Utisak(int idUtiska, String tekst, LocalDateTime datum,Korisnik k) {
		this.idUtiska = idUtiska;
		this.tekst = tekst;
		this.datum = datum;
		this.korisnik=k;
	}
	public int compareTo(Object o){
		Utisak u = (Utisak) o;
		long time =u.getDatum().toEpochSecond(ZoneOffset.UTC)-this.datum.toEpochSecond(ZoneOffset.UTC);
		return (int) time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUtiska;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utisak other = (Utisak) obj;
		if (idUtiska != other.idUtiska)
			return false;
		return true;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public int getIdUtiska() {
		return idUtiska;
	}
	public void setIdUtiska(int idUtiska) {
		this.idUtiska = idUtiska;
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public LocalDateTime getDatum() {
		return datum;
	}
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}

}
