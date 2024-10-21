import java.util.ArrayList;

public class  Giocatore implements Comparable {

        private String username;

        private int punteggio;

        private ArrayList<Carta> carte=new ArrayList<Carta>(); //carte che giocatore ha in mano

       public Giocatore(String nome,int punteggio,ArrayList<Carta> mazzo){
           this.username = nome;
           this.punteggio = punteggio;

           this.carte = mazzo;
       }

        public Giocatore(String nome){
            this.username = nome;
            this.punteggio = 0;
        }

        public Giocatore(String nome, int punti){
            this.username = nome;
            this.punteggio = punti;
        }

        public int getTotalScore() {
            return punteggio;
        }

        public String getUsername() {
            return username;
        }

        public void setTotalScore(int totalScore) {
            this.punteggio = totalScore;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void pesca(Mazzo mazzo) {
            for (int i = 0; i < this.carte.size(); i++) {
                if(this.carte.get(i)==null) {
                    this.carte.set(i, mazzo.getMazzo().getFirst());
                    mazzo.getMazzo().removeFirst();
                }
            }
        }


     public String toString() {
        String carteinmano="";
        for(Carta c : this.carte) {
            if (c != null)
                carteinmano = carteinmano + c.getNome() + ",";
        }
        return username + "," + punteggio + "," + carteinmano + "\n";
     }


     public int giocaCartaMigliore(){

         int n= this.getCarte().indexOf(Partita.TrovaMaggiore(this.getCarte()));
         if(n==-1) {
             for (int i=0; i<this.getCarte().size(); i++) {
                 if (this.getCarte().get(i) != null) {
                     n = i;
                     break;
                 }
             }
         }
     return n;
      }


        public ArrayList<Carta> getCarte(){
            return carte;
        }

    @Override
    public int compareTo(Object o) {
           if(o!=null&& o instanceof Giocatore){
               Giocatore g=(Giocatore) o;
               if(g.getTotalScore()>this.getTotalScore())
                   return 1;
               else
                   return -1;
           }
        return 0;
    }
}

