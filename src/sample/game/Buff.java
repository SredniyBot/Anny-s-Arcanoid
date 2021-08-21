package sample.game;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Buff {

    private static ValueSize ballSpeed=ValueSize.MIDDLE;
    private static ValueSize heroSize=ValueSize.MIDDLE;
    private static boolean DUPLICATION=false;
    private static boolean bigBall=false;
    private static Timeline heroSizeTimer=new Timeline();
    private static Timeline ballSizeTimer=new Timeline();
    private static Timeline ballSpeedTimer=new Timeline();
    private static int time=1000;

    public static ValueSize getBallSpeed() {
        return ballSpeed;
    }

    public static void setBallSpeed(ValueSize ballSpeed) {
        try {
            ballSpeedTimer.stop();
        }catch (Exception e){
            System.out.println("timer canceled");
        }
        switch (ballSpeed){
            case MIDDLE:
                Buff.ballSpeed=ValueSize.MIDDLE;
                break;
            case SMALL:
                Buff.ballSpeed=ValueSize.SMALL;
                ballSpeedTimer.setDelay(Duration.millis(time));
                ballSpeedTimer.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Buff.ballSpeed=ValueSize.MIDDLE;
                    }
                });
                ballSpeedTimer.playFromStart();
                break;
            case BIG:
                Buff.ballSpeed=ValueSize.BIG;
                ballSpeedTimer.setDelay(Duration.millis(time));
                ballSpeedTimer.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Buff.ballSpeed=ValueSize.MIDDLE;
                    }
                });
                ballSpeedTimer.playFromStart();
                break;
        }
    }

    public static ValueSize getHeroSize() {
        return heroSize;
    }

    public static void setHeroSize(ValueSize heroSize) {
        try {
            heroSizeTimer.stop();
        }catch (Exception e){
            System.out.println("timer canceled");
        }
        switch (heroSize){
            case MIDDLE:
                Buff.heroSize=ValueSize.MIDDLE;
                break;
            case SMALL:
                Buff.heroSize=ValueSize.SMALL;
                heroSizeTimer.setDelay(Duration.millis(time));
                heroSizeTimer.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Buff.heroSize=ValueSize.MIDDLE;
                    }
                });
                heroSizeTimer.playFromStart();
                break;
            case BIG:
                Buff.heroSize=ValueSize.BIG;
                heroSizeTimer.setDelay(Duration.millis(time));
                heroSizeTimer.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Buff.heroSize=ValueSize.MIDDLE;
                    }
                });
                heroSizeTimer.playFromStart();
                break;
        }
    }

    public static boolean isDUPLICATION() {
        return DUPLICATION;
    }

    public static void setDUPLICATION(boolean DUPLICATION) {
        Buff.DUPLICATION = DUPLICATION;
    }

    public static boolean isBigBall() {
        return bigBall;
    }

    public static void setBigBall(boolean bigBall) {
        try {
            ballSizeTimer.stop();
        }catch (Exception e){
            System.out.println("timer canceled");
        }
        if(bigBall){
            Buff.bigBall=true;
            ballSizeTimer.setDelay(Duration.millis(time));
            ballSizeTimer.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Buff.bigBall=false;
                }
            });
            ballSizeTimer.playFromStart();
        }else {
            Buff.bigBall=false;
        }
    }
}
