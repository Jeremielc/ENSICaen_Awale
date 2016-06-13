package com.ensicaen.awale.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jeremie
 */
public class ConnectionLayoutController implements Initializable {

    private Stage connectionStage;
    private String parameter;

    @FXML
    private TextField ip, port, name;

    public ConnectionLayoutController() {

    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ip.setText("127.0.0.1");
        port.setText("15425");
    }

    @FXML
    public void handleOk() {
        if (!name.getText().trim().equals("")) {
            format();
            connectionStage.close();
        }
    }

    @FXML
    public void handleCancel() {
        connectionStage.close();
    }

    private void format() {
        String s = ip.getText().trim()
                + ";" + port.getText().trim()
                + ";" + name.getText().trim();

        this.parameter = s;
    }

    public void setConnectionStage(Stage connectionStage) {
        this.connectionStage = connectionStage;
    }
    
    public String getParameters() {
        return this.parameter;
    }
}
