package com.patikaDev.Helper;

// Helper Sınıfını çoğu yerde lazım olacak operasyonları tutmak için kullanacağız.
// ÖRNEK: JFrame'i ekranın ortasına yerleştirmek için
// ekranın ortasını x ve y ekseninde veren bir metot.

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setLayout(){
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){

                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    public static int getScreenCenterPoint(String axis, Dimension size){
        int point;
        switch (axis){
            case "x":
                point = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-size.getWidth()) / 2;
                break;
            case "y":
                point = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-size.getHeight()) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField jTextField){
        return jTextField.getText().trim().isEmpty();
    }
    public static boolean isFieldEmpty(JTextArea jTextField){
        return jTextField.getText().trim().isEmpty();
    }

    public static void showMessage(String str){
        optionPaneTR();
        String message;
        String title;
        switch (str){
            case "fill":
                message = "Please fill all the input fields";
                title = "Warning";
                break;
            case "done":
                message = "Operation is successfully completed";
                title = "Result";
                break;
            case "error":
                message = "Operation is failed";
                title = "Error";
                break;
            default:
                message = str;
                title = "dialog";
        }
        JOptionPane.showMessageDialog(
                null, message, title, JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static boolean confirm(String str){
        String message;
        String title;
        switch (str){
            case "sure":
                message = "Are you sure?";
                title = "Confirm";
                break;
            default:
                message = str;
                title = "dialog";
        }
        // if zero YES, else NO
        return  JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION) == 0;

    }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }
}
