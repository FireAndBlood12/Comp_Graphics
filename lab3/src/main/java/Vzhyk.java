import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

public class Vzhyk extends Application {

    public static double X (double originalX){
        return originalX + 300;
    }
    public static double Y (double originalY){
        return originalY + 250;
    }

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        //Creating a Group object
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 600);
        Color bodyColor = Color.rgb(137, 186, 193);
        Path head = new Path(
                new MoveTo(75, 25),
                new QuadCurveTo(58, 50, 65, 63),
                new QuadCurveTo(48, 78, 90, 94),
                new QuadCurveTo(107, 106, 120, 94),
                new QuadCurveTo(162, 72, 142, 63),
                new QuadCurveTo(150, 50, 132, 25)
        );
        head.setStroke(Color.BLACK);
        head.setFill(bodyColor);

        Path eyes = new Path(
                new MoveTo(105, 22),
                new QuadCurveTo(96, 5, 75, 25),
                new QuadCurveTo(54, 80, 105, 75),
                new QuadCurveTo(158, 76, 132, 25),
                new QuadCurveTo(120, 5, 105, 22)
        );
        eyes.setStroke(Color.BLACK);
        eyes.setFill(Color.YELLOW);
        Ellipse nose = new Ellipse(104,75,11,7);
        Ellipse nose_1 = new Ellipse(104,72,5,2);
        nose_1.setFill(Color.WHITE);
        Ellipse eye_1 = new Ellipse(100,65,3,4);
        Ellipse eye_2 = new Ellipse(110,65,3,4);

        Path lip_1 = new Path(
                new MoveTo(70, 73),
                new QuadCurveTo(108, 104, 136, 73)
        );
        lip_1.setStroke(Color.BLACK);
        lip_1.setFill(bodyColor);
        lip_1.setStrokeWidth(2);
        Path lip_2 = new Path(
                new MoveTo(85, 84),
                new QuadCurveTo(107, 106, 120, 85)
        );
        lip_2.setFill(Color.rgb(77, 17, 25));

        Ellipse tongue = new Ellipse(105,94,5,2);
        tongue.setFill(Color.rgb(220, 167, 163));

        Path antenna_1 = new Path(
                new MoveTo(87, 15),
                new QuadCurveTo(87, 10, 81, 5)
        );
        antenna_1.setStroke(Color.BLACK);
        antenna_1.setStrokeWidth(2);
        Path antenna_2 = new Path(
                new MoveTo(93, 14),
                new QuadCurveTo(95, 10, 94, 6)
        );
        antenna_2.setStroke(Color.BLACK);
        antenna_2.setStrokeWidth(2);
        Path antenna_3 = new Path(
                new MoveTo(118, 14),
                new QuadCurveTo(120, 10, 122, 6)
        );
        antenna_3.setStroke(Color.BLACK);
        antenna_3.setStrokeWidth(2);
        Path antenna_4 = new Path(
                new MoveTo(125, 16),
                new QuadCurveTo(123, 9, 130, 5)
        );
        antenna_4.setStroke(Color.BLACK);
        antenna_4.setStrokeWidth(2);
        Path neck = new Path(
                new MoveTo(90, 94),
                new LineTo(88, 102),
                new QuadCurveTo(115, 112, 118, 96)
        );
        neck.setFill(Color.rgb(254, 54, 57));
        neck.setStroke(Color.BLACK);
        Path t_shirt = new Path(
                new MoveTo(90, 94),
                new LineTo(78, 109),
                new LineTo(86, 114),
                new MoveTo(90, 106),
                new QuadCurveTo(84, 112, 82, 130),
                new QuadCurveTo(106, 145, 122, 129),
                new QuadCurveTo(124, 115, 118, 106),
                new MoveTo(121, 112),
                new LineTo(128, 108),
                new LineTo(118, 96)

        );
        Path right_hand = new Path(
                new MoveTo(78, 109),
                new QuadCurveTo(61, 136, 76, 144),
                new QuadCurveTo(84, 140, 76, 134),
                new QuadCurveTo(82, 138, 78, 130),
                new QuadCurveTo(76, 128, 84, 114)
        );
        Path right_leg = new Path(
                new MoveTo(82, 130),
                new QuadCurveTo(82, 146, 92, 160),
                new CubicCurveTo(70, 160, 74, 168, 98, 163),
                new QuadCurveTo(94, 144, 98, 146),
                new LineTo(106, 146)
        );
        Path left_leg = new Path(
                new MoveTo(106, 146),
                new QuadCurveTo(107, 146, 106, 163),
                new CubicCurveTo(125, 174, 125, 166, 112, 160),
                new QuadCurveTo(118, 148, 122, 129)
        );

        Path left_hand = new Path(
                new MoveTo(126, 110),
                new QuadCurveTo(140, 136, 124, 144),
                new QuadCurveTo(120, 140, 124, 136),
                new QuadCurveTo(116, 140, 126, 130),
                new QuadCurveTo(126, 130, 120, 110)
        );

        right_hand.setFill(bodyColor);
        right_leg.setFill(bodyColor);
        left_leg.setFill(bodyColor);
        left_hand.setFill(bodyColor);
        Ellipse tshirt_back = new Ellipse(105,105,20,10);
        tshirt_back.setFill(Color.rgb(254, 54, 57));
        Ellipse legs_back = new Ellipse(100,138,18,8);
        legs_back.setFill(bodyColor);
        t_shirt.setFill(Color.rgb(254, 54, 57));
        t_shirt.setStroke(Color.BLACK);
        root.getChildren().add(legs_back);
        root.getChildren().add(tshirt_back);
        root.getChildren().add(left_hand);
        root.getChildren().add(left_leg);
        root.getChildren().add(right_leg);
        root.getChildren().add(right_hand);
        root.getChildren().add(t_shirt);
        root.getChildren().add(neck);
        root.getChildren().add(head);
        root.getChildren().add(lip_2);
        root.getChildren().add(lip_1);
        root.getChildren().add(antenna_1);
        root.getChildren().add(antenna_2);
        root.getChildren().add(antenna_3);
        root.getChildren().add(antenna_4);

        root.getChildren().add(tongue);
        root.getChildren().add(eyes);
        root.getChildren().add(nose);
        root.getChildren().add(nose_1);
        root.getChildren().add(eye_1);
        root.getChildren().add(eye_2);


        Path movement = new Path(
                new MoveTo(120, 120),
                new CubicCurveTo(270, 10, 470, 500, 880, 480)
        );

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(5000));
        pathTransition.setPath(movement);
        pathTransition.setNode(root);
        pathTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(2500), root);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(3);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(5000), root);
        scaleTransition.setToX(0.4);
        scaleTransition.setToY(0.4);
        scaleTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition,
                scaleTransition,
                pathTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Vzhyk");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
