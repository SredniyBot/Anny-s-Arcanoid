package sample.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Hero extends Rectangle implements Intersection,Buffed {

    private double x,y,fieldW,fieldH,width,height,bigWidth,smallWidth,tempWidth;
    private boolean started=false;

    private StartBall startBall;

    Hero(double fieldW,double fieldH){
        this.fieldH=fieldH;
        this.fieldW=fieldW;
        x=-1000;
        y = (fieldH * 9) / 10;
        height=fieldH/60;
        width=fieldW/9;
        smallWidth=fieldW/12;
        bigWidth=fieldW/6;
        tempWidth=width;
        setWidth(tempWidth);
        setHeight(height);
        setArcHeight(20);
        setArcWidth(10);
        setX(x);
        setY(y);
        setVisible(true);
        setFill(Color.rgb(164,237,165));
        startBall=new StartBall(fieldW,fieldH,this);
    }

    public void setPosition(double position){
        if(position<=0){
            x=0;
        }else {
            x = Math.min(position, fieldW - tempWidth);
        }
        setX(x);
        if(!started){
            startBall.setXBall(x+tempWidth/2-startBall.getSize()/2);
        }
    }

    public StartBall getStartBall() {
        return startBall;
    }

    public void startGame(){
        started=true;
    }

    public void restart(){
        startBall=new StartBall(fieldW,fieldH,this);
        started=false;
    }

    @Override
    public Reflection checkIntersection(Ball ball) {
        if(ball.intersects(getBoundsInLocal())){
            return Reflection.VERTICAL;
        }
        return Reflection.NONE;
    }

    @Override
    public void endIntersection(Ball ball) {
        return;
    }

    @Override
    public void checkBuff() {
        switch (Buff.getHeroSize()){
            case MIDDLE:
                tempWidth=width;
                break;
            case SMALL:
                tempWidth=smallWidth;
                break;
            case BIG:
                tempWidth=bigWidth;
                break;
        }
        setWidth(tempWidth);
    }

    private class StartBall extends Ball {
        StartBall(double fieldW, double fieldH,Hero hero) {
            super(fieldW, fieldH,hero);
            this.setXBall(x+tempWidth/2-getSize()/2);
            this.setYBall(y-getSize()-10);
        }
    }

}
