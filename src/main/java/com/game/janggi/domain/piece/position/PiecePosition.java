package com.game.janggi.domain.piece.position;

import com.game.janggi.domain.piece.move.Directions;
import com.game.janggi.exception.RecoverableException;

public record PiecePosition(int rowIndex, int colIndex) implements MoveAble {
    @Override
    public boolean canMove(Directions directions) {
        int newRowIndex = this.rowIndex + directions.getTotalRow();
        int newColIndex = this.colIndex + directions.getTotalCol();

        return newRowIndex >= 0 && newRowIndex <= 8 && newColIndex >= 0 && newColIndex <= 9;
    }

    public static PiecePosition create(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > 9 || colIndex < 0 || colIndex > 10) {
            throw new RecoverableException("Invalid position: (" + rowIndex + "," + colIndex + ")");
        }

        return new PiecePosition(rowIndex, colIndex);
    }

    public static PiecePosition create(PiecePosition currentPosition, Directions directions) {
        int newRowIndex = currentPosition.rowIndex + directions.getTotalRow();
        int newColIndex = currentPosition.colIndex + directions.getTotalCol();

        return create(newRowIndex, newColIndex);
    }
}
