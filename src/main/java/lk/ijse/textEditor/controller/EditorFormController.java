package lk.ijse.textEditor.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.textEditor.AppInitializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EditorFormController {


    private final Stage stage = AppInitializer.stage;
    @FXML
    private MenuItem openMenu;
    @FXML
    private TextArea textEditor;
    @FXML
    private VBox lineVbox;

    private int line = 0;

    private String openFilePath = null;

    public void openNew(ActionEvent actionEvent) {


    }

    public void openFile(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile!=null){
            openFilePath = selectedFile.getAbsolutePath();
            String[] split = openFilePath.split("\\\\");
            stage.setTitle(split[split.length-1]);

            try {
                Scanner input = new Scanner(selectedFile);
                while (input.hasNext()) {

                    String textLine = input.nextLine();
                    line++;

                    HBox hBox = new HBox();
                    Label label = new Label();
                    label.setStyle("-fx-font-size: 15");
                    label.setText(String.valueOf(line));
                    hBox.setAlignment(Pos.CENTER);
                    hBox.getChildren().add(label);
                    Platform.runLater(()->lineVbox.getChildren().addAll(hBox));

                    textEditor.appendText("\n"+textLine);

                }

            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,"something else !").show();
            }
        }
    }

    public void saveFile(ActionEvent actionEvent) {
    }

    public void saveAs(ActionEvent actionEvent) {
    }

    public void viewDelete(ActionEvent actionEvent) {
    }

    public void viewHelp(ActionEvent actionEvent) {
    }

    public void keyPressedArea(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER) {
            line++;
            HBox hBox = new HBox();
            Label label = new Label();
            label.setStyle("-fx-font-size: 15");
            label.setText(String.valueOf(line));
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(label);
            Platform.runLater(()->lineVbox.getChildren().addAll(hBox));
        }
    }

}
