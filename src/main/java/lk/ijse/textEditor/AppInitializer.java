package lk.ijse.textEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/editor-form.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("text Editor !");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
