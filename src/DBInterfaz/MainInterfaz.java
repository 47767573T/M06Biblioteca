package DBInterfaz;

/**
 * Created by Moises on 18/01/2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainInterfaz extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfazGrafica.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("M06Biblioteca");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }
}
