package com.ensicaen.awale.main;

import com.ensicaen.awale.fxml.RootLayoutController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jérémie Leclerc : jeremie.leclerc@fime.com
 */
public class ENSICaen_Awale extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/ensicaen/awale/fxml/RootLayout.fxml"));
            BorderPane rootLayout = loader.load();
            
            Scene scene = new Scene(rootLayout);
            
            RootLayoutController rlc = loader.getController();
            rlc.setOwner(primaryStage);
            
            primaryStage.getIcons().add(new Image("/com/ensicaen/awale/images/icon.png"));
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
