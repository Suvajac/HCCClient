package net.etfbl.hcc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import org.controlsfx.control.Notifications;

import net.etfbl.hcc.model.Obavjestenje;
import net.etfbl.hcc.util.ConnectionProperty;
import net.etfbl.hcc.util.TemporalStringConverters;

public class ClientMulticast extends Thread {

	public static ArrayList<Obavjestenje> listaObavjestenja=new ArrayList<>();

	public void run(){
		InetAddress groupAddress = null;
        byte[] buf = new byte[512];
        try (MulticastSocket clientSocket = new MulticastSocket(ConnectionProperty.getInstance().getMulticastPort())){
            groupAddress=InetAddress.getByName(ConnectionProperty.getInstance().getMulticastIpAdress());
        	clientSocket.joinGroup(groupAddress);
            while (true) {
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);
                String msg = new String(msgPacket.getData(), msgPacket.getOffset(), msgPacket.getLength());
                Notifications.create()
                	.title("Obavjestenje")
                	.text("Imate novo neprocitano obavjestenje!")
                	.showInformation();
                System.out.println("stiglo");
                listaObavjestenja.add(new Obavjestenje(msg.split("#")[0],
                		TemporalStringConverters.parseToLocalDateTime(msg.split("#")[1]),false));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

	}
	public ClientMulticast() {
		// TODO Auto-generated constructor stub
	}

	public ClientMulticast(Runnable target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	public ClientMulticast(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public ClientMulticast(ThreadGroup group, Runnable target) {
		super(group, target);
		// TODO Auto-generated constructor stub
	}

	public ClientMulticast(ThreadGroup group, String name) {
		super(group, name);
		// TODO Auto-generated constructor stub
	}

	public ClientMulticast(Runnable target, String name) {
		super(target, name);
		// TODO Auto-generated constructor stub
	}

	public ClientMulticast(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		// TODO Auto-generated constructor stub
	}

	public ClientMulticast(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		// TODO Auto-generated constructor stub
	}

}
