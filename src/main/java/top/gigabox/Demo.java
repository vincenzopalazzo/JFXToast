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
package top.gigabox;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import top.gigabox.components.JFXToast;

/**
 *
 * @author https://github.com/vincenzopalazzo
 */
public class Demo extends Application {

    public static void main(String[] args){
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = new AnchorPane();
        Button button = new Button("TEST TOAST");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                JFXToast.makeText(primaryStage, "I'm Super Sayan", JFXToast.LONG, JFXToast.BUTTON);
            }
        });
        ((AnchorPane) parent).getChildren().add(button);
        Scene scene = new Scene(parent);


        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Demo support components");
        primaryStage.show();
    }
}
