
import javafx.application.Application;

import javax.swing.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GUI extends Application {
    java.util.List calibers;
    java.util.List caliberNames;

    public void start(Stage primaryStage) {
        //Setup
        configs();
        Calculations calc = new Calculations();
        System.out.println(calibers);
        System.out.println(caliberNames);

        //Scene Setup

        primaryStage.setTitle("Ballistics Calculator");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        Text scenetitle = new Text("Ballistics");
        scenetitle.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //Adding all of the elements

        Label lcali = new Label("Caliber:");
        grid.add(lcali,0,1);
        ObservableList<String> calis = FXCollections.observableArrayList(caliberNames);
        ComboBox cali = new ComboBox(calis);
        grid.add(cali, 1, 1);
        Label lrange = new Label("Range:");
        grid.add(lrange,0,2);
        TextField range = new TextField();
        grid.add(range,1,2);
        Label lelev = new Label("Elevation:");
        grid.add(lelev,0,3);
        TextField elev = new TextField();
        grid.add(elev,1,3);
        Label output = new Label();
        grid.add(output,0,5);

        //Constructing the Buttons

        javafx.scene.control.Button confirm = new javafx.scene.control.Button("Confirm Selection");
        confirm.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Object vel = calibers.toArray()[caliberNames.indexOf(cali.getValue())];
                    double a = Double.parseDouble(vel.toString());
                    double b = Double.parseDouble(range.getText());
                    double c = Double.parseDouble(elev.getText());
                    output.setTextFill(Color.GREEN);
                    String oust = String.valueOf(calc.calculate(a,b,c)[0]);
                    System.out.println(oust);
                    output.setText(oust);
                } catch (Exception e){
                    output.setTextFill(Color.FIREBRICK);
                    System.out.println(e);
                    output.setText("Error Occurred");
                }
            }
        });
        grid.add(confirm,0,4);

        javafx.scene.control.Button clear = new javafx.scene.control.Button("Clear Selections");
        clear.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                elev.clear();
                range.clear();
            }
        });
        grid.add(clear,1,4);

        //Setting the Scene up

        Scene scene = new Scene(grid, 640, 640);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void configs(){
        ElectricBugaloo cali = new ElectricBugaloo("calibers");
        calibers = cali.read();
        caliberNames = cali.readHashes();
    }
}

