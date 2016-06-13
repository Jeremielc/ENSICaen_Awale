package com.ensicaen.awale.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * Manage the rules layout pop-up.
 *
 * @author Pierrick Hue and Jérémie Leclerc
 */
public class RulesLayoutController implements Initializable {

    public RulesLayoutController() {

    }

    @FXML
    private TextArea txtRules;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtRules.wrapTextProperty().set(true);
        txtRules.setEditable(false);
    }

    /**
     * Displays the rules in the window.
     */
    public void handleRules() {
        txtRules.setText("Rule 1 : Only two palyers can compete.\n"
                + "\nRule 2 : Forty-eight seeds are distributed in the twelve holes with four seeds per hole.\n"
                + "\nRule 3 : Each player plays in turn, the one that goes first is drawn at random. The player will take all the seeds in one of the holes of its territory and distribute them, by a hole in the opposite direction clockwise.\n"
                + "\nRule 4 : If the last seed falls into a hole of the other side and there are now two or three seeds in the hole, the player gets two or three seeds and puts them aside. Then he looks at the previous box: if it is in the opposite camp and contains two or three seeds, it retrieves these seeds, and so on until he came to his side or until there has a different number of seeds of two or three.\n"
                + "\nRule 5 : It is forbidden to jump box when shells except when twelve or more seeds, that is to say, we come full circle: we do not complete box in which it has just taken the seeds.\n"
                + "\nRule 6 : We must \"feed\" the opponent, that is to say, when it has no more seeds, be sure to make a move that allows him to play again then. If this is not possible, the game ends and the player who would play captures the remaining seeds.\n"
                + "\nRule 7 : If a coup were to take all opposing seeds, then the shot can be played, but no capture is made: we must not \"starve\" the opponent.\n"
                + "\nRule 8 : The game ends when one player has captured at least 25 seeds, and is designated winner, or there are no more than 6 seeds in.\n"
                + "\nRule 9 : When there are no more than 10 seeds on the board, the player with the hand may propose the abandonment of the game. If it is accepted both players share the remaining seeds. If the total seed tray is less than 6, with no player has a total of seeds greater than 24. The game is drawn.\n");
    }
}
