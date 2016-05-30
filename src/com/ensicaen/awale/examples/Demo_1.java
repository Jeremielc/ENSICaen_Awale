package com.ensicaen.awale.examples;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Demo_1 extends Application {
    private static TextField userText;
    private static TextArea chatWindow;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    private static String message = "";
    private static String serverIP;
    private static Socket connection;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        HBox yourMessage = new HBox();
        String host = "127.0.0.1";
        serverIP = host;
        userText = new TextField();
        userText.setEditable(false);
        userText.setOnAction(e
                -> {
            sendMessage(e.getEventType().toString());
            userText.setText("");
        });
        chatWindow = new TextArea();
        chatWindow.setPrefSize(300, 150);
        yourMessage.setAlignment(Pos.CENTER);
        yourMessage.setPadding(new Insets(10));
        yourMessage.getChildren().add(userText);
        root.setBottom(yourMessage);
        root.setCenter(chatWindow);
        startRunning();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    //Connect to the server
    public static void startRunning() {
        try {
            connecToServer();
            setupStreams();
            whileChatting();
        } catch (EOFException eof) {
            showMessage("\n Client terminated the connection...");
        } //If something really went wrong and non-human closed the connection
        catch (IOException io) {
            io.printStackTrace();
        } finally {
            closeStreamsAndSockets();
        }
    }

    private static void connecToServer() throws IOException {
        showMessage("Attempting connection... \n");
        //This is the connection we want to use (IpAddress)... THIS IS THE PORT WE WANT TO USE
        connection = new Socket(InetAddress.getByName(serverIP), 6789);
        showMessage("Connected to " + connection.getInetAddress().getHostName());
    }

    private static void setupStreams() throws IOException {
        //Creating the pathway that allows to connect to another computer. The computer that the socket created.
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are now setup! \n");
    }

    //While chatting with server.
    private static void whileChatting() throws IOException {
        ableToType(true);
        do {
            try {
                //We are gonna read whatever object they send, treat is as a string and save it in message.
                message = (String) input.readObject();
                showMessage("\n " + message);
            } catch (ClassNotFoundException cnfe) {
                showMessage("Unknown object type...");
            }
        } //As long as the server doesn't type end, we will keep chatting.
        while (!message.equals("SERVER - END"));
    }

    //Close Streams and sockets
    private static void closeStreamsAndSockets() {
        showMessage("\nClosing connection...");
        ableToType(false);
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //Send messages to the server and makes the clients able to see them.
    private static void sendMessage(String message) {
        try {
            output.writeObject("CLIENT - " + message);
            output.flush();
            //Displays in GUI
            showMessage("\nCLIENT - " + message);
        } catch (IOException io) {
            chatWindow.appendText("\n Unable to send the message!");
        }
    }

    //Shows the message / Updates chatwindow
    private static void showMessage(final String text) {
        //Creating a thread
        Platform.runLater(() -> {
            //Adds a message to the end of document. Updating the Chat Window.
            chatWindow.appendText(text);
        });
    }
    
    //Able to type
    private static void ableToType(final boolean permission) {
        //Creating a thread
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                //Able to type now
                userText.setEditable(permission);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
