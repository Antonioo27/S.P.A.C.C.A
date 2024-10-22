## Requisiti

Versione 21+ di Java per eseguire il `.jar`
[Download Java21](https://www.oracle.com/java/technologies/downloads/?er=221886)

Installare le librerie di javaFX versione 23.0.1
[Download javaFX](https://gluonhq.com/products/javafx/)

Eseguire il `.jar` da terminale icludendo le librerie
```
java --module-path "path-to-javafx-sdk/lib" --add-modules=javafx.controls,javafx.xml -jar spacca.jar
```

`src`: la cartella in cui mantenere i sorgenti
`lib`: la cartella per mantenere le dipendenze
Nel frattempo, i file di output compilati verranno generati nella cartella `bin` di default.

Se desideri personalizzare la struttura delle cartelle, apri `.vscode/settings.json` e aggiorna lì le relative impostazioni.


## Regole gioco

Ogni partita sarà composta da 3 giocatori.

Le carte totali del gioco sono 21:

- **Carte Influencer** : Carte che hanno un determinato numero di follower

- **Carte imprevisto** : 

	- *Anonymous* : Chi schiera questa carta, sottrarrà alla fine della mano la metà dei punti che spettano al vincitore della mano, di conseguenza il vincitore prenderà la restante metà.
	
	- *Prodotto Difettoso* : Chi schiera questa carta alla fine della mano farà perdere la metà del punteggio al giocatore alla sua sinistra.
	
	- *Profilo Virale* : Chi schera questa carta riceverà alla fine della mano 80 punti bonus.


Se i giocatori che partecipano alla partita sono meno di tre, verrà riempita con dei bot.

### Come si gioca ?

Durante ogni mano un giocatore ha un turno a disposizione per schierare una carta.

I turni sono sequenziali e vanno in senso orario.

Alla fine di ogni mano, la carta con il **numero di followers più alto** aggiungerà il suo overall al punteggio totale del giocatore che l'ha schierata.

Alla fine di ogni mano automaticamente i giocatori pescheranno una carta dal mazzo.

Quando il mazzo esaurirà le proprie carte, i giocatori continueranno a schierare le carte che hanno in mano, fino ad esaurimento di quest'ultime.

La partita finisce nel momento in cui tutti i giocatori hanno schierato tutte le carte che avevano in mano ed il mazzo è vuoto. 

**Vincerà il giocatore con il punteggio maggiore.**
