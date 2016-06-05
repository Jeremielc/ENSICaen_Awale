package com.ensicaen.awale.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private Text playerOneName, playerTwoName, playerOneSeedNumber, playerTwoSeedNumber;
    @FXML
    private ImageView playerOneSeeds, playerTwoSeeds;
    @FXML
    private Button playerButton_1, playerButton_2, playerButton_3, playerButton_4, playerButton_5, playerButton_6;
    @FXML
    private Button opponentButton_1, opponentButton_2, opponentButton_3, opponentButton_4, opponentButton_5, opponentButton_6;
    
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
        playerOneName.setText("Player 1");
        playerTwoName.setText("Player 2");
        
        playerOneSeedNumber.setText("0");
        playerTwoSeedNumber.setText("0");
        
        //playerOneSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_1.png"));
        //playerTwoSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_1.png"));
        
        playerButton_1.setText("4");
        playerButton_2.setText("4");
        playerButton_3.setText("4");
        playerButton_4.setText("4");
        playerButton_5.setText("4");
        playerButton_6.setText("4");
        opponentButton_1.setText("4");
        opponentButton_2.setText("4");
        opponentButton_3.setText("4");
        opponentButton_4.setText("4");
        opponentButton_5.setText("4");
        opponentButton_6.setText("4");
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
    
    @FXML
    public void handleConnect() {
        
    }
    
    @FXML
    public void handleClose() {
        
    }
    
    @FXML
    public void handleSurrend() {
        
    }
    
    @FXML
    public void handleRestart() {
        
    }
    
    @FXML
    public void handlePlayerButton_1() {
        
    }
    
    @FXML
    public void handlePlayerButton_2() {
        
    }
    
    @FXML
    public void handlePlayerButton_3() {
        
    }
    
    @FXML
    public void handlePlayerButton_4() {
        
    }
    
    @FXML
    public void handlePlayerButton_5() {
        
    }
    
    @FXML
    public void handlePlayerButton_6() {
        
    }
    
    @FXML
    public void handleOpponentButton_1() {
        
    }
    
    @FXML
    public void handleOpponentButton_2() {
        
    }
    
    @FXML
    public void handleOpponentButton_3() {
        
    }
    
    @FXML
    public void handleOpponentButton_4() {
        
    }
    
    @FXML
    public void handleOpponentButton_5() {
        
    }
    
    @FXML
    public void handleOpponentButton_6() {
        
    }
    
    private void wrapInfoToJson() {
        
    }
    
    private void unwrapJson() {
        
    }
    
    public void setOwner(Stage owner) {
        this.owner = owner;
    }
}
