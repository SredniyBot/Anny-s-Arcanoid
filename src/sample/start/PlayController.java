package sample.start;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.game.Shake;

public class PlayController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button playButton;

    @FXML
    void initialize() {

        playButton.setOnAction(e->{
            Shake s = new Shake(playButton,102,0,10,4);
            playButton.setDisable(true);
            s.setOnFinished(event -> {
                playButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("app2.fxml"));
                try {
                    loader.load();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Parent root=loader.getRoot();
                Stage stage=new Stage();
                stage.setMinHeight(600);
                stage.setMinWidth(1100);
                stage.setScene(new Scene(root));
                stage.show();
            });
            s.play();
        });


    }

}
