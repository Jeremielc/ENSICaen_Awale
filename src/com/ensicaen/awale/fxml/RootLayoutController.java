package com.ensicaen.awale.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jérémie Leclerc : jeremie.leclerc@fime.com
 */
public class RootLayoutController implements Initializable {
    
    private Stage owner;
    
    @FXML
    private Text playerOneName, playerTwoName, playerOneSeedNumber, playerTwoSeedNumber, txtRules;
    @FXML
    private ImageView playerOneSeeds, playerTwoSeeds;
    
    public RootLayoutController() {
        
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(owner);
        alert.setHeaderText("About");
        alert.setTitle("A propos");
        alert.setContentText("Auteurs : Pierrick HUE et Jérémie LECLERC.\n"
                + "Version : 1.0");
        
        alert.showAndWait();
    }
    
    public void handleRules(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ensicaen/awale/fxml/RulesLayout.fxml"));
            BorderPane rulesLayout = fxmlLoader.load();
            
            RulesLayoutController rlc = fxmlLoader.getController();
            rlc.handleRules(event);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(rulesLayout));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public void setOwner(Stage owner) {
        this.owner = owner;
    }
}
