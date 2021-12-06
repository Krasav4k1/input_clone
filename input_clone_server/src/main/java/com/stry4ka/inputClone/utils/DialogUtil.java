package com.stry4ka.inputClone.utils;

import javax.swing.*;

public class DialogUtil {

    public DialogUtil() {

    }

    public static String showInputDialog(String message, String input) {
        String result = String.valueOf(JOptionPane.showInputDialog(null, message, "Input Clone",
                JOptionPane.QUESTION_MESSAGE, null, null, input));
        if (result.equals("null")) {
            System.exit(0);
        }
        return result;
    }


}
