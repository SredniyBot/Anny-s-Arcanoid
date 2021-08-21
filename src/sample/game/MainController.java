package sample.game;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    private GamePanel gamePanel;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Label str1;

    @FXML
    private Label str6;

    @FXML
    private Label str2;

    @FXML
    private Label str4;

    @FXML
    private Label str3;

    @FXML
    private Label str5;

    @FXML
    private Pane gamePane;

    @FXML
    private Group gameGroup;

    @FXML
    private Arc resultArc;

    @FXML
    private Label resultTxt;

    @FXML
    private Label ballsOnField;

    @FXML
    private ImageView heart1;

    @FXML
    private ImageView heart2;

    @FXML
    private ImageView heart3;

    @FXML
    void initialize(MouseEvent event) {
        gamePanel.getHero().setPosition(event.getX());
        if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
            gamePanel.startGame();
        }
    }

    @FXML
    void initialize() {

        gamePane.getChildren().addAll(new Rectangle(0,0,0,0));
        System.out.println(gamePane.getWidth());
        gamePanel=new GamePanel(gamePane.getMinWidth(),gamePane.getMinHeight());

        gamePane.widthProperty().addListener(this::changed);
        gamePane.heightProperty().addListener(this::changed);

        gameGroup.getChildren().addAll(gamePanel);

        AnimationTimer anim = new AnimationTimer() {
            private boolean first =true;
            private long previousL;
            @Override
            public void handle(long l) {
                if(!first) {
                    int time= (int) (l-previousL);
                    gamePanel.update(time);
                }
                first=false;
                previousL=l;
            }
        };
        anim.start();
    }

    private void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
        gamePanel = new GamePanel(gamePane.getWidth(), gamePane.getHeight());
        gameGroup.getChildren().clear();
        gameGroup.getChildren().addAll(gamePanel);
    }
}
