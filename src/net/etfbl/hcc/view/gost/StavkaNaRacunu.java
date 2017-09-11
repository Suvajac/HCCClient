package net.etfbl.hcc.view.gost;

public class StavkaNaRacunu {
	
	private String naziv;
	private String datum;
	private double cijena;
	
	public StavkaNaRacunu(String naziv,String datum,double cijena){
        this.naziv=naziv;
        this.datum=datum;
        this.cijena=cijena;
}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public double getCijena() {
		return cijena;
	}

	public void setCijena(double cijena) {
		this.cijena = cijena;
	}

    
 

}
