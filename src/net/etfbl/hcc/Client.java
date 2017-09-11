package net.etfbl.hcc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import net.etfbl.hcc.model.Korisnik;
import net.etfbl.hcc.model.Utisak;
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
			k = new Korisnik("bojansuvajac","bojan","suvajac","asdfg","hesh");
			out.reset();
			out.writeObject("Korisnik.getKorisnik");
			out.flush();
			
			out.reset();
			out.writeObject(k.getUsername());
			out.flush();
			
	        Korisnik retKorisnik = (Korisnik) in.readObject();
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
//		try{
////			ProtokolPoruka ppout = new ProtokolPoruka("Korisnik.logout");
////			out.reset();
////			out.writeObject(ppout);
////			in.close();
////			out.close();
////			sock.close();
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
	}
	
	public boolean dodajUtisak(Utisak u){
		return true;
//		try{
//			ArrayList<Object> lista = new ArrayList<>();
//			lista.add(u);
//			ProtokolPoruka ppout = new ProtokolPoruka("Utisak.dodaj",lista);
//			out.reset();
//			out.writeObject(ppout);
//			ProtokolPoruka ppin = (ProtokolPoruka) in.readObject();
//			if(ppin!=null){
//				return true;
//			}
//		}
//		catch(IOException | ClassNotFoundException e){
//			e.printStackTrace();
//		}
//		return false;
	}
}
