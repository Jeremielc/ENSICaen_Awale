package com.ensicaen.awale.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jérémie Leclerc : jeremie.leclerc@fime.com
 */
public class RootLayoutController implements Initializable {

    private Stage owner;
    
    @FXML private Text playerOneName, playerTowName, playerOneSeedNumber, playerStowSeedNumber;
    @FXML private ImageView playerOneSeeds, playerTowSeeds;
    
    public RootLayoutController() {
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setOwner(Stage owner) {
        this.owner = owner;
    }
}
