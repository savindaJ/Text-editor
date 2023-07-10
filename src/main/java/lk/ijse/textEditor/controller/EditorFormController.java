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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.textEditor.AppInitializer;

import java.io.*;
import java.util.Scanner;

public class EditorFormController {


    String fileName = null;
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
            fileName=split[split.length-1];
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

    public void saveFile(ActionEvent actionEvent) throws IOException {

        String detail[]= textEditor.getText().split("\n");
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(openFilePath));

            for (String names :detail) {
                writer.write("\n"+names);
            }
            new Alert(Alert.AlertType.CONFIRMATION,"saved").show();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveAs(ActionEvent actionEvent) {

        String newPath;

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");

        File selectedDirectory = directoryChooser.showDialog(null);

        String detail[]=textEditor.getText().split("\n");

        System.out.println(selectedDirectory.getAbsolutePath());
        try  {
            FileWriter writer = new FileWriter(selectedDirectory.getAbsolutePath()+"/"+ "samplefile"+".txt");


                BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(selectedDirectory.getAbsolutePath()+"/"+ "samplefile"+".txt"));

                for (String names :detail) {
                    bufferedWriter.write("\n"+names);
                }
                new Alert(Alert.AlertType.CONFIRMATION,"saved").show();
                bufferedWriter.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
            new Alert(Alert.AlertType.CONFIRMATION,"saved !").show();
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

    public void keyTyped() {
        stage.setTitle("*"+fileName);
    }
}
