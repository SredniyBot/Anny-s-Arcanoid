package sample.game;

import javafx.scene.Group;

import java.util.ArrayList;

public abstract class ObjectCollection<T> extends Group {

    private ArrayList<T> objects;

    private double fieldW,fieldH;

    ObjectCollection(double fieldW,double fieldH){
        objects=new ArrayList<>();
        this.fieldW=fieldW;
        this.fieldH=fieldH;
    }

    public double getFieldW() {
        return fieldW;
    }

    public double getFieldH() {
        return fieldH;
    }

    public ArrayList<T> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<T> objects) {
        this.objects = objects;
    }

    public void add(T t){
        objects.add(t);
    }

    public abstract void update(int time);

}
