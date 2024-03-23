package asocijacije;

import java.net.*;
import java.nio.charset.Charset;
import java.io.*;

public class Client {

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.defaultCharset()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.defaultCharset()));
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))){

            System.out.println(in.readLine());
            String tekst;
            int nova = 0;
            while((tekst = in.readLine()) != null) {

                if(nova == 0) {
                    for(int i = 0; i<4; i++) {
                        if(tekst != null)
                            System.out.println(tekst);
                        if(tekst.contains("Osvojili ste") || tekst.contains("okej")) {
                            nova = 0;
                            break;
                        }
                        tekst = in.readLine();
                        nova = 1;
                    }
                }
                if(nova == 0) {
                    break;
                }

                while(nova == 1) {
                    out.write(userIn.readLine());
                    out.newLine();
                    out.flush();

                    tekst = in.readLine();
                    System.out.println(tekst);
                    if(tekst.equals("Tacno!") || tekst.equals("Niste pogodili"))
                        nova = 0;
                }


            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Kraj igre");
    }
}
