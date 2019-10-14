package com.lalaalal.quiz.controller;

import com.lalaalal.quiz.model.Board;
import com.lalaalal.quiz.model.Pos;
import com.lalaalal.quiz.model.Range;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

class MyButton extends Button {
    private Pos pos;

    MyButton(int x, int y) {
        pos = new Pos(x, y);

        setPrefHeight(Double.MAX_VALUE);
        setPrefWidth(Double.MAX_VALUE);
        styleProperty().setValue("-fx-background-color: black; -fx-border-width: 1px; -fx-border-color: white");

        setCursor(Cursor.HAND);
    }

    Pos getPos() {
        return pos;
    }

    void setTransparent(boolean value) {
        if (value) hide();
        else show();
    }

    private void hide() {
        setOpacity(0);
    }

    private void show() {
        setOpacity(1);
    }
}

public class BoardController implements Initializable {

    private Board board;

    @FXML
    private GridPane gridPane;

    private MyButton[][] buttons;

    public BoardController(int row, int col, int difficulty) throws Exception {
        board = new Board(row, col, difficulty);
        Pos[] arr = Pos.randPosArray(difficulty, new Range(0, row - 1), new Range(0, col - 1));

        for (int i = 0; i < difficulty; i++)
            board.openTile(arr[i]);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            buttons = new MyButton[board.COL][board.ROW];
            initButtons();

            while (gridPane.getColumnCount() < board.COL) {
                int originalColumnCount = gridPane.getColumnCount();
                gridPane.addColumn(originalColumnCount);
                ColumnConstraints column = gridPane.getColumnConstraints().get(originalColumnCount);
                column.setHgrow(Priority.SOMETIMES);
                column.setPrefWidth(100.0);
                column.setMinWidth(10.0);
            }

            while (gridPane.getRowCount() < board.ROW) {
                int originalRowCount = gridPane.getRowCount();
                gridPane.addRow(originalRowCount);
                RowConstraints row = gridPane.getRowConstraints().get(originalRowCount);
                row.setVgrow(Priority.SOMETIMES);
                row.setPrefHeight(100.0);
                row.setMinHeight(10.0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void initButtons() throws Exception {
        if (buttons == null)
            throw new Exception("MyButton[][] buttons is not constructed");
        for (int y = 0; y < board.COL; y++) {
            for (int x = 0; x < board.ROW; x++) {
                MyButton button = buttons[y][x] = new MyButton(x, y);
                gridPane.add(button, y, x);
                setOnClickListener(button);
            }
        }
        updateBoardView();
    }

    private void setOnClickListener(MyButton button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Debug
                    System.out.println(button.getPos() + " Clicked");
                    board.openTile(button.getPos());
                    updateBoardView();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                if (board.allTilesAreOpened())
                    succeed();
            }
        });
    }

    private void updateBoardView() {
        for (int y = 0; y < board.COL; y++) {
            for (int x = 0; x < board.ROW; x++) {
                boolean open = board.isOpen(x, y);
                buttons[y][x].setTransparent(open);
            }
        }
    }

    private void succeed() {
        try {
            System.out.println("Succeed!");

            gridPane.setDisable(true);
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lalaalal/quiz/view/succeed.fxml"));
            PopupController popupController = new PopupController();
            loader.setController(popupController);

            Parent root = loader.load();
            Scene scene = new Scene(root, 400, 300);

            stage.setTitle("성공!");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
