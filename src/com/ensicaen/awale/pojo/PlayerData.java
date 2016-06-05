package com.ensicaen.awale.pojo;

/**
 *
 * @author Jérémie Leclerc : jeremie.leclerc@fime.com
 */
public class PlayerData {
    private String playerName;
    private int nbCatchedSeeds;
    private boolean playerTurn;

    public PlayerData(String playerName) {
        this.playerName = playerName;
        this.nbCatchedSeeds = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getNbCatchedSeeds() {
        return nbCatchedSeeds;
    }

    public void setNbCatchedSeeds(int nbCatchedSeeds) {
        this.nbCatchedSeeds = nbCatchedSeeds;
    }
    
    @Override
    public String toString() {
        String s = playerName + ";" + nbCatchedSeeds;
        return s;
    }
}
