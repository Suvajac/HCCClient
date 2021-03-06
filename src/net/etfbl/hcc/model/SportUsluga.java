package net.etfbl.hcc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class SportUsluga extends Usluga implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<SportskaOprema> listaOpreme;
	private SportTermin sportTermin;

	public SportUsluga() {
		// TODO Auto-generated constructor stub
		listaOpreme=new ArrayList<SportskaOprema>();
		sportTermin=null;
	}

	public SportUsluga(int idUsluge, String naziv,double c) {
		super(idUsluge, naziv,c);
		listaOpreme=new ArrayList<SportskaOprema>();
		sportTermin=null;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		String newLine = System.getProperty("line.separator");
		String temp = "Sport usluga ["+sportTermin + "]"+newLine;
		
		ArrayList<SportskaOprema> tempLista = new ArrayList<>();
		
		for(SportskaOprema o : listaOpreme) {
			if(!tempLista.contains(o)) {
				temp+=o+", kolicina="+Collections.frequency(listaOpreme, o)+"]"+newLine;
				tempLista.add(o);
			}
		}
		tempLista = null;
		return temp;
	}

	public ArrayList<SportskaOprema> getListaOpreme() {
		return listaOpreme;
	}

	public void setListaOpreme(ArrayList<SportskaOprema> listaOpreme) {
		this.listaOpreme = listaOpreme;
	}

	public SportTermin getSportTermin() {
		return sportTermin;
	}

	public void setSportTermin(SportTermin sportTermin) {
		this.sportTermin = sportTermin;
	}

}
