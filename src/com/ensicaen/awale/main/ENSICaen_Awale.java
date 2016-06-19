package com.ensicaen.awale.main;

import com.ensicaen.awale.fxml.RootLayoutController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Pierrick Hue and Jérémie Leclerc
 */
public class ENSICaen_Awale extends Application {

    @Override
    /**
     * Launch the javaFX stage and scene.
     */
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/ensicaen/awale/fxml/RootLayout.fxml"));
            BorderPane rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);

            RootLayoutController rlc = loader.getController();
            rlc.setOwner(primaryStage);

            primaryStage.getIcons().add(new Image("/com/ensicaen/awale/resources/images/icon.png"));
            primaryStage.setResizable(false);
            primaryStage.setTitle("Awale");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * Program entry point. Automatically call the start() method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
