package sample.game;


import java.util.ArrayList;
import java.util.Random;


public class BlockCollection extends ObjectCollection<Block> implements Destroyable {


    private static ArrayList<Intersection> figures;

    BlockCollection(double width, double height){
        super(width,height);
        figures=new ArrayList<>();
        addAll(getBlocks(15,6));
    }


    public void update(int time) {

    }


    private ArrayList<Block> getBlocks(int cols,int rows) {
        ArrayList<Block> blocks=new ArrayList<>();
        int padding=2;
        double width=(getFieldW()-cols*padding)/(cols);
        double height=(getFieldH()-rows*padding)/rows/2;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                blocks.add(new Block(width, height, width*j+padding*j, height*i+padding*i,this));
            }
        }
        return blocks;
    }

    private void addAll(ArrayList<Block> blocks){
        getObjects().addAll(blocks);
        getChildren().addAll(blocks);
        figures.addAll(blocks);
    }

    private void remove(Block block){
        getObjects().remove(block);
        getChildren().remove(block);
        figures.remove(block);
    }

    public ArrayList<Intersection> getFigures() {
        return figures;
    }

    @Override
    public void destroyObject(Block b) {
        remove(b);
    }

    @Override
    public void destroyAndCreate(Block b) {
        remove(b);
        switch (new Random().nextInt(6)){
            case 0:
                Buff.setBallSpeed(ValueSize.SMALL);
                System.out.println("small speed");
                break;
            case 1:
                Buff.setBallSpeed(ValueSize.BIG);
                System.out.println("big speed");
                break;
            case 2:
                Buff.setBigBall(true);
                System.out.println("big ball");
                break;
            case 3:
                Buff.setHeroSize(ValueSize.SMALL);
                System.out.println("small hero");
                break;
            case 4:
                Buff.setHeroSize(ValueSize.BIG);
                System.out.println("big hero");
                break;
            case 5:
                Buff.setDUPLICATION(true);
                System.out.println("duplication");
                break;
        }
    }

}
