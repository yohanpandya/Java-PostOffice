import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FrondendFD is a class that takes care of the frontend of the Package center
 * application, it allows users to interact with a interface that will help them
 * make use of a file by putting the nodes into a graph and using a Data
 * Structure called Dijkstra Graph
 *
 * @author Kevin Carroll
 *
 */
public class FrontendFD extends Application {

    public void start(Stage stage) {

        BackendBD filler = new BackendBD();

        // Labels to tell which text field box will have a location
        Label start = new Label("Start Loaction:");
        Label end = new Label("End Location:");

        // text field to enter START location
        TextField startLoc = new TextField();
        // text field to enter END location
        TextField endLoc = new TextField();
        // Text field to print out the
        TextField distance = new TextField();
        distance.setEditable(false);
        Text str = new Text();

        // File fields
        TextField file = new TextField();
        Label filePrint = new Label("No file Loaded");
        Text packageC = new Text("No Package centers found, please try again");

        // File textfield setting action when user enters a "file"
        file.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                filler.readAll(file.getText());
                List<PackageCenterDW> packages = filler.allPackages();
                filePrint.setText("File has been Loaded!\nHere is the list of Package centers:");
                StringBuilder packageStr = new StringBuilder();
                for (int i = 0; i < packages.size(); i++) {
                    packageStr.append(packages.get(i).getName()).append("\n");
                }
                packageC.setText(packageStr.toString());
            }
        });
        file.setAlignment(Pos.TOP_LEFT);

        // setting up reset button to ONLY wipe the textfields that the user types in for locations,
        // does not wipe everything
        Button reset = new Button("Reset");
        reset.setOnAction(e -> {
            str.setText("");
            startLoc.setText("");
            endLoc.setText("");
            distance.setText("");
        });

        // The clear button wipes everything clear to restart the app
        Button clear = new Button("Clear");
        clear.setOnAction(e -> {
            startLoc.setText("");
            endLoc.setText("");
            distance.setText("");
            filePrint.setText("No file Loaded");
            file.setText("");
            str.setText("");
            packageC.setText("No Package centers found, please try again");
        });

        Button calculate = new Button("Caluculate Distance:");
        calculate.setOnAction(e -> {

            PackageCenterDW startP = filler.getPackageCenterByName(startLoc.getText());
            PackageCenterDW endP = filler.getPackageCenterByName(endLoc.getText());

            distance.setText("" + filler.shortestPathCost(startP,endP));
            str.setText(filler.length(filler.shortestPath(startP,endP)));
        });

        ButtonBar holder = new ButtonBar();
        holder.getButtons().addAll(reset);

        // Exit button to quit program
        final Button quitButton = new Button("Exit");
        quitButton.setOnAction(e -> Platform.exit());
        quitButton.setDisable(false);

        // creating a button bar for the right side of teh screen
        // so the user can clear and quit the application if needed
        ButtonBar rightSide = new ButtonBar();
        rightSide.getButtons().addAll(clear,quitButton);

        // declaring grid and adding all variables to the grid
        GridPane layout = new GridPane();

        layout.add(start, 0, 0, 1, 1);
        layout.add(end, 0, 1, 1, 1);
        layout.add(startLoc, 1, 0, 1, 1);
        layout.add(endLoc, 1, 1, 1, 1);
        layout.add(calculate, 0, 2, 1, 1);
        layout.add(distance,1,2,1,1);
        layout.add(holder, 0, 3, 2, 1);

        // setting up grid layout
        layout.setHgap(20);
        layout.setVgap(15);
        layout.setAlignment(Pos.CENTER);

        // GridPane for the left hand side of the screen
        GridPane leftP = new GridPane();

        leftP.setVgap(15);
        leftP.add(file, 0, 0);
        leftP.add(filePrint, 0, 1);
        leftP.add(packageC, 0, 2);
        leftP.setAlignment(Pos.TOP_LEFT);

        // BorderPane set up
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #9DD9F3");
        borderPane.setCenter(layout);
        borderPane.setTop(rightSide);
        borderPane.setBottom(str);
        borderPane.setLeft(leftP);

        Scene scene = new Scene(borderPane, 2000, 1000);
        stage.setScene(scene);
        stage.setTitle("Shortest Path Calculator of Package Centers");
        stage.show();
    }

    /**
     * Main method to run the Application
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hello");
        Application.launch(args);
        System.out.println("Goodbye");
    }
}
