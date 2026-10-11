package com.example.vytah;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Elevator extends Canvas {
    private static final double FLOOR_HEIGHT = 80;
    private static final double STARTPOS = 600;
    private static final double SPEED = 30;

    private GraphicsContext gc;
    private Timeline t;
    private int currnetFloor = 1;
    private int targetFloor = 1;
    private boolean moving = false;
    private int pending = 0;


    public Elevator(){
        super(150,175);
        this.gc = this.getGraphicsContext2D();
        setLayoutX(150);
        setLayoutY(600);
        vykresli(Color.GREY);
    }

    public void vykresli(Color grey){
        gc.clearRect(0,0,getWidth(),getHeight());
        gc.setFill(grey);
        gc.fillRect(0,0,getWidth(),getHeight());

    }

    private double floorY(int floor){
        return STARTPOS - (floor - 1) * FLOOR_HEIGHT;
    }

    public void requestFloor(int floor) {
        if (moving) {
            if (floor != targetFloor) {
                pending = floor;
            }
        } else if (floor != currnetFloor) {
            moveTo(floor);
        }
    }

    private void moveTo(int floor){
        moving = true;
        targetFloor = floor;

        double targetY = floorY(floor);
        double dist = Math.abs(targetY - getLayoutY());

        t = new Timeline(new KeyFrame(Duration.seconds(dist / SPEED), new KeyValue(this.layoutYProperty(), targetY)));
        t.setOnFinished(e -> {currnetFloor = floor;
            if (pending == 0) {
                moving = false;
            }else {
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e2 -> {int next = pending;
                    pending = 0;
                    if (next == currnetFloor) {
                        moving = false;
                    }else {
                        moveTo(next);
                    }



                });
                pause.play();
            }

        });
        t.play();


    }

    public void options(KeyCode key){
        switch (key){
            case DIGIT1,NUMPAD1 -> requestFloor(1);
            case DIGIT2,NUMPAD2 -> requestFloor(2);
            case DIGIT3,NUMPAD3 -> requestFloor(3);
            case DIGIT4,NUMPAD4 -> requestFloor(4);
            case DIGIT5,NUMPAD5 -> requestFloor(5);
            case DIGIT6,NUMPAD6 -> requestFloor(6);
            case DIGIT7,NUMPAD7 -> requestFloor(7);
            case DIGIT8,NUMPAD8 -> requestFloor(8);

        }
    }

}
