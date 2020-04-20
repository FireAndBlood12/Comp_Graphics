package lab1;
 
import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.shape.*;

import static javafx.scene.paint.Color.rgb;

public class Figure extends Application {
       
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int rectangle_width = 225;
        int rectangle_height = 60;
        int ellipse_radiusX = 25;
        int ellipse_radiusY = 100;
        
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 500);
        scene.setFill(rgb(218, 112, 214));

        //create green rectangle
        Rectangle r = new Rectangle(400,50, rectangle_width, rectangle_height);
        r.setFill(Color.GREEN);

        //create red rectangle
        Rectangle r1 = new Rectangle(400, 190, rectangle_width, rectangle_height);
        r1.setFill(Color.RED);

        //create blue ellipse
        Ellipse ellipse = new Ellipse(425,150, ellipse_radiusX, ellipse_radiusY);
        ellipse.setFill(Color.BLUE);

        //create yellow ellipse
        Ellipse ellipse2 = new Ellipse(600,150, ellipse_radiusX, ellipse_radiusY);
        ellipse2.setFill(Color.YELLOW);

        Rectangle r3 = new Rectangle(400, 50, rectangle_width / 2, rectangle_height);
        r3.setFill(Color.GREEN);

        root.getChildren().add(r);
        root.getChildren().add(ellipse2);
        root.getChildren().add(r1);
        root.getChildren().add(ellipse);
        root.getChildren().add(r3);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
