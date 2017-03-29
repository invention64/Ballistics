
import javafx.application.Application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class GUI extends Application {
    java.util.List calibers;
    java.util.List caliberNames;

    public void start(Stage primaryStage) {
        configs();
        System.out.println(calibers);
        System.out.println(caliberNames);
        primaryStage.setTitle("Ballistics Calculator");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Ballistics");
        scenetitle.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        Calculations calc = new Calculations();
        JComboBox cali = new JComboBox(caliberNames.toArray());
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

