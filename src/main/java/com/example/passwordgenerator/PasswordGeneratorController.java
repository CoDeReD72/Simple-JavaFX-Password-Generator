package com.example.passwordgenerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;

public class PasswordGeneratorController {
    @FXML private TextField passwordLength;

    @FXML private CheckBox numberCheck;

    @FXML private CheckBox specialCheck;

    @FXML private CheckBox capitalCheck;

    @FXML private Button generateButton;

    @FXML private TextField finalPassword;

    @FXML
    protected void generateAction(ActionEvent e) throws NullPointerException{
        int length = Integer.parseInt(passwordLength.getText());

        if(length<4){length=4;} //ENFORCE MINIMUM LENGTH

        boolean number = numberCheck.isSelected();
        boolean special = specialCheck.isSelected();
        boolean capital = capitalCheck.isSelected();

        finalPassword.setText(generatePassword(length, number, special, capital));
    }

    private static String generatePassword(int length, boolean number, boolean special, boolean capital) {
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "1234567890";
        String specialCharacters = "!@#$-_";
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String combinedChars = lowerCaseLetters;
        if(number){combinedChars+=numbers;}
        if(special){combinedChars+=specialCharacters;}
        if(capital){combinedChars+=capitalCaseLetters;}

        // ==== CREATING PASSWORD ====
        Random random = new Random();
        char[] password = new char[length];

        boolean numberValidated = false;
        boolean specialValidated = false;
        boolean capitalValidated = false;

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(combinedChars.length());
            password[i] = combinedChars.charAt(randomIndex);

            // ==== VALIDATION OF PASSWORD REQUIREMENTS
            if(number && !numberValidated) {
                for (int j = 0; j < numbers.length(); j++) {
                    if (combinedChars.charAt(randomIndex) == numbers.charAt(j)) {
                        numberValidated = true;
                    }
                }
            }

            if(special && !specialValidated){
                for (int j = 0; j < specialCharacters.length(); j++) {
                    if (combinedChars.charAt(randomIndex) == specialCharacters.charAt(j)) {
                        specialValidated = true;
                    }
                }
            }

            if(capital && !capitalValidated){
                for (int j = 0; j < specialCharacters.length(); j++) {
                    if (combinedChars.charAt(randomIndex) == specialCharacters.charAt(j)) {
                        specialValidated = true;
                    }
                }
            }

        }




        String finalPassword = new String(password);
        return finalPassword;
    }
}