package com.game.janggi.io;

import com.game.janggi.domain.board.GameBoard;
import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.Piece;
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

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                Piece piece = board.getPiece(i, j);
                String name = (piece == null) ? "□ㅤ" : piece.printPieceName();
                System.out.printf(String.format("%-2s ", name));
            }
            System.out.println();
        }
    }

    public void showErrorComments(String message) {
        System.out.println(message);
    }

    public void showErrorEndComments() {
        System.out.println("예기치 못한 문제로 게임을 종료합니다.");
    }
}
