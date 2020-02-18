import java.io.*;
import java.net.*;

public class Klient {

    public static void main(String[] args){

        String host;
        int port;

        if(args.length==0){
            host= "localhost";
            port = 9999;
        }
        else{
            host = args[0];
            String portStr = args[1];
            try {
                port=Integer.parseInt(portStr);
            }
            catch(NumberFormatException nfe){
                System.out.println("Uuups, zły numer portu. Przełączam na domyslny port: 9999");
                port = 9999;
            }
        }
        try{
            System.out.println("Klient: Próba podłączenia do serwera jako host-"+host+" port: "+port+'.');
            Socket skt = new Socket(host,port);

            BufferedReader Input = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            PrintStream Output = new PrintStream(skt.getOutputStream());

            Output.println("Klient: Siema!");

            String buf=Input.readLine();
            if(buf !=null){
                System.out.println("Klient: Odpowiedź serwera [ "+buf+" ]");
            }
            else
                System.out.println("Klient: Brak odpowiedzi z serwera.");


        }
        catch (IOException ex){
            ex.printStackTrace();
            System.out.println("Uuuups, coś się skopało. nie podziałam!");
        }
    }
}