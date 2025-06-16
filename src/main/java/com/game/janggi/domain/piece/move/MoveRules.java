package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.PieceType;
import com.game.janggi.domain.piece.Po;

public class MoveRules {
    public static boolean canBeJumpedOver(Piece nextPiece) {
        return !canNotBeJumpedOver(nextPiece);
    }

    public static boolean canNotBeJumpedOver(Piece nextPiece) {
        return nextPiece instanceof Po;
    }

    public static boolean canMoveToNextPiece(PieceType currentPieceType, Piece nextPiece, boolean isDifferentTeam) {
        if (currentPieceType == PieceType.PHO) {
            return isDifferentTeam && canBeJumpedOver(nextPiece);
        }

        return isDifferentTeam;
    }
}
