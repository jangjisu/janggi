package com.game.janggi.io;

import com.game.janggi.domain.board.GameBoard;
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

        System.out.println("1. 상마마상");
        System.out.println("2. 상마상마");
        System.out.println("3. 마상마상");
        System.out.println("4. 마상상마");
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
}
