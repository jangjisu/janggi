package com.game.janggi.io;

import com.game.janggi.domain.board.GameBoard;
import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.domain.team.TeamType;

public class OutputHandler {
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
        int row = board.getRowSize();
        int col = board.getColSize();

        System.out.printf("   ");
        for (int i = 0; i < col; i++) {
            char c = (char) ('A' + i);
            System.out.printf("%-2s ", c);
        }
        System.out.println();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    System.out.printf("%-2s ", i);
                }

                PiecePosition piecePosition = PiecePosition.create(i, j);
                Piece piece = board.getPiece(piecePosition);
                String name = (piece == null) ? "□ㅤ" : piece.printPieceName();
                System.out.printf(String.format("%-2s ", name));
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
}
