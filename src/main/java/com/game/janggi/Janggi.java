package com.game.janggi;


import com.game.janggi.domain.board.GameBoard;
import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.io.InputHandler;
import com.game.janggi.io.OutputHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Janggi {
    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;
    private GameBoard gameBoard;

    public Janggi() {
        this.outputHandler = new OutputHandler();
        this.inputHandler = new InputHandler();
        initalizeGameBoard();
    }

    private void initalizeGameBoard() {
        outputHandler.showGameStartComments();

        outputHandler.showChooseFormationTypeComments(TeamType.CHO);
        FormationType choFormationType = inputHandler.selectFormationType();

        outputHandler.showChooseFormationTypeComments(TeamType.HAN);
        FormationType hanFormationType = inputHandler.selectFormationType();

        gameBoard = GameBoard.initalizePieces(hanFormationType, choFormationType);

        outputHandler.showBoard(gameBoard);
    }

    public static void main(String[] args) {
        Janggi janggi = new Janggi();
    }




}
