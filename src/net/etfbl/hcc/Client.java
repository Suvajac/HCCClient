package net.etfbl.hcc;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import net.etfbl.hcc.model.Gost;
import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Obavjestenje;
import net.etfbl.hcc.model.Oglas;
import net.etfbl.hcc.model.Popust;
import net.etfbl.hcc.model.Proizvod;
import net.etfbl.hcc.model.Utisak;
import net.etfbl.hcc.util.ConnectionProperty;
import net.etfbl.hcc.util.ProtokolPoruka;

public class Client {
	private static Client instance;	
	public static Client getInstance(){
		if(instance==null){
			instance = new Client();
		}
		return instance;
	}
	
	private Socket sock;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private InetAddress addr;
	private int port;
	
	private Client(){
		try{
			addr = InetAddress.getByName(ConnectionProperty.getInstance().getServerIpAddress());
			port = ConnectionProperty.getInstance().getServerTCPPort();
			sock = new Socket(addr,port);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public Korisnik login(Korisnik k){
		try{
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(k.getUsername());
			ProtokolPoruka ppout = new ProtokolPoruka("Korisnik.getKorisnik",lista);
			
			out.reset();
			out.writeObject(ppout);
			out.flush();
			
	        ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
	        Korisnik retKorisnik = (Korisnik) ppin.getListaObjekata().get(0);
			if(retKorisnik!=null && retKorisnik.getUsername().equals(k.getUsername()) && retKorisnik.getLozinkaHash().equals(k.getLozinkaHash())){
				return retKorisnik;
			}
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void logout(){
		try{
			out.reset();
			ProtokolPoruka ppout = new ProtokolPoruka("Korisnik.logout");
			out.writeObject(ppout);
			
			try{
				in.readObject();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			
			in.close();
			out.close();
			sock.close();
			instance = null;
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Utisak> getUtisci(){
		try{
			ProtokolPoruka ppout = new ProtokolPoruka("Utisak.getUtisci");
			out.reset();
			out.writeObject(ppout);
			out.flush();
			
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			ArrayList<Utisak> lista = (ArrayList<Utisak>) ppin.getListaObjekata().get(0);
			return lista;
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean dodajUtisak(Utisak u){
		try{
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(u);
			ProtokolPoruka ppout = new ProtokolPoruka("Utisak.dodaj",lista);
			out.reset();
			out.writeObject(ppout);
			out.flush();
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			if(ppin!=null){
				return true;
			}
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean obrisiUtisak(Utisak u){
		try {
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(u);
			ProtokolPoruka ppout = new ProtokolPoruka("Utisak.obrisi", lista);
			out.reset();
			out.writeObject(ppout);
			out.flush();
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			
			if("response".equals(ppin.getTip())) {
				return true;
			}
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Obavjestenje> getObavjestenja(){
		try{
			ProtokolPoruka ppout = new ProtokolPoruka("Obavjestenje.getObavjestenja");
			out.reset();
			out.writeObject(ppout);
			out.flush();
			
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			ArrayList<Obavjestenje> lista = (ArrayList<Obavjestenje>) ppin.getListaObjekata().get(0);
			return lista;
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean procitajObavjestenje(Obavjestenje o) {
		try{
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(o);
			ProtokolPoruka ppout = new ProtokolPoruka("Obavjestenje.procitajObavjestenje",lista);
			
			out.reset();
			out.writeObject(ppout);
			out.flush();
			
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			boolean response = (boolean) ppin.getListaObjekata().get(0);
			return response;
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Oglas> getOglasi(){
		try{
			ProtokolPoruka ppout = new ProtokolPoruka("Oglas.getOglasi");
			out.reset();
			out.writeObject(ppout);
			out.flush();
			
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			ArrayList<Oglas> lista = (ArrayList<Oglas>) ppin.getListaObjekata().get(0);
			return lista;
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Popust> getPopusti() {
		try {
			ProtokolPoruka ppout = new ProtokolPoruka("Popust.getPopusti");
			out.reset();
			out.writeObject(ppout);
			out.flush();
			
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			ArrayList<Popust> lista = (ArrayList<Popust>) ppin.getListaObjekata().get(0);
			return lista;
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean dodajPopust(Popust p) {
		try{
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(p);
			ProtokolPoruka ppout = new ProtokolPoruka("Popust.dodaj",lista);
			out.reset();
			out.writeObject(ppout);
			out.flush();
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			if(ppin!=null){
				return true;
			}
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean obrisiPopust(Popust p){
		try {
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(p);
			ProtokolPoruka ppout = new ProtokolPoruka("Popust.obrisi", lista);
			out.reset();
			out.writeObject(ppout);
			out.flush();
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			
			if("response".equals(ppin.getTip())) {
				return true;
			}
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean potvrdiPopust(int kodPopusta, Gost g){
		try{
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(kodPopusta);
			lista.add(g);
			ProtokolPoruka ppout = new ProtokolPoruka("Popust.potvrdiPopust",lista);
			
			out.reset();
			out.writeObject(ppout);
			out.flush();
			
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			boolean response = (boolean) ppin.getListaObjekata().get(0);
			return response;
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean dodajProizvod(Proizvod p) {
		try{
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(p);
			ProtokolPoruka ppout = new ProtokolPoruka("Proizvod.dodaj",lista);
			out.reset();
			out.writeObject(ppout);
			out.flush();
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			if(ppin!=null){
				return true;
			}
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean obrisiProizvod(Proizvod p){
		try {
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(p);
			ProtokolPoruka ppout = new ProtokolPoruka("Proizvod.obrisi", lista);
			out.reset();
			out.writeObject(ppout);
			out.flush();
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			
			if("response".equals(ppin.getTip())) {
				return true;
			}
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}	
	
	public ArrayList<Proizvod> getProizvodi(){
		try{
			ProtokolPoruka ppout = new ProtokolPoruka("Proizvod.getProizvodi");
			out.reset();
			out.writeObject(ppout);
			out.flush();
			
			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
			ArrayList<Proizvod> lista = (ArrayList<Proizvod>) ppin.getListaObjekata().get(0);
			return lista;
		}
		catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

}
