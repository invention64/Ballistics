/*
####################################
#James Gottshall & Sean Fitzpatrick#
#Done for Java project
#Made Possible by the Following Sponsors:
#
#Header written 3/31/2017
 */
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

public class GUI extends Application {
    java.util.List calibers;
    java.util.List caliberNames;
    double velocity;
    NumberFormat shave = new DecimalFormat("#.###");
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
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(15, 15, 15, 15));
        Text scenetitle = new Text("Ballistics");
        scenetitle.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 30));
        grid.add(scenetitle, 0, 0, 2, 1);

        //Adding all of the elements

        Label lcali = new Label("Caliber:");
        grid.add(lcali,0,1);
        ObservableList<String> calis = FXCollections.observableArrayList(caliberNames);
        ComboBox cali = new ComboBox(calis);
        cali.setPromptText("Meters/Second");
        cali.setEditable(true);
        grid.add(cali, 1, 1);
        Label lrange = new Label("Range:");
        grid.add(lrange,0,2);
        TextField range = new TextField();
        range.setPromptText("Meters");
        grid.add(range,1,2);
        Label lelev = new Label("Elevation:");
        grid.add(lelev,0,3);
        TextField elev = new TextField();
        elev.setPromptText("Meters");
        grid.add(elev,1,3);
        Label outputTime = new Label();
        grid.add(outputTime,0,5);
        Label outputAng = new Label();
        grid.add(outputAng,1,5);


        //Constructing the Buttons

        javafx.scene.control.Button confirm = new javafx.scene.control.Button("Confirm Selection");
        confirm.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (cali.getValue() == null || range.getText() == null || elev.getText() == null){
                        throw  new IllegalArgumentException();
                    }
                    if (caliberNames.contains(cali.getValue())) {
                        Object vel = calibers.toArray()[caliberNames.indexOf(cali.getValue())];
                        velocity = Double.parseDouble(vel.toString());
                    }else{
                        velocity = Double.parseDouble(cali.getValue().toString());
                    }
                    System.out.println(velocity);
                    double b = Double.parseDouble(range.getText());
                    double c = Double.parseDouble(elev.getText());
                    System.out.println(velocity+"\n"+b+"\n"+c);
                    outputTime.setTextFill(Color.GREEN);
                    outputAng.setTextFill(Color.GREEN);
                    String T = String.valueOf(shave.format(calc.calculate(velocity,b,c)[0]));
                    String ANG = String.valueOf(shave.format(calc.calculate(velocity,b,c)[1]));
                    System.out.println(T);
                    outputTime.setText("Time to Target= "+T+"s");
                    outputAng.setText("Firing Angle= "+ANG);
                } catch (IllegalArgumentException e){
                    outputTime.setTextFill(Color.FIREBRICK);
                    outputAng.setText("");
                    System.out.println(e);
                    outputTime.setText("Null Values Detected");
                }
                catch (Exception e){
                    outputTime.setTextFill(Color.FIREBRICK);
                    outputAng.setText("");
                    System.out.println(e);
                    outputTime.setText("Error Occurred");
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
                cali.setValue("");
                outputAng.setText("");
                outputTime.setText("");
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
        ElectricBugaloo cali = new ElectricBugaloo("Calibers");
        calibers = cali.read();
        caliberNames = cali.readHashes();
    }
}
