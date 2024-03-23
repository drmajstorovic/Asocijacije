package asocijacije;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class ClientThread implements Runnable{
    Socket client;

    ClientThread(Socket client){
        this.client = client;
    }

    @Override
    public void run() {

        try(BufferedReader in = new BufferedReader(new InputStreamReader(this.client.getInputStream(), Charset.defaultCharset()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream(), Charset.defaultCharset()));
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream("src/asocijacije.txt"), Charset.defaultCharset()))){

            out.write("Dobro dosli u igru asocijacije");
            out.newLine();
            out.flush();
            String[] asocijacija;
            String red;
            int brpokusaja = 0, brbodova = 0;
            while((red = file.readLine()) != null) {
                if(brpokusaja==-1)
                    break;
                brpokusaja = 3;
                asocijacija = red.strip().split(" ");

                for(int i = 0; i<4; i++) {
                    out.write(asocijacija[i]);
                    out.newLine();
                    out.flush();
                }
                out.write("okej");
                out.newLine();
                out.flush();

                String ulaz;
                while(brpokusaja>0) {
                    ulaz = in.readLine().strip();
                    System.out.println(ulaz);
                    System.out.println(asocijacija[4]);
                    if(ulaz.equals(asocijacija[4].strip())) {
                        brbodova += brpokusaja;
                        out.write("Tacno!");
                        out.newLine();
                        out.flush();
                        break;
                    }
                    else if(ulaz.strip().equals("prekini igru")) {
                        brpokusaja = -1;
                        break;
                    }
                    else if(brpokusaja>1){
                        brpokusaja--;
                        out.write("Pokusajte ponovo");
                        out.newLine();
                        out.flush();
                    }else if(brpokusaja == 1){
                        brpokusaja--;
                        out.write("Niste pogodili");
                        out.newLine();
                        out.flush();
                    }
                }
            }

            out.write("Osvojili ste " + brbodova + " / 15 bodova.");
            out.newLine();
            out.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
