package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Food extends Rectangle {

    private final int FOOD_WIDTH = 20;
    private final int FOOD_HEIGHT = 20;
    private Group root;

    Food(Group root){
        this.setWidth(FOOD_WIDTH);
        this.setHeight(FOOD_HEIGHT);
        this.setFill(Color.RED);
        this.root = root;
    }

    public void spwan(Snake snake){

        int max_x = 14;
        int min_x = 1;
        int max_y = 19;
        int min_y = 1;

        int randomX;
        int randomY;
        boolean isFail = false;

        while(true) {
            randomX = (int) (Math.random() * (max_x - min_x + 1) + min_x);
            randomY = (int) (Math.random() * (max_y - min_y + 1) + min_y);
            randomX *= 20;
            randomY *= 20;
            System.out.println("X: " + randomX);
            System.out.println("Y: " + randomY);
            if((randomX == snake.getX()) && (randomY == snake.getY())) {
                continue;
            }

            for (int i = 0; i < snake.getBodySize(); i++) {
                if ((randomX == snake.getBody(i).getX()) && (randomY == snake.getBody(i).getY())){
                    isFail = true;
                    break;
                }
            }

            if(!isFail) {
                this.setX(randomX);
                this.setY(randomY);
                break;
            }
            isFail = false;

        }

    }

}
