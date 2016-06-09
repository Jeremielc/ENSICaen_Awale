package com.ensicaen.awale.fxml;

import com.ensicaen.awale.pojo.PlayerData;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
    private final int[] tab;

    @FXML
    private Text playerOneName, playerTwoName, playerOneSeedNumber, playerTwoSeedNumber;
    @FXML
    private ImageView playerOneSeeds, playerTwoSeeds;
    @FXML
    private Button playerButton_1, playerButton_2, playerButton_3, playerButton_4, playerButton_5, playerButton_6;
    @FXML
    private Button opponentButton_1, opponentButton_2, opponentButton_3, opponentButton_4, opponentButton_5, opponentButton_6;

    public RootLayoutController() {
        tab = new int[12];
        for (int i = 0; i < 12; i++) {
            tab[i] = 4;
        }
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

        playerOneSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_0.png"));
        playerTwoSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_0.png"));
        
        for (int i = 0; i < 12; i++) {
            tab[i] = 4;
        }

        updateButtons();
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
        Platform.exit();
    }

    @FXML
    public void handleSurrend() {
        int remainingSeeds = 48 - Integer.parseInt(playerOneSeedNumber.getText());

        playerTwoSeedNumber.setText(String.valueOf(remainingSeeds));
        playerTwoSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_" + remainingSeeds + ".png"));

        for (int i = 0; i < 12; i++) {
            tab[i] = 0;
        }
        
        updateButtons();
    }

    @FXML
    public void handleRestart() {
        playerOneSeedNumber.setText("0");
        playerTwoSeedNumber.setText("0");

        playerOneSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_0.png"));
        playerTwoSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_0.png"));
        
        for (int i = 0; i < 12; i++) {
            tab[i] = 4;
        }

        updateButtons();
    }

    @FXML
    public void handlePlayerButton_1() {
        int position = 0;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handlePlayerButton_2() {
        int position = 1;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handlePlayerButton_3() {
        int position = 2;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handlePlayerButton_4() {
        int position = 3;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handlePlayerButton_5() {
        int position = 4;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handlePlayerButton_6() {
        int position = 5;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handleOpponentButton_1() {
        int position = 6;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handleOpponentButton_2() {
        int position = 7;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handleOpponentButton_3() {
        int position = 8;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handleOpponentButton_4() {
        int position = 9;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handleOpponentButton_5() {
        int position = 10;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }

    @FXML
    public void handleOpponentButton_6() {
        int position = 11;
        int seedNumber = tab[position];
        tab[position] = 0;
        
        dispatchSeeds(position, seedNumber);
        updateButtons();
    }
    
    private void dispatchSeeds(int position, int seedNumber) {
        int i = position;
        while (seedNumber > 0) {
            i = (i + 1) % 12;
            
            if (i == position) {
                i = (i + 1) % 12;
                tab[i] += 1;
            } else {
                tab[i] += 1;
            }
            
            seedNumber -= 1;
        }
    }
    
    private void updateButtons() {
        playerButton_1.setText(String.valueOf(tab[0]));
        playerButton_2.setText(String.valueOf(tab[1]));
        playerButton_3.setText(String.valueOf(tab[2]));
        playerButton_4.setText(String.valueOf(tab[3]));
        playerButton_5.setText(String.valueOf(tab[4]));
        playerButton_6.setText(String.valueOf(tab[5]));
        
        opponentButton_1.setText(String.valueOf(tab[6]));
        opponentButton_2.setText(String.valueOf(tab[7]));
        opponentButton_3.setText(String.valueOf(tab[8]));
        opponentButton_4.setText(String.valueOf(tab[9]));
        opponentButton_5.setText(String.valueOf(tab[10]));
        opponentButton_6.setText(String.valueOf(tab[11]));
    }

    private void wrapAndSendInfo() {
        PlayerData pd = new PlayerData(playerOneName.getText());
        pd.setNbCatchedSeeds(Integer.parseInt(playerOneSeedNumber.getText()));

        String infos = pd.toString();
        //send
    }

    private void unwrapInfo(String infos) {
        String received = infos;
        StringTokenizer st = new StringTokenizer(infos, ";");

        int i = 0;
        while (st.hasMoreTokens()) {
            if (i == 0) {
                playerTwoName.setText(st.nextToken());
            } else if (i == 1) {
                playerTwoSeedNumber.setText(st.nextToken());
            }
        }
    }

    public void setOwner(Stage owner) {
        this.owner = owner;
    }
}
