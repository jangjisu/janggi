package com.game.janggi;


import com.game.janggi.domain.board.GameBoard;
import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;
import com.game.janggi.exception.NeedStopException;
import com.game.janggi.exception.RecoverableException;
import com.game.janggi.io.InputHandler;
import com.game.janggi.io.OutputHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Janggi {
    private final OutputHandler outputHandler;
    private final InputHandler inputHandler;
    private GameBoard gameBoard;

    public void run() {
        initializeGameBoard();

        doGame();
    }

    private void doGame() {
        //TODO : Implement game loop
        //while (gameBoard.isGameIsInProgress()) {
        outputHandler.showBoard(gameBoard);

        outputHandler.showTurnComments(gameBoard.getCurrentTurn());

        selectPiece();

        outputHandler.showSelectedPieceComments(gameBoard.getSelectedPiece());

        outputHandler.showMoveComments();

        movePiece();

        gameBoard.changeTurn();
        //}

    }

    private void initializeGameBoard() {
        outputHandler.showGameStartComments();

        outputHandler.showChooseFormationTypeComments(TeamType.CHO);
        FormationType choFormationType = selectFormationType();

        outputHandler.showChooseFormationTypeComments(TeamType.HAN);
        FormationType hanFormationType = selectFormationType();

        gameBoard = GameBoard.initializePieces(hanFormationType, choFormationType);
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

    private void selectPiece() {
        while (true) {
            try {
                PiecePosition selectedPiecePosition = inputHandler.selectPiecePosition();
                gameBoard.validatePieceSelection(selectedPiecePosition);
                break;
            } catch (RecoverableException e) {
                outputHandler.showErrorComments(e.getMessage());
            } catch (NeedStopException e) {
                outputHandler.showErrorEndComments();
                System.exit(0);
            }
        }
    }

    private void movePiece() {
        while (true) {
            try {
                PiecePosition selectedPiecePosition = inputHandler.selectPiecePosition();
                gameBoard.validatePieceMove(selectedPiecePosition);
                break;
            } catch (RecoverableException e) {
                outputHandler.showErrorComments(e.getMessage());
            } catch (NeedStopException e) {
                outputHandler.showErrorEndComments();
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        Janggi janggi = new Janggi(new OutputHandler(), new InputHandler());
        janggi.run();
    }


}
