/*
####################################
#James Gottshall & Sean Fitzpatrick#
#Done for Java project
#Made Possible by the Following Sponsors:
#

#Header written 3/31/2017
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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


        Label WS = new Label("Wind Speed:");
        grid.add(WS,0,4);
        TextField windSpeed = new TextField();
        elev.setPromptText("M/s");
        grid.add(windSpeed,1,4);


        Label WA = new Label("Wind Angle:");
        grid.add(WA,0,5 );
        TextField windAngle = new TextField();
        elev.setPromptText("Degrees");
        grid.add(windAngle,1,5);


        Label CA = new Label("Compass Angle:");
        grid.add(CA,0,6);
        TextField compassAngle = new TextField();
        elev.setPromptText("Degrees");
        grid.add(compassAngle,1,6);


        Label outputTime = new Label();
        grid.add(outputTime,0,8);
        Label outputAng = new Label();
        grid.add(outputAng,1,8);
        Label outputWindDiff = new Label();
        grid.add(outputTime,0,9);
        Label outputWindage = new Label();
        grid.add(outputAng,1,9);



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
                    double outputs[] = calc.calculate(velocity,b,c,Double.parseDouble(windSpeed.getText()),Double.parseDouble(windAngle.getText()),Double.parseDouble(compassAngle.getText()));
                    String T = String.valueOf(shave.format(outputs[0]));
                    String ANG = String.valueOf(shave.format(outputs[1]));
                    String WINDDIFF = String.valueOf(shave.format(outputs[2]));
                    String WINDAGE = String.valueOf(shave.format(outputs[3]));
                    System.out.println(T);
                    outputTime.setText("Time to Target= "+T+"s");
                    outputAng.setText("Firing Angle= "+ANG);
                    outputWindDiff.setText("Flight Angle= "+WINDDIFF);
                    outputWindage.setText("Point of Impact Shift= "+WINDAGE);
                } catch (IllegalArgumentException e){
                    outputTime.setTextFill(Color.FIREBRICK);
                    outputAng.setText("");
                    outputWindDiff.setText("");
                    outputWindage.setText("");
                    System.out.println(e);
                    outputTime.setText("Null Values Detected");
                }
                catch (Exception e){
                    outputTime.setTextFill(Color.FIREBRICK);
                    outputAng.setText("");
                    outputWindDiff.setText("");
                    outputWindage.setText("");
                    System.out.println(e);
                    outputTime.setText("Error Occurred");
                }
            }
        });
        grid.add(confirm,0,7);

        javafx.scene.control.Button clear = new javafx.scene.control.Button("Clear Selections");
        clear.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                elev.clear();
                range.clear();
                cali.setValue("");
                outputAng.setText("");
                outputTime.setText("");
                outputWindDiff.setText("");
                outputWindage.setText("");
            }
        });
        grid.add(clear,1,7);

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

