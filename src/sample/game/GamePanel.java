package sample.game;

import javafx.scene.Group;


public class GamePanel extends Group {

    private double width,height;

    private Hero hero;
    private boolean gameIsStarted=false;
    private BallCollection ballCollection;


    GamePanel(double width,double height){
        this.width=width;
        this.height=height;
        hero=new Hero(width,height);
        ballCollection=new BallCollection(width,height);
        ballCollection.addBuff(hero);
        ballCollection.addIntersection(hero);
        this.getChildren().addAll(hero);
        this.getChildren().addAll(hero.getStartBall());
        this.getChildren().addAll(ballCollection);

    }

    public void update(int time){
        if(ballCollection.getBallCount()==0){
            if(gameIsStarted)
                restart();
        }else {
            ballCollection.update(time);
        }
    }

    public Hero getHero() {
        return hero;
    }

    private void restart(){
        gameIsStarted=false;
        hero.restart();
        this.getChildren().addAll(hero.getStartBall());

    }

    public void startGame(){
        if(!gameIsStarted) {
            gameIsStarted = true;
            ballCollection.startGame(hero.getStartBall());
            hero.startGame();
        }
    }

}
