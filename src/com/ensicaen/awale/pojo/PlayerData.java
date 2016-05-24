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
        this.playerTurn = false;
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

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
    
}
