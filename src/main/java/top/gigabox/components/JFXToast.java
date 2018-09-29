/**
 * MIT License
 *
 * Copyright (c) 2018 Vincenzo Palazzo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package top.gigabox.components;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */

public class JFXToast {

    /**
     * Set a display time of long toast
     */
    public static final int LONG = 3500;
    /**
     * Set a display time of short toast
     */
    public static final int SHORT = 500;
    /**
     * Set the toast in the central high position of the reference stage
     */
    public static final int TOP = 1;
    /**
     * Set the toast in the low central position of the reference stage
     */
    public static final int BUTTON = 0;

    private JFXToast(){}

    /**
     * Create toast with maximum customization
     * @param ownerStage
     * @param toastMsg
     * @param toastDelay
     * @param position
     */
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay, int position) {

        int fadeInDelay = 500;
        int fadeOutDelay = 500;

        Stage toastStage = new Stage();
        toastStage.initOwner(ownerStage);
        toastStage.setResizable(false);
        toastStage.initStyle(StageStyle.TRANSPARENT);

        Text text = new Text(toastMsg);
        text.setFont(Font.font("Roboto", 15));
        text.setFill(Color.LIGHTGRAY);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-radius: 70; -fx-background-color: rgba(0, 0, 0, 0.8)");
        root.setOpacity(80);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        toastStage.setScene(scene);
        toastStage.setWidth(toastMsg.length() * 10);
        toastStage.setHeight(20);

        //set position toast
        if (position == BUTTON) {
            toastStage.setY(ownerStage.getY() + (ownerStage.getHeight()));
            toastStage.setX(ownerStage.getX() + ((ownerStage.getWidth() / 2) - (toastStage.getWidth() / 2)));
        } else {
            toastStage.setY(ownerStage.getY() + 10);
            toastStage.setX(ownerStage.getX() + ((ownerStage.getWidth() / 2) - (toastStage.getWidth() / 2)));
        }

        toastStage.show();

        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey1 = new KeyFrame(Duration.millis(fadeInDelay), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey1);
        fadeInTimeline.setOnFinished((ae) -> {
            new Thread(() -> {
                try {
                    Thread.sleep(toastDelay);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Timeline fadeOutTimeline = new Timeline();
                KeyFrame fadeOutKey1 = new KeyFrame(Duration.millis(fadeOutDelay), new KeyValue(toastStage.getScene().getRoot().opacityProperty(), 0));
                fadeOutTimeline.getKeyFrames().add(fadeOutKey1);
                fadeOutTimeline.setOnFinished((aeb) -> toastStage.close());
                fadeOutTimeline.play();
            }).start();
        });
        fadeInTimeline.play();
    }

    /**
     * Create a Toast at the bottom of the reference stage and with the following editable parameters
     * @param ownerStage
     * @param toastMsg
     * @param toastDelay
     */
    public static void makeText(Stage ownerStage, String toastMsg, int toastDelay){
        makeText(ownerStage, toastMsg, toastDelay, BUTTON);
    }

    /**
     * Create a Toast at the bottom of the reference stage, with maximum display time and with the following editable parameter
     * @param ownerStage
     * @param toastMsg
     */
    public static void makeText(Stage ownerStage, String toastMsg){
        makeText(ownerStage, toastMsg, LONG, BUTTON);
    }

}
