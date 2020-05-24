package sample.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;

    public Shake(Node node){ // construktor; Node can be everything: label, button, image, ets that is going to be shaked
        tt = new TranslateTransition(Duration.millis(70), node);
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);// Node comes back to it's initial position
    }

    // method that shakes
    public void PlayAnim(){
        tt.playFromStart();
    }
}
