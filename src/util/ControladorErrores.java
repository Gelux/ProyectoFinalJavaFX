/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author calata98
 */
public class ControladorErrores {
    
    
    
    
    
    
    public void conISBN(TextField isbnT){
        
        isbnT.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (isbnT.getText().length() > 13 || !isNumeric(isbnT.getText())) {
                    if (!isbnT.getText().equals("")) {
                        isbnT.setText(oldValue.toString());
                    }
                }

            }
        });
        
    }
    
    
    public boolean isNumeric(String texto) {
        boolean num = false;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '0'
                    || texto.charAt(i) == '1'
                    || texto.charAt(i) == '2'
                    || texto.charAt(i) == '3'
                    || texto.charAt(i) == '4'
                    || texto.charAt(i) == '5'
                    || texto.charAt(i) == '6'
                    || texto.charAt(i) == '7'
                    || texto.charAt(i) == '8'
                    || texto.charAt(i) == '9') {
                num = true;
            }else{
                num = false;
            }
        }
        return num;
    }
    
    private boolean isPrecio(String texto) {
        int punto = 0;

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == '0'
                    || texto.charAt(i) == '1'
                    || texto.charAt(i) == '2'
                    || texto.charAt(i) == '3'
                    || texto.charAt(i) == '4'
                    || texto.charAt(i) == '5'
                    || texto.charAt(i) == '6'
                    || texto.charAt(i) == '7'
                    || texto.charAt(i) == '8'
                    || texto.charAt(i) == '9'
                    || texto.charAt(i) == '.'
                    || texto.charAt(i) == ',') {
                if (texto.charAt(i) == '.') {
                    punto++;
                }
            } else {
                return false;
            }
        }
        return punto < 2;
    }
    
    
}
