package com.game.janggi;


import com.game.janggi.domain.board.GameBoard;
import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.RecoverableException;
import com.game.janggi.exception.NeedStopException;
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
        FormationType choFormationType = selectFormationType();

        outputHandler.showChooseFormationTypeComments(TeamType.HAN);
        FormationType hanFormationType = selectFormationType();

        gameBoard = GameBoard.initializePieces(hanFormationType, choFormationType);

        outputHandler.showBoard(gameBoard);
    }

    private FormationType selectFormationType() {
        while (true) {
            try {
                return inputHandler.selectFormationType();
            } catch (RecoverableException e) {
                outputHandler.showErrorComments(e.getMessage());
            } catch (NeedStopException e) {
                outputHandler.showErrorEndComments();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new Janggi();
    }


}
