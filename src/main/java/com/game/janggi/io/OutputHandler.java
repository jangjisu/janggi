package com.game.janggi.io;

import com.game.janggi.domain.board.GameBoard;
import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

import java.util.Optional;

public class OutputHandler {
    private static final String EMPTY_PIECE_DISPLAY = "□ ";

    public void showGameStartComments() {
        System.out.println(">>>>>>>>>>>>>");
        System.out.println("장기 게임 시작");
        System.out.println(">>>>>>>>>>>>>");
    }

    public void showChooseFormationTypeComments(TeamType teamType) {
        System.out.println(teamType.getTeamName() + " 진영의 진형을 선택하세요.");

        for (FormationType formationType : FormationType.values()) {
            System.out.println(formationType.getInputNumber() + ". " + formationType.getDescription());
        }
    }

    public void showBoard(GameBoard board) {
        int maxRow = GameBoard.BOARD_ROW_SIZE;
        int maxCol = GameBoard.BOARD_COL_SIZE;

        System.out.printf("   ");
        for (int row = 0; row < maxRow; row++) {
            char c = (char) ('A' + row);
            System.out.printf("%-2s ", c);
        }
        System.out.println();

        for (int col = 0; col < maxCol; col++) {
            for (int row = 0; row < maxRow; row++) {
                if (row == 0) {
                    System.out.printf("%-2s ", col);
                }

                PiecePosition piecePosition = PiecePosition.create(row, col);

                Optional<Piece> pieceCandidate = board.getPieceAt(piecePosition);
                String printablePiece = pieceCandidate.isPresent() ? pieceCandidate.get().printPieceName() : EMPTY_PIECE_DISPLAY;
                System.out.printf(String.format("%-2s ", printablePiece));
            }
            System.out.println();
        }
    }

    public void showTurnComments(TeamType teamType) {
        System.out.println(teamType.getTeamName() + " 진영의 턴입니다.");
        System.out.println("움직일 말을 선택하세요. EX) A0");
    }

    public void showSelectedPieceComments(Piece piece) {
        System.out.println(piece.printPieceName() + "을(를) 선택했습니다.");
    }

    public void showMoveComments() {
        System.out.println("선택한 말이 움직일 위치을 선택하세요. EX) A0");
    }

    public void showErrorComments(String message) {
        System.out.println(message);
    }

    public void showErrorEndComments() {
        System.out.println("예기치 못한 문제로 게임을 종료합니다.");
    }

    public void showEndComments() {
        System.out.println(">>>>>>>>>>>>>");
        System.out.println("게임이 종료되었습니다.");
        System.out.println(">>>>>>>>>>>>>");
    }
}
