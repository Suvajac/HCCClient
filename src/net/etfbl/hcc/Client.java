package net.etfbl.hcc;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;


import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Oglas;
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
			in.close();
			out.close();
			sock.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
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
	
}
