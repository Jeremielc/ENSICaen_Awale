package com.ensicaen.awale.fxml;

import com.ensicaen.awale.pojo.PlayerData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Root layout Controller class. Manage all actions inside the view.
 *
 * @author Pierrick Hue and Jérémie Leclerc
 */
public class RootLayoutController implements Initializable {

    private Stage owner;
    private final int[] tab;
    private final Socket socket;
    private boolean playerTurn = true;
    private boolean isConnected = false;

    @FXML
    private Text playerOneName, playerTwoName, playerOneSeedNumber, playerTwoSeedNumber;
    @FXML
    private ImageView playerOneSeeds, playerTwoSeeds;
    @FXML
    private Button playerButton_1, playerButton_2, playerButton_3, playerButton_4, playerButton_5, playerButton_6;
    @FXML
    private Button opponentButton_1, opponentButton_2, opponentButton_3, opponentButton_4, opponentButton_5, opponentButton_6;

    public RootLayoutController() {
        socket = new Socket();
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
    /**
     * Handle the about button actions.
     */
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(owner);
        alert.setHeaderText("About");
        alert.setTitle("A propos");
        alert.setContentText("Auteurs : Pierrick HUE et Jérémie LECLERC.\n"
                + "Version : 1.0");

        alert.showAndWait();
    }

    @FXML
    /**
     * Handle the rules button actions.
     */
    public void handleRules(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/ensicaen/awale/fxml/RulesLayout.fxml"));
            BorderPane rulesLayout = fxmlLoader.load();

            RulesLayoutController rlc = fxmlLoader.getController();
            rlc.handleRules();

            Stage stage = new Stage();
            stage.setScene(new Scene(rulesLayout));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @FXML
    /**
     * Handle the connect button actions.
     */
    public void handleConnect() {
        String ip = "";
        int port = 0;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/ensicaen/awale/fxml/ConnectionLayout.fxml"));

            AnchorPane connectionLayout = loader.load();
            Scene scene = new Scene(connectionLayout);
            Stage connectionStage = new Stage();
            connectionStage.setScene(scene);

            ConnectionLayoutController clc = loader.getController();
            clc.setConnectionStage(connectionStage);

            connectionStage.showAndWait();

            if (!clc.getParameters().contains(";;") /*Un des parametres est vide.*/) {
                StringTokenizer st = new StringTokenizer(clc.getParameters(), ";");
                int i = 0;
                while (st.hasMoreTokens()) {
                    switch (i) {
                        case 0:
                            ip = st.nextToken();
                            break;
                        case 1:
                            port = Integer.parseInt(st.nextToken());
                            break;
                        case 2:
                            playerOneName.setText(st.nextToken());
                            break;
                        default:
                            break;
                    }
                    i++;
                }

                SocketAddress sa = new InetSocketAddress(ip, port);
                socket.connect(sa);
                isConnected = true;

            }
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            isConnected = false;
        }
    }

    @FXML
    /**
     * Handle the disconnect button actions.
     */
    public void handleDisconnect() {
        try {
            socket.close();
            isConnected = false;
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @FXML
    /**
     * Handle the close button actions.
     */
    public void handleClose() {
        Platform.exit();
    }

    @FXML
    /**
     * Handle the surrend button actions.
     */
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
    /**
     * Handle the restart button actions.
     */
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
    /**
     * Handle the player 1 button actions.
     */
    public void handlePlayerButton_1() {
        int position = 0;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the player 2 button actions.
     */
    public void handlePlayerButton_2() {
        int position = 1;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the player 3 button actions.
     */
    public void handlePlayerButton_3() {
        int position = 2;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the player 4 button actions.
     */
    public void handlePlayerButton_4() {
        int position = 3;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the player 5 button actions.
     */
    public void handlePlayerButton_5() {
        int position = 4;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the player 6 button actions.
     */
    public void handlePlayerButton_6() {
        int position = 5;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the opponent 1 button actions.
     */
    public void handleOpponentButton_1() {
        int position = 6;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the opponent 2 button actions.
     */
    public void handleOpponentButton_2() {
        int position = 7;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the opponent 3 button actions.
     */
    public void handleOpponentButton_3() {
        int position = 8;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the opponent 4 button actions.
     */
    public void handleOpponentButton_4() {
        int position = 9;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the opponent 5 button actions.
     */
    public void handleOpponentButton_5() {
        int position = 10;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    @FXML
    /**
     * Handle the opponent 6 button actions.
     */
    public void handleOpponentButton_6() {
        int position = 11;
        int seedNumber = tab[position];
        tab[position] = 0;

        int lastPos = dispatchSeeds(position, seedNumber);
        retrieveSeeds(lastPos, seedNumber);
        togglePlayerTurn();
        updateButtons();
    }

    /**
     * Allow to gather the seeds from the last play.
     *
     * @param lastPos The last position from the last play.
     * @param seedNumber The number of dispatched seeds.
     */
    private void retrieveSeeds(int lastPos, int seedNumber) {
        int i = lastPos;
        while (seedNumber > 0) {
            if (tab[i] == 2 || tab[i] == 3) {
                if (playerTurn) {
                    playerOneSeedNumber.setText(String.valueOf(
                            Integer.parseInt(playerOneSeedNumber.getText()) + tab[i])
                    );
                    tab[i] = 0;

                    playerOneSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_" + playerOneSeedNumber.getText() + ".png"));
                } else {
                    playerTwoSeedNumber.setText(String.valueOf(
                            Integer.parseInt(playerTwoSeedNumber.getText()) + tab[i])
                    );
                    tab[i] = 0;

                    playerTwoSeeds.setImage(new Image("/com/ensicaen/awale/resources/images/seeds/seed_" + playerTwoSeedNumber.getText() + ".png"));
                }

                i = (i + 11) % 12; //+11 pour revenir a la case précédente.
            } else {
                break;
            }

            seedNumber -= 1;
        }
    }

    /**
     * Allow to dispatch seeds from a box to the necessary number of boxes.
     *
     * @param position Starting position.
     * @param seedNumber Number of seeds to dispatch.
     * @return The last position index.
     */
    private int dispatchSeeds(int position, int seedNumber) {
        int i = position;
        while (seedNumber > 0) {
            i = (i + 1) % 12;

            if (i == position) {
                i = (i + 1) % 12;
            }

            tab[i] += 1;
            seedNumber -= 1;
        }

        return i;
    }

    /**
     * Update the text on the buttons.
     */
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

        if (playerTurn) {
            opponentButton_1.setDisable(true);
            opponentButton_2.setDisable(true);
            opponentButton_3.setDisable(true);
            opponentButton_4.setDisable(true);
            opponentButton_5.setDisable(true);
            opponentButton_6.setDisable(true);

            playerButton_1.setDisable(false);
            playerButton_2.setDisable(false);
            playerButton_3.setDisable(false);
            playerButton_4.setDisable(false);
            playerButton_5.setDisable(false);
            playerButton_6.setDisable(false);
        } else {
            playerButton_1.setDisable(true);
            playerButton_2.setDisable(true);
            playerButton_3.setDisable(true);
            playerButton_4.setDisable(true);
            playerButton_5.setDisable(true);
            playerButton_6.setDisable(true);

            opponentButton_1.setDisable(false);
            opponentButton_2.setDisable(false);
            opponentButton_3.setDisable(false);
            opponentButton_4.setDisable(false);
            opponentButton_5.setDisable(false);
            opponentButton_6.setDisable(false);
        }
    }

    /**
     * Format information in order to send them to the other player.
     */
    private void wrapAndSendInfo() {
        PlayerData pd = new PlayerData(playerOneName.getText());
        pd.setNbCatchedSeeds(Integer.parseInt(playerOneSeedNumber.getText()));

        String infos = pd.toString();
        infos += ";" + tab[0] + ";" + tab[1] + ";" + tab[2] + ";" + tab[3] + ";" + tab[4] + ";" + tab[5];
        infos += ";" + tab[6] + ";" + tab[7] + ";" + tab[8] + ";" + tab[9] + ";" + tab[10] + ";" + tab[11];
        
        System.out.println(infos);
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(infos);
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * Retrieve informations provided by the other player.
     *
     * @param infos A string representing the infos.
     */
    private void unwrapInfo() {
        String infos = "";
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            infos = dis.readUTF();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        
        StringTokenizer st = new StringTokenizer(infos, ";");

        int i = 0;
        while (st.hasMoreTokens()) {
            switch (i) {
                case 0:
                    playerTwoName.setText(st.nextToken());
                    break;
                case 1:
                    playerTwoSeedNumber.setText(st.nextToken());
                    break;
                case 2:
                    tab[0] = Integer.parseInt(st.nextToken());
                    break;
                case 3:
                    tab[1] = Integer.parseInt(st.nextToken());
                    break;
                case 4:
                    tab[2] = Integer.parseInt(st.nextToken());
                    break;
                case 5:
                    tab[3] = Integer.parseInt(st.nextToken());
                    break;
                case 6:
                    tab[4] = Integer.parseInt(st.nextToken());
                    break;
                case 7:
                    tab[5] = Integer.parseInt(st.nextToken());
                    break;
                case 8:
                    tab[6] = Integer.parseInt(st.nextToken());
                    break;
                case 9:
                    tab[7] = Integer.parseInt(st.nextToken());
                    break;
                case 10:
                    tab[8] = Integer.parseInt(st.nextToken());
                    break;
                case 11:
                    tab[9] = Integer.parseInt(st.nextToken());
                    break;
                case 12:
                    tab[10] = Integer.parseInt(st.nextToken());
                    break;
                case 13:
                    tab[11] = Integer.parseInt(st.nextToken());
                    break;
            }
        }
    }

    /**
     * Manage the alternance between player and opponent.
     */
    public void togglePlayerTurn() {
        playerTurn = !playerTurn;
    }

    /**
     * Set the owner of the view. Used in order to set other owners for pop-up.
     *
     * @param owner A stage object that is the owner of the view.
     */
    public void setOwner(Stage owner) {
        this.owner = owner;
    }
}
