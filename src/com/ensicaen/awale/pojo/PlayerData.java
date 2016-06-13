package com.ensicaen.awale.pojo;

/**
 * This class is used for storage purpose. It is intended to contain player
 * informations.
 *
 * @author Pierrick Hue and Jérémie Leclerc
 */
public class PlayerData {

    private String playerName;
    private int nbCatchedSeeds;

    public PlayerData(String playerName) {
        this.playerName = playerName;
        this.nbCatchedSeeds = 0;
    }

    /**
     * Allow to get the player name attribute.
     *
     * @return A string representing the player name attribute.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Allow to set the player name attribute.
     *
     * @param playerName A string representing the player name attribute.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Allow to get the catched seeds attribute.
     *
     * @return An integer representing the catched seeds attribute.
     */
    public int getNbCatchedSeeds() {
        return nbCatchedSeeds;
    }

    /**
     * Allow to get the catched seeds attribute.
     *
     * @param nbCatchedSeeds An integer representing the catched seeds
     * attribute.
     */
    public void setNbCatchedSeeds(int nbCatchedSeeds) {
        this.nbCatchedSeeds = nbCatchedSeeds;
    }

    @Override
    /**
     * @return A formatted string containing the player name and the catched
     * seeds number, separated by ';'.
     */
    public String toString() {
        String s = playerName + ";" + nbCatchedSeeds;
        return s;
    }
}
