package sample.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Ball extends Rectangle implements Buffed{



    private double x,y,fieldW,fieldH, size,tempSize,bigSize;

    private double tempSpeed,speed;
    private double slowerSpeed;
    private double fasterSpeed;
    private double vX,vY;
    private int angle;
    private Hero hero;
    private boolean active;
    private int lastTime;

    Ball(double fieldW, double fieldH,Hero hero){
        active=true;
        this.hero=hero;
        this.fieldH=fieldH;
        this.fieldW=fieldW;
        size =fieldH/50;
        tempSize=size;
        fasterSpeed=size/500000000;
        slowerSpeed = size /500000000;
        speed = size /200000000;
        tempSpeed=speed;
        bigSize=fieldW/30;
        setWidth(tempSize);
        setHeight(tempSize);
        setArcHeight(tempSize);
        setArcWidth(tempSize);
        setVisible(true);
        setFill(Color.rgb(237,211,152));
        angle=new Random().nextInt(61)-30;
        vY=-tempSpeed *Math.cos(Math.toRadians(angle));
        vX= tempSpeed *Math.sin(Math.toRadians(angle));
    }

    public void update(ArrayList<Intersection> figures,int time){
        lastTime=time;
        if(active) {
            x += vX * time;
            y += vY * time;
            if (x <= 0) {
                x = 0;
                vX = -vX;
                littleChangeAngle();
            } else if (x >= fieldW - tempSize) {
                x = fieldW - tempSize;
                vX = -vX;
                littleChangeAngle();
            }
            if (y <= 0) {
                y = 0;
                vY = -vY;
                littleChangeAngle();
            } else if (y >= fieldH - tempSize) {
                deactivate();
            }
            setX(x);
            setY(y);
            for(int i=figures.size()-1;i>=0;i--){
                answerCollision(figures.get(i),time);
            }
        }
    }

    public void littleChangeAngle(){
        angle= (int) Math.toDegrees(Math.asin((vX/ tempSpeed)))+new Random().nextInt(11)-5;
        vX= tempSpeed *Math.sin(Math.toRadians(angle));
        if(vY>0){
            vY= tempSpeed *Math.cos(Math.toRadians(angle));
        }else{
            vY=-tempSpeed *Math.cos(Math.toRadians(angle));
        }
    }

    public double getSize() {
        return tempSize;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate(){
        active=false;
        vX=0;
        vY=0;
        setVisible(false);
    }

    public double getXBall() {
        return x;
    }

    public void setXBall(double x) {
        this.x = x;
        setX(x);
    }

    public double getYBall() {
        return y;
    }

    public void setYBall(double y) {
        this.y = y;
        setY(y);
    }

    public Point getCenter(){
        return new Point((int)(x+ tempSize /2),(int)(y+ tempSize /2));
    }

    private void answerCollision(Intersection intersection,int time){
        time=time/50;
        Reflection reflection=intersection.checkIntersection(this);
        switch (reflection){
            case NONE:return;
            case HORIZONTAL:
                vX=-vX;
                break;
            case VERTICAL:
                vY=-vY;
                break;
            case BOTH:
                vY=-vY;
                vX=-vX;
                littleChangeAngle();
                littleChangeAngle();
                break;
        }
        while (intersection.checkIntersection(this)!=Reflection.NONE){
            y+=vY*time/3;
            x+=vX*time/3;
            setX(x);
            setY(y);
        }
        intersection.endIntersection(this);
        littleChangeAngle();
    }

    @Override
    public void checkBuff() {
        switch (Buff.getBallSpeed()){
            case BIG:
                tempSpeed=fasterSpeed;
                break;
            case SMALL:tempSpeed=slowerSpeed;
                break;
            case MIDDLE:tempSpeed=speed;
                break;
        }
        if(Buff.isBigBall()){
            tempSize=bigSize;
        }else{
            tempSize=size;
        }
        setWidth(tempSize);
        setHeight(tempSize);
    }

}
