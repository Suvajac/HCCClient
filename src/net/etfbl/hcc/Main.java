package net.etfbl.hcc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.etfbl.hcc.controller.LoginController;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/login.fxml"));
        AnchorPane anchorPane = loader.load();
        LoginController loginController = loader.getController();
        loginController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(anchorPane);
        primaryStage.setTitle("Hotel - Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception{
        launch(args);
//    	try {
//			Socket sock = new Socket(InetAddress.getByName(ConnectionProperty.getInstance().getServerIpAddress()), ConnectionProperty.getInstance().getServerTCPPort());
//			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
//			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
//			
//			out.writeObject(new ProtokolPoruka("Korisnik.getKorisnik"));
//			ProtokolPoruka p = (ProtokolPoruka) in.readObject();
//			System.out.println(((Gost)p.getListaObjekata().get(0)).getLozinkaHash());
//		} catch (UnknownHostException | ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	ArrayList<Object> lista = new ArrayList<>();
//    	lista.add(new Korisnik("asd",null,null,null,null));
//        ProtokolPoruka por = new ProtokolPoruka("ovo je neki tip",lista);
//        JSONObject json = new JSONObject(por);
//        Gson gson = new Gson();
//        ProtokolPoruka poruka = gson.fromJson(json.toString(), ProtokolPoruka.class);
//        
//        System.out.println(poruka.getTip());
//        System.out.println(poruka.getListaObjekata()); 
//        
        
    }
}
