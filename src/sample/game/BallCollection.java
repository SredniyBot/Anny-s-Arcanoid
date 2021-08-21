package sample.game;

import java.util.ArrayList;

public class BallCollection extends ObjectCollection<Ball> {

    private int ballCount;
    private BlockCollection blockCollection;
    private ArrayList<Buffed> buffed;
    BallCollection(double width, double height){
        super(width,height);
        buffed=new ArrayList<>();
        blockCollection=new BlockCollection(width,height);
        getChildren().addAll(blockCollection);
    }

    public void update(int time){
        for (int i=buffed.size()-1;i>=0;i--){
            buffed.get(i).checkBuff();
        }
        for (int i=getObjects().size()-1;i>=0;i--) {
            Ball ball=getObjects().get(i);
            if (ball.isActive()) {
                getObjects().get(i).update(blockCollection.getFigures(),time);
            } else {
                ballCount--;
                getObjects().remove(ball);
                this.getChildren().remove(ball);
            }
        }
    }

    public void startGame(Ball ball){
        ballCount=1;
        buffed.add(ball);
        getObjects().add(ball);
        this.getChildren().addAll(ball);
    }

    public int getBallCount() {
        return ballCount;
    }

    public void addIntersection(Intersection intersection){
        blockCollection.getFigures().add(intersection);
    }

    public void addBuff(Buffed buff){
        buffed.add(buff);
    }

    public void addAllIntersections(ArrayList<Intersection> intersections){
        blockCollection.getFigures().addAll(intersections);
    }

}
