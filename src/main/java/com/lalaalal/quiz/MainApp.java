package com.lalaalal.quiz;

import com.lalaalal.quiz.controller.BoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lalaalal/quiz/view/main.fxml"));
        BoardController boardController = new BoardController(4, 4, 3);

        loader.setController(boardController);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000, 1000);

        primaryStage.setTitle("Quiz");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
