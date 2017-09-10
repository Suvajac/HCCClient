package net.etfbl.hcc.model;

public class Oprema extends Proizvod{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String velicina;
	
	public Oprema(){
		
	}
		
	public Oprema(int idProizvoda, String naziv, double cijena,String velicina) {
		super(idProizvoda,"oprema",naziv,cijena);
		this.velicina = velicina;
	}

	public String getVelicina() {
		return velicina;
	}

	public void setVelicina(String velicina) {
		this.velicina = velicina;
	}
	
	
}
