package it.unicam.cs.mpgc.rpg126164.abstractions;

public interface MondoDiGioco {

    /**
     * Questo metodo permette di entrare nel mondo di gioco e cominciare/continuare la propria
     * esperienza di gioco
     */
    void entraNelGioco();
    /**
     * Questo metodo permette di salvare i progressi di gioco nello slot scelto alla creazione
     * e all'avvio del gioco
     */
    void salvaProgressi();

    /**
     * Questo metodo permette di uscire dal gioco e tornare al menu principale
     */
    void esciDalGioco();
}
