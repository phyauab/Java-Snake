package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application implements EventHandler <KeyEvent> {

    final int SCENE_WIDTH = 300;
    final int SCENE_HEIGHT = 400;
    Snake snake;
    Food food;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Snake by Clement");

        Group root = new Group();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.BLACK); // setFill = set color

        // input
        final Box keyboardNode = new Box();
        keyboardNode.setFocusTraversable(true);
        keyboardNode.requestFocus();
        keyboardNode.setOnKeyPressed(this); // call to the EventHandler
        root.getChildren().add(keyboardNode);

        snake = new Snake(root);
        food = new Food(root);
        food.spwan(snake);
        root.getChildren().add(snake);
        root.getChildren().add(food);

        AnimationTimer animationTimer = new AnimationTimer() {

            private long lastUpdate = 0;

            @Override
            public void handle(long l) {

                //UPDATE
                if(l - lastUpdate >= 50_000_000) { // 1 second = 1 000 000 000 nanosecond

                    snake.move();
                    if(snake.getX_pos() >= SCENE_WIDTH) {
                        snake.setX_pos(0);
                    } else if (snake.getX_pos() < 0){
                        snake.setX_pos(SCENE_WIDTH-20);
                    } else if (snake.getY_pos() >= SCENE_HEIGHT){
                        snake.setY_pos(0);
                    } else if (snake.getY_pos() < 0){
                        snake.setY_pos(SCENE_HEIGHT-20);
                    }
                    lastUpdate = l;

                    if(snake.eat(food)) {
                        food.spwan(snake);
                    }
                    if(snake.hurt()){
                        JOptionPane.showMessageDialog(new JFrame(), "Score: " + snake.getLength(), "End", JOptionPane.ERROR_MESSAGE);
                        snake.reset();
                    }

                }


            }
        };
        animationTimer.start();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // This class is for event handling
    @Override
    public void handle(KeyEvent arg0) {

        if(arg0.getCode() == KeyCode.UP && snake.getDir() != 'D')
            snake.setDir('U');
        else if(arg0.getCode() == KeyCode.DOWN && snake.getDir() != 'U')
            snake.setDir('D');
        else if(arg0.getCode() == KeyCode.LEFT && snake.getDir() != 'R')
            snake.setDir('L');
        else if(arg0.getCode() == KeyCode.RIGHT && snake.getDir() != 'L') {
            snake.setDir('R');
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
