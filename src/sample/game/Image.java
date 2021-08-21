package sample.game;

import java.io.File;

public enum Image {

    STAR(0),
    MONSTER(1),
    WATERMELON(2),
    BONE(3),
    CAT(4),
    ELEPHANT(5),
    CROWN(6),
    GLASS(7),
    CARROT(8);

    private int index;
    private String URL;
    private javafx.scene.image.Image image;

    Image(int index){
        this.index=index;
        System.out.println();
        this.URL=
                File.separator + "assets"+ File.separator + "arcanoidBlocks"+ File.separator +index+".png";
        image = new javafx.scene.image.Image(getClass().getResourceAsStream(URL));

    }

    public javafx.scene.image.Image getImage() {
        return image;
    }

    public String getURL() {
        return URL;
    }

    public int getIndex() {
        return index;
    }

}
