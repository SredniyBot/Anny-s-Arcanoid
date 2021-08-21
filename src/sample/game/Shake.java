package sample.game;


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {

    private TranslateTransition tt;
    private Node node;

    public Shake(Node node,int duration, int x, int y, int cycle){
        this.node=node;
        tt=new TranslateTransition(Duration.millis(duration),node);
        tt.setFromY(-y);
        tt.setByY(y);
        tt.setFromX(-x);
        tt.setByX(x);
        tt.setCycleCount(cycle);
        tt.setAutoReverse(true);
    }

    public void play(){
        tt.playFromStart();
    }

    public void setOnFinished(EventHandler<ActionEvent> eventHandler){
        tt.setOnFinished(eventHandler);
    }

}
