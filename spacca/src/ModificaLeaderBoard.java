import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ModificaLeaderBoard {

    public void aggiungiGiocatoreLeaderBoard(String giocatore) throws IOException {
        File file = new File("LeaderBoardFile.csv");
        FileWriter scrivo = new FileWriter(file, true);
        ArrayList<Giocatore> giocatoriFile = new ArrayList<Giocatore>();
        boolean presente = false;

        if(giocatore.length()<3||!(giocatore.substring(0,3).equalsIgnoreCase("bot"))) {
            Scanner scan = new Scanner(file);
            if (scan.hasNextLine() == false) {
                scrivo.append(giocatore + "," + 0 + "\n");
            } else {
                while (scan.hasNextLine()) {
                    String riga = scan.nextLine();
                    String[] str = riga.split(",");
                    if(str[0]!=null)
                    giocatoriFile.add(new Giocatore(str[0], Integer.parseInt(str[1])));
                }
                for (int i = 0; i < giocatoriFile.size(); i++) {
                    if (giocatoriFile.get(i).getUsername().equalsIgnoreCase(giocatore)) {
                        presente = true;
                        break;
                    }
                }
                if (presente == false)
                    scrivo.append(giocatore + "," + 0 + "\n");

            }
            scrivo.close();
        }

    }



    public static void aggiornaLeaderBoard(Partita partita)throws IOException {
        File file = new File("LeaderBoardFile.csv");
        Scanner scan = new Scanner(file);
        ArrayList<Giocatore> giocatoriFile=new ArrayList<Giocatore>();

            while (scan.hasNextLine()) {
                String riga=scan.nextLine();
                String[] str=riga.split(",");
                giocatoriFile.add(new Giocatore(str[0],Integer.parseInt(str[1])));
              }
            for(int i=0; i<partita.getGiocatori().size(); i++){
                for(int j=0; j < giocatoriFile.size(); j++){
                    if(partita.getGiocatori().get(i).getUsername().equalsIgnoreCase(giocatoriFile.get(j).getUsername()))
                        giocatoriFile.get(j).setTotalScore(giocatoriFile.get(j).getTotalScore()+partita.getGiocatori().get(i).getTotalScore());
                }
            }
            Collections.sort(giocatoriFile);

            PrintWriter scrivo = new PrintWriter(file);

            for(int k=0; k<giocatoriFile.size(); k++)
                scrivo.println(giocatoriFile.get(k).getUsername()+","+giocatoriFile.get(k).getTotalScore());
            scrivo.close();
    }


}

