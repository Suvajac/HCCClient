package net.etfbl.hcc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.util.Duration;
import net.etfbl.hcc.util.ConnectionProperty;

public class ClientMulticast extends Thread {

	public void run(){
		InetAddress groupAddress = null;
        byte[] buf = new byte[512];
        try (MulticastSocket clientSocket = new MulticastSocket(ConnectionProperty.getInstance().getMulticastPort())){
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }

	}
	public ClientMulticast() {
		setDaemon(true);
		this.start();
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
