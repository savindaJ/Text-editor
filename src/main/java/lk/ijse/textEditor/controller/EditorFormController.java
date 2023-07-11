package lk.ijse.textEditor.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.textEditor.AppInitializer;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.Arrays;
import java.util.Scanner;

public class EditorFormController {


    static String fileName = null;

    private static final Stage stage = AppInitializer.stage;

    public  TextArea textEditorS;
    public Label lblLine;
    public Label lblWords;
    public Label lblChars;
    @FXML
    private VBox lineVbox;
    private int line = 0;

    private String openFilePath = null;

    public void openNew(ActionEvent actionEvent) {
        textEditorS.setText("");
        openFilePath="";
        Platform.runLater(()->lineVbox.getChildren().clear());
        fileName="new text";
        stage.setTitle(fileName);
        line=0;
    }

   /* public static void setClose(){

        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to Delete ?", yes, no).showAndWait();

        if(result.orElse(no)==yes){
            stage.setTitle(fileName);

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select a directory");

            File selectedDirectory = directoryChooser.showDialog(null);

            String detail[]=textEditorS.getText().split("\n");

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
        }else {
            stage.close();
        }
    }*/

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
                    textEditorS.appendText("\n"+textLine);
                    setDetails();
                }

            } catch (FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR,"something else !").show();
            }
        }
    }

    public void saveFile(ActionEvent actionEvent) throws IOException {

        stage.setTitle(fileName);

        String detail[]= textEditorS.getText().split("\n");
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

        stage.setTitle(fileName);

        String newPath;

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a directory");

        File selectedDirectory = directoryChooser.showDialog(null);

        String detail[]=textEditorS.getText().split("\n");

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
    }

    public void viewDelete(ActionEvent actionEvent) {
        System.out.println(openFilePath);

        String[] split = openFilePath.split("\\\\");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            stringBuilder.append(i);
        }

        String newPath = String.valueOf(stringBuilder);

        File file = new File(newPath+"/"+split[split.length-1]);

        boolean delete = file.delete();

        if (delete) new Alert(Alert.AlertType.CONFIRMATION,"deleted !").show();

        else new Alert(Alert.AlertType.ERROR,"under development !").show();
    }

    public void viewHelp(ActionEvent actionEvent) throws IOException {
        Desktop.getDesktop().browse(URI.create("https://github.com/savindaJ"));
    }

    public void keyPressedArea(KeyEvent keyEvent) {
        setDetails();
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
        setDetails();
        stage.setTitle("*"+fileName);
    }

    public void setDetails(){

        String [] split = textEditorS.getText().split(" ");
        System.out.println(Arrays.toString(split));
        lblWords.setText(String.valueOf(split.length));
        lblLine.setText(String.valueOf(line));
        lblChars.setText(String.valueOf(textEditorS.getText().length()));
    }
}
