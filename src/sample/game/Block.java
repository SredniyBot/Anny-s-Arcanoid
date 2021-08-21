package sample.game;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.Random;

public class Block extends ImageView implements Intersection{

    private Image image;
    private double fieldW, fieldH,width,height,x=0,y=0;
    private Bounds bounds;
    private int xCenter, yCenter;
    private Destroyable destroyable;
    private boolean active;

    Block(double fieldW,double fieldH){
        this.fieldH=fieldH;
        this.fieldW=fieldW;
        width=fieldW/12;
        height=fieldH/10;
        setX(x);
        setY(y);
        setFitWidth(width);
        setFitHeight(height);
        setRandomImage();
    }

    Block(double width,double height,double x,double y,Destroyable destroyable){
        this.destroyable=destroyable;
        this.width=width;
        this.height=height;
        this.x=x;
        this.y=y;
        setFitWidth(width);
        setFitHeight(height);
        setX(x);
        setY(y);
        active=true;
        setRandomImage();
    }

    private void setRandomImage(){
        image= Image.values()[new Random().nextInt(9)];
        setImage(image.getImage());
        bounds=getBoundsInLocal();
        xCenter=(int)(x+width/2);
        yCenter=(int)(y+height/2);
        setVisible(true);
    }

    @Override
    public Reflection checkIntersection(Ball ball) {
        if(active) {
            if (ball.intersects(bounds)) {
                Point ballCenter = ball.getCenter();
                double xDiffTemp=Math.max(Math.abs(ballCenter.x - xCenter),0.01);
                double yDiffTemp=Math.max(Math.abs((ballCenter.y - yCenter)),0.01);
                double xDiff = width / xDiffTemp;
                double yDiff = height / yDiffTemp;
                if(Math.abs(Math.abs(xDiff) - Math.abs(yDiff))<1) return Reflection.BOTH;
                boolean b = Math.abs(xDiff) < Math.abs(yDiff);
                if (b) {
                    return Reflection.HORIZONTAL;
                }
                return Reflection.VERTICAL;
            }
        }
        return Reflection.NONE;
    }

    @Override
    public void endIntersection(Ball ball) {
        active=false;
        Shake shake = new Shake(this,50,2,2,3);
        shake.play();
        Block b=this;
        shake.setOnFinished(actionEvent -> {
            if(new Random().nextInt(10)==0) {
                destroyable.destroyObject(b);
            }else{
                destroyable.destroyAndCreate(b);
            }
        });

    }

}
