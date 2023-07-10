package lk.ijse.textEditor.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EditorFormController {

    @FXML
    private TextArea textEditor;
    @FXML
    private VBox lineVbox;

    private int line = 0;

    public void openNew(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile!=null){
            String path =selectedFile.getAbsolutePath();
    try {
            Scanner input = new Scanner(selectedFile);
            while (input.hasNext()) {
            String line = input.nextLine();
            textEditor.appendText("\n"+line);
            }
            } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"something else !").show();
            }
        }

    }

    public void openFile(ActionEvent actionEvent) {
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
