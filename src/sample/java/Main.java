package sample.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        String sceneFile = "sample/sample.fxml";
        Parent root = FXMLLoader.load( getClass().getClassLoader().getResource( sceneFile ) );
        primaryStage.setScene(new Scene(root, 600  , 500));
        primaryStage.setTitle("Stoper");

        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }


}

