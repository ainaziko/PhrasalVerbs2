package sample;

//import com.gluonhq.charm.glisten.control.TextField;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import sample.animation.Shake;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;



public class Controller  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userInput;

    @FXML
    private Label russianVersion;

    @FXML
    private Button checkButton;

    @FXML
    private Button startButton;

    @FXML
    private Button nextButton;

    @FXML
    private ImageView image;

    @FXML
    void check(ActionEvent event) {

    }

    @FXML
    void startButtonE(ActionEvent event) {
    }

    @FXML
    private ProgressIndicator progress;

    String currentWord = getRandomWord();
    static double i = 0;

    @FXML
    void initialize() {
        startButton.setOnAction(e-> updateAtFirst());
        checkButton.setOnAction(e-> check());
        nextButton.setOnAction(e-> update());
        userInput.setOnKeyPressed(event -> {
            KeyCode keycode = event.getCode();
            if(keycode == KeyCode.ENTER){
                check();
            }
        });

    }

    public void updateAtFirst() {
        String word = getRandomWord();
        currentWord = word;
        int stopRussian = word.indexOf("-");
        if(stopRussian == -1){
            updateAtFirst();
        }
        russianVersion.setText(word.substring(0,stopRussian));
        startButton.setVisible(false);
        checkButton.setVisible(true);
        userInput.setVisible(true);
        progress.setVisible(true);

        //userInput.setText(currentWord.substring(stopRussian + 1, currentWord.length() ));
    }

    public void check(){
        int stopRussian = currentWord.indexOf("-");
        if(stopRussian == -1){
            update();
        } else {
            String userAnswer = userInput.getText().trim();
            String engVersion = currentWord.substring(stopRussian + 1).trim();
            if (!userAnswer.equals(engVersion)) {
                i = 0;
                progress.setProgress(i);
                Shake shakeTextField = new Shake(userInput);
                shakeTextField.PlayAnim();
                userInput.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
                userInput.setDisable(true);
                userInput.setText(engVersion);
                nextButton.setVisible(true);
                checkButton.setVisible(false);
            } else {
                i += 0.03;
                progress.setProgress(i);
                userInput.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                update();
            }
        }

    }

    public void update() {
        userInput.setDisable(false);
        checkButton.setVisible(true);
        nextButton.setVisible(false);
        String word = getRandomWord();
        currentWord = word;
        int stopIndex = word.indexOf("-");
        if (stopIndex == -1) {
            update();
        } else {
            russianVersion.setText(word.substring(0, stopIndex));
            userInput.clear();
            userInput.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
        }
    }


    public String getRandomWord(){
        ArrayList listOfWords = new ArrayList();
        try {
            //File myObj = new File("/Users/mac/Desktop/russanVersionOfPhrasalVerbs.txt"); //reading a file
            File myObj = new File("/Users/mac/Desktop/russanVersionOfPhrasalVerbs.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listOfWords.add(data);// adding every word to list
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //getting a random word from a list
        Random random = new Random();
        String word = (listOfWords.get(random.nextInt(listOfWords.size())) + " ");
        return word;
    }
}
