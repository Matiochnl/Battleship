import java.io.*;
import java.net.*;

public class Server {


    public static void main(String[] args) {
        //Controller controller = new Controller();
        //controller.runner();
        // TODO Auto-generated method stub
        try{
            ServerSocket myServerSocket = new ServerSocket(9999);

            System.out.println("Serwer: Start na hoście-"
                    +InetAddress.getLocalHost().getCanonicalHostName()
                    +" port: "+myServerSocket.getLocalPort());
            Socket skt = myServerSocket.accept();

            BufferedReader Input = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            PrintStream Output = new PrintStream(skt.getOutputStream());

            String buf = Input.readLine();

            if (buf !=null){
                System.out.println("Serwer, odczyt: [ "+buf+" ]");
                Output.print("  ccc");
            }


        }
        catch (IOException ex){
            ex.printStackTrace();
            System.out.println("Uuuups, coś się skopało. nie podziałam!");
        }
    }


}