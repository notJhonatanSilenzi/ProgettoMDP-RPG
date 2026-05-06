package it.unicam.cs.mpgc.rpg126164.abstractions;

public interface SlotDiSalvataggio {

    /**
     * Questo metodo permette, partendo dallo slot di salvataggio considerato, di caricare i
     * progressi salvati per riprendere a giocare
     */
    void caricaProgressi();

    /**
     * Questo metodo permette, partendo dallo slot di salvataggio considerato, di sovrascrivere
     * i progressi di gioco per terminare la sessione di gioco
     * ----------------------------------------------------------------------------------------
     * NOTA: ogni sovrascrittura cancella i progressi precedenti, rendendoli irrecuperabili. Se
     * si vogliono mantenere versioni precedenti, occorre utilizzare più slot di salvataggio
     */
    void scaricaProgressi();

    /**
     * Questo metodo permette di cancellare i progressi in questo slot di salvataggio, lasciandolo
     * vuoto
     */
    void elimina();
}
