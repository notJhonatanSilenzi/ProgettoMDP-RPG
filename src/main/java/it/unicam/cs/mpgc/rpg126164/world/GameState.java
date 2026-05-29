package it.unicam.cs.mpgc.rpg126164.world;

import it.unicam.cs.mpgc.rpg126164.characters.PlayableCharacter;

import java.io.Serializable;

public record GameState(
        PlayableCharacter player,
        String currentLevelId,
        int progressPercentage
) implements Serializable {
}
