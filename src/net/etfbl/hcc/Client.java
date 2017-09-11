package net.etfbl.hcc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.util.ConnectionProperty;
import net.etfbl.hcc.util.ProtokolPoruka;

public class Client {
	private static Client instance;	
	public static Client getInstance(){
		if(instance==null){
			return new Client();
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
}
