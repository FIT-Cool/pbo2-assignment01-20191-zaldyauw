package com.zaldy.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BangunRuangController {
    public TextField textLength;
    public TextField textWidth;
    public TextField textRadius;
    public TextField textHeight;
    public Button idBall;
    public Button idTube;
    public Button idSquare;
    public double A;
    public double C;
    public double V;
    public boolean square = false;
    public boolean ball = false;
    public boolean tube = false;


    public void SquareAction(ActionEvent actionEvent) {
        textLength.setText("");
        textWidth.setText("");
        textRadius.setText("");
        textHeight.setText("");
        square = true;
        ball = false;
        tube = false;
        textLength.setDisable(false);
        textWidth.setDisable(false);
        textRadius.setDisable(true);
        textHeight.setDisable(true);
    }

    public void BallAction(ActionEvent actionEvent) {
        textLength.setText("");
        textWidth.setText("");
        textRadius.setText("");
        textHeight.setText("");
        square = false;
        ball = true;
        tube = false;
        textLength.setDisable(true);
        textWidth.setDisable(true);
        textRadius.setDisable(false);
        textHeight.setDisable(true);
    }

    public void TubeAction(ActionEvent actionEvent) {
        textLength.setText("");
        textWidth.setText("");
        textRadius.setText("");
        textHeight.setText("");
        square = false;
        ball = false;
        tube = true;
        textLength.setDisable(true);
        textWidth.setDisable(true);
        textRadius.setDisable(false);
        textHeight.setDisable(false);
    }

    public void SubmitAction(ActionEvent actionEvent) {
        if(square == true)
        {
            double length = Double.parseDouble(textLength.getText());
            double width = Double.parseDouble(textWidth.getText());
            A = length * width;
            C = 2*(length + width);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Surface Area: " + String.valueOf(A) + ", Circumference: " + String.valueOf(C));
            alert.show();
        }
        else if(ball == true)
        {
            double radius = Double.parseDouble(textRadius.getText());
            V = ( 4.0 / 3.0 ) * Math.PI * Math.pow( radius, 3 );
            A = 4.0 * Math.PI * Math.pow( radius, 2 );
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Surface Area: " + String.valueOf(A) + ", Volume: " + String.valueOf(V));
            alert.show();
        }
        else
        {
            double radius = Double.parseDouble(textRadius.getText());
            double height = Double.parseDouble(textHeight.getText());
            V = Math.PI * (radius*radius) * height;
            A = 2 * Math.PI * radius * height + 2 * Math.PI * (radius*radius);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Surface Area: " + String.valueOf(A) + ", Volume: " + String.valueOf(V));
            alert.show();
        }
    }
}
