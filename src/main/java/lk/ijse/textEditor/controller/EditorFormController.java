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
import java.util.Scanner;
import java.util.StringTokenizer;

public class EditorFormController {
    static String fileName = null;
    private static final Stage stage = AppInitializer.stage;
    public  TextArea textEditorS;
    public Label lblLine;
    public Label lblWords;
    public Label lblChars;
    public Label lblFileName;
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
        lblFileName.setText(fileName==null ? "empty !" :  fileName);
        line=0;
        lblChars.setText("0");
        lblWords.setText("0");
        lblLine.setText("0");
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
            lblFileName.setText(split[split.length-1]);

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
        lblFileName.setText(fileName);

        String detail[]= textEditorS.getText().split("\n");
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter(openFilePath));

            for (String names :detail) {
                writer.write("\n"+names);
            }
            new Alert(Alert.AlertType.CONFIRMATION,"saved !").show();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveAs(ActionEvent actionEvent) {

        stage.setTitle(fileName);
        lblFileName.setText(fileName);
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

        /*try {
            String ss = null;
            Process p = Runtime.getRuntime().exec("cmd.exe /c start dir ");
            BufferedWriter writeer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            writeer.write("dir");
            writeer.flush();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            System.out.println("Here is the standard output of the command:\n");
            while ((ss = stdInput.readLine()) != null) {
                System.out.println(ss);
            }
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((ss = stdError.readLine()) != null) {
                System.out.println(ss);
            }

        } catch (IOException e) {
            System.out.println("FROM CATCH" + e.toString());
        }*/


        Process p;
        try {

            String test = "cmd.exe /c start cmd /k \" javac \"";
            String command = "cmd /c start cmd.exe"+" /c javac";
            Process child = Runtime.getRuntime().exec(test);
            child.waitFor();

//            p = Runtime.getRuntime().exec("cmd /c start cmd.exe");
//            Runtime.getRuntime().exec("cmd /c javac");
//            p.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

       /* System.out.println(openFilePath);

        String[] split = openFilePath.split("\\\\");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < split.length - 1; i++) {
            stringBuilder.append(i);
        }

        String newPath = String.valueOf(stringBuilder);

        File file = new File(newPath+"/"+split[split.length-1]);

        boolean delete = file.delete();

        if (delete) new Alert(Alert.AlertType.CONFIRMATION,"deleted !").show();

        else new Alert(Alert.AlertType.ERROR,"under development !").show();*/
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
        stage.setTitle(fileName==null ? "empty !" :"*"+ fileName);
        lblFileName.setText(fileName==null ? "empty !" :"*"+ fileName);
    }

    public void setDetails(){

        int count =0;

        StringTokenizer tokens = new StringTokenizer(textEditorS.getText());

        lblWords.setText(String.valueOf(tokens.countTokens()));

        lblLine.setText(String.valueOf(line));

        for (int i = 0; i < textEditorS.getText().length(); i++) {
            if (!(textEditorS.getText().charAt(i) ==' ')){
                count++;
            }
        }
        lblChars.setText(String.valueOf(count));
    }
}
