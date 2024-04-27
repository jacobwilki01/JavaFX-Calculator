package com.example.testfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ButtonsController implements Initializable {
    @FXML
    private Label display;

    private int realDisplay = 0;

    private int numOne = 0;

    private int numTwo = 0;

    private ButtonMode DisplayMode = ButtonMode.RESET;

    private CalculatorMode CalcMode = CalculatorMode.ADD;

    private boolean EditOne = true;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button zero;

    @FXML
    private Button add;

    @FXML
    private Button subtract;

    @FXML
    private Button multiply;

    @FXML
    private Button divide;

    @FXML
    private Button equals;

    @FXML
    private Button clear;

    public void initialize(URL url, ResourceBundle rb)
    {
        display.setText("0");
        display.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 35));

        zero.setOnAction(e -> numChange(0));
        one.setOnAction(e -> numChange(1));
        two.setOnAction(e -> numChange(2));
        three.setOnAction(e -> numChange(3));
        four.setOnAction(e -> numChange(4));
        five.setOnAction(e -> numChange(5));
        six.setOnAction(e -> numChange(6));
        seven.setOnAction(e -> numChange(7));
        eight.setOnAction(e -> numChange(8));
        nine.setOnAction(e -> numChange(9));

        List<Button> buttons = Arrays.asList(zero, one, two, three, four, five, six, seven, eight, nine, add, subtract, multiply, divide, equals, clear);
        for (Button button : buttons)
            button.setStyle("-fx-font-size:30");

        add.setOnAction(e -> modeChange(CalculatorMode.ADD));
        subtract.setOnAction(e -> modeChange(CalculatorMode.SUBTRACT));
        multiply.setOnAction(e -> modeChange(CalculatorMode.MULTIPLY));
        divide.setOnAction(e -> modeChange(CalculatorMode.DIVIDE));

        clear.setOnAction(e -> ClearCalculator());

        equals.setOnAction(e -> Total());
    }

    private void ClearCalculator()
    {
        DisplayMode = ButtonMode.RESET;
        EditOne = true;
        realDisplay = 0;
        numOne = 0;
        numTwo = 0;
        display.setText(String.valueOf(realDisplay));
    }

    private void Total()
    {
        numTwo = realDisplay;

        switch(CalcMode) {
            case ADD:
                realDisplay = numOne + numTwo;
                break;
            case SUBTRACT:
                realDisplay = numOne - numTwo;
                break;
            case MULTIPLY:
                realDisplay = numOne * numTwo;
                break;
            case DIVIDE:
                realDisplay = numTwo != 0 ? numOne / numTwo : 0;
                break;
            default:
                realDisplay = 0;
                break;
        }

        numOne = realDisplay;
        numTwo = 0;
        DisplayMode = ButtonMode.RESET;
        EditOne = true;

        display.setText(String.valueOf(realDisplay));
    }

    private void modeChange(CalculatorMode mode)
    {
        DisplayMode = ButtonMode.RESET;
        CalcMode = mode;

        if (EditOne)
        {
            EditOne = false;
            numOne = realDisplay;

            display.setText(String.valueOf(realDisplay));
        }
        else
        {
            Total();
            EditOne = false;
        }
    }

    @FXML
    private void numChange(int value)
    {
        if (DisplayMode == ButtonMode.RESET)
        {
            realDisplay = value;
            DisplayMode = ButtonMode.APPEND;
        }
        else
        {
            realDisplay = (realDisplay * 10) + value;
        }

        display.setText(String.valueOf(realDisplay));
    }
}