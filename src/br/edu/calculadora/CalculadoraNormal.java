package br.edu.calculadora;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Alunos
 */
public class CalculadoraNormal {

    private javax.swing.JTextField display;
    protected double num1;
    protected double num2;
    protected Boolean selectNum2 = false;
    protected double res;
    protected String symbol;
    protected ArrayList<String> history = new ArrayList<String>();

    public String isNotDivZero = "Não é possivel dividir por 0";

    public void resetButtonsEnabledDisplayFont() {
        display.setText("0");
        display.setFont(new Font("Dialog", Font.BOLD, 32));
    }

    public void onClickNumber(java.awt.event.ActionEvent e, Runnable setEnabledIfDivZero) {
        if (display.getText().equals(isNotDivZero)) {
            setEnabledIfDivZero.run();
            resetButtonsEnabledDisplayFont();
        }
        String num;
        if (display.getText().equals("0")) {
            num = e.getActionCommand();
        } else {
            num = display.getText() + e.getActionCommand();
        }
        display.setText(num);
    }

    public void onClickDelete(Runnable setEnabledIfDivZero) {
        if (display.getText().equals(isNotDivZero)) {
            setEnabledIfDivZero.run();
            resetButtonsEnabledDisplayFont();
        }
        String num = display.getText();
        display.setText(num.length() == 1 ? "0" : num.substring(0, num.length() - 1));
    }

    public void onClickAC(Runnable setEnabledIfDivZero) {
        if (display.getText().equals(isNotDivZero)) {
            setEnabledIfDivZero.run();
            resetButtonsEnabledDisplayFont();
        }
        num1 = 0;
        num2 = 0;
        display.setText("0");
    }

    public void onClickOperator(java.awt.event.ActionEvent e, Runnable setEnabledIfDivZero) {
        if (display.getText().equals(isNotDivZero)) {
            setEnabledIfDivZero.run();
            resetButtonsEnabledDisplayFont();
        }
        if (!display.getText().replaceAll(" ", "").equals("")) {
            num1 = Double.parseDouble(display.getText().replace(",", "."));
            selectNum2 = false;
        }
        display.setText("");
        symbol = e.getActionCommand();
    }

    public void onClickPoint(java.awt.event.ActionEvent e, Runnable setEnabledIfDivZero) {
        if (display.getText().equals(isNotDivZero)) {
            setEnabledIfDivZero.run();
            resetButtonsEnabledDisplayFont();
        }
        String num = "";
        if (display.getText().replaceAll(" ", "").equals("")) {
            num = "0,";
        } else if (!display.getText().contains(",")) {
            num = display.getText() + e.getActionCommand();
        } else {
            num = display.getText();
        }

        display.setText(num);
    }

    public void onClickResult(java.awt.event.ActionEvent e, Runnable setEnabledIfDivZero, Runnable setEnabledIfDivZeroTrue) {
        if (!display.getText().replaceAll(" ", "").equals("") && !display.getText().equals(isNotDivZero)) {
            if (selectNum2) {
                num1 = Double.parseDouble(display.getText().replace(",", "."));
            } else {
                num2 = Double.parseDouble(display.getText().replace(",", "."));
                selectNum2 = true;
            }

            if (symbol != null) {
                switch (symbol) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "×":
                        res = num1 * num2;
                        break;
                    case "÷":
                        if (selectNum2 && (int) num2 == 0) {
                            display.setText(isNotDivZero);
                            display.setFont(new Font("Dialog", Font.BOLD, 18));
                            setEnabledIfDivZero.run();
                        } else {
                            res = num1 / num2;
                        }
                        break;
                    case "%":
                        res = (num1 * num2) / 100.0;
                        break;
                    default:
                        res = 0.0;
                }
                if (!display.getText().equals(isNotDivZero)) {
                    String result = String.format("%.2f", res);
                    if (result.substring(result.length() - 2, result.length()).equals("00")) {
                        result = String.format("%.0f", res);
                    }
                    display.setText(result);
                    history.add(num1 + " " + symbol + " " + num2 + " = " + res);
                }

            }

        } else {
            setEnabledIfDivZeroTrue.run();
            resetButtonsEnabledDisplayFont();
            num1 = 0;
            num2 = 0;
            symbol = "";
        }

    }

    public double somar() {
        res = num1 + num2;
        return res;
    }

    public double subtrair() {
        res = num1 - num2;
        return res;
    }

    public double multiplicar() {
        res = num1 * num2;
        return res;
    }

    public double dividir() {
        res = num1 / num2;
        return res;
    }

    public double porcentagem() {
        res = (num1 * num2) / 100.0;
        return res;
    }

    public JTextField getDisplay() {
        return display;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void setDisplay(javax.swing.JTextField display) {
        this.display = display;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
