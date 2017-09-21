package net.etfbl.hcc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.util.Duration;
import net.etfbl.hcc.util.ConnectionProperty;

public class ClientMulticast extends Thread {
	private MulticastSocket clientSocket;
	
	public ClientMulticast() {
		try {
			setDaemon(true);
			clientSocket = new MulticastSocket(ConnectionProperty.getInstance().getMulticastPort());
			this.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run(){
		InetAddress groupAddress = null;
        byte[] buf = new byte[512];
        try{
            groupAddress=InetAddress.getByName(ConnectionProperty.getInstance().getMulticastIpAdress());
        	clientSocket.joinGroup(groupAddress);
        	while (true) {
                DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
                clientSocket.receive(msgPacket);
//                String msg = new String(msgPacket.getData(), msgPacket.getOffset(), msgPacket.getLength());
                Platform.runLater(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Notifications.create()
	                	.hideAfter(new Duration(7000)).title("Obavjestenje")
	                	.text("Imate novo neprocitano obavjestenje!")
	                	.showInformation();
					}
                });
            }
        }
        catch(SocketException ex) {
        	System.out.println("Logged out");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

	}
	
	public void close() {
		clientSocket.close();
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
