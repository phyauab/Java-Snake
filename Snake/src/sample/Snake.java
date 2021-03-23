package sample;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Snake extends Rectangle {

    /* size of each cell = 20 */
    private final int HEAD_WIDTH = 20;
    private final int HEAD_HEIGHT = 20;
    private final int SPEED = 20;

    /* moving parts of snake */
    private int x_pos;
    private int y_pos;
    private char dir;
    private Group root;
    private ArrayList<Rectangle> body;
    private int length;

    Snake(Group root){

        x_pos = generateRandom(14,1) * 20;
        y_pos = generateRandom(19,1) * 20;
        int randomD = generateRandom(4,1);
        length = 0;

        this.setWidth(HEAD_WIDTH);
        this.setHeight(HEAD_HEIGHT);
        this.setX(x_pos);
        this.setY(y_pos);
        this.setFill(Color.GREEN);
        this.root = root;
        this.body = new ArrayList<Rectangle>();

        /* Snake is assigned a direction randomly */
        switch(randomD){
            case 1:
                this.dir = 'U';
                break;
            case 2:
                this.dir = 'D';
                break;
            case 3:
                this.dir = 'L';
                break;
            case 4:
                this.dir = 'R';
                break;
        }

    }

    private int generateRandom(int max, int min){
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public void reset(){
        x_pos = generateRandom(14,1) * 20;
        y_pos = generateRandom(19,1) * 20;
        int randomD = generateRandom(4,1);
        switch(randomD){
            case 1:
                this.dir = 'U';
                break;
            case 2:
                this.dir = 'D';
                break;
            case 3:
                this.dir = 'L';
                break;
            case 4:
                this.dir = 'R';
                break;
        }
        length = 0;

        for(int i=0; i<getBodySize(); i++){
            root.getChildren().remove(body.get(i));
            body.remove(i);
        }

        body.clear();
    }

    /* Snake moves constantly and has a direction */
    public void move(){

        // update body, if any
        for(int i=body.size()-1; i>0; --i){
            body.get(i).setX(body.get(i-1).getX());
            body.get(i).setY(body.get(i-1).getY());
        }

        if(body.size() != 0) {
            body.get(0).setX(this.x_pos);
            body.get(0).setY(this.y_pos);
        }

        // reponds to input
        if(dir == 'U') {
            y_pos -= SPEED;
            this.setY(y_pos);
        } else if (dir == 'D'){
            y_pos += SPEED;
            this.setY(y_pos);
        } else if (dir == 'L'){
            x_pos -= SPEED;
            this.setX(x_pos);
        } else if (dir == 'R'){
            x_pos += SPEED;
            this.setX(x_pos);
        }

    }

    public void setDir(char dir){
        this.dir = dir;
    }

    /* Snake eats when collides with food */
    public boolean eat(Food food){

        if(this.contains(food.getX(), food.getY())){
            this.grow();
            length++;
            return true;
        }

        return false;
    }

    /* Check if the snake eats itself */
    public boolean hurt(){
        for(int i=0; i<body.size(); i++){
            if(this.contains(body.get(i).getX(), body.get(i).getY()))
                return true;
        }
        return false;
    }

    /* Create one block of body */
    public void grow(){

        int index = body.size();
        body.add(new Rectangle());
        body.get(index).setWidth(HEAD_WIDTH);
        body.get(index).setHeight(HEAD_HEIGHT);
        body.get(index).setFill(Color.GREEN);
        body.get(index).setX(500);
        body.get(index).setY(500);
        root.getChildren().add(body.get(index));

    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos){
        this.x_pos = x_pos;
        this.setX(x_pos);
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos){
        this.y_pos = y_pos;
        this.setY(y_pos);
    }

    public Rectangle getBody(int index){
        return body.get(index);
    }

    public int getBodySize(){
        return body.size();
    }

    public char getDir(){
        return dir;
    }

    public int getLength(){ return length; };
}
