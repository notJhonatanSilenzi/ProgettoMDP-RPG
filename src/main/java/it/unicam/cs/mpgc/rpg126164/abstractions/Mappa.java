package it.unicam.cs.mpgc.rpg126164.abstractions;

public interface Mappa {

    /**
     * Questo metodo permette di aprire la mappa del mondo di gioco in questa sessione di gioco
     * ----------------------------------------------------------------------------------------
     * NOTA: la visualizzazione della mappa blocca la possibilità di muoversi nel mondo di gioco
     */
    void apri();

    /**
     * Questo metodo permette di chiudere la mappa del mondo di gioco in questa sessione
     */
    void chiudi();

    /**
     * Questo metodo aggiorna la mappa in base alla posizione corrente e ai progressi fatti nel
     * gioco in questa sessione
     */
    void aggiornaMappa();
}
