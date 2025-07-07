package com.game.janggi.domain.piece.position;

import com.game.janggi.domain.piece.move.Movement;
import com.game.janggi.exception.RecoverableException;

public record PiecePosition(int rowIndex, int colIndex) implements MoveAble {
    @Override
    public boolean canMove(Movement movement) {
        int newRowIndex = this.rowIndex + movement.getTotalRow();
        int newColIndex = this.colIndex + movement.getTotalCol();

        return newRowIndex >= 0 && newRowIndex <= 8 && newColIndex >= 0 && newColIndex <= 9;
    }

    public static PiecePosition create(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > 9 || colIndex < 0 || colIndex > 10) {
            throw new RecoverableException("Invalid position: (" + rowIndex + "," + colIndex + ")");
        }

        return new PiecePosition(rowIndex, colIndex);
    }

    public static PiecePosition create(PiecePosition currentPosition, Movement movement) {
        int newRowIndex = currentPosition.rowIndex + movement.getTotalRow();
        int newColIndex = currentPosition.colIndex + movement.getTotalCol();

        return create(newRowIndex, newColIndex);
    }
}
