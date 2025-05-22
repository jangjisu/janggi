package com.game.janggi.domain.piece.position;

import com.game.janggi.domain.piece.move.Directions;
import com.game.janggi.exception.RecoverableException;

public record PiecePosition(int rowIndex, int colIndex) implements MoveAble {
    @Override
    public boolean canMove(Directions direction) {
        int newRowIndex = this.rowIndex + direction.getTotalRow();
        int newColIndex = this.colIndex + direction.getTotalCol();

        return newRowIndex >= 0 && newRowIndex <= 9 && newColIndex >= 0 && newColIndex <= 8;
    }

    public static PiecePosition create(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > 9 || colIndex < 0 || colIndex > 8) {
            throw new RecoverableException("Invalid position: (" + rowIndex + "," + colIndex + ")");
        }

        return new PiecePosition(rowIndex, colIndex);
    }

    public static PiecePosition create(PiecePosition currentPosition, Directions directions) {
        int newRowIndex = currentPosition.rowIndex + directions.getTotalRow();
        int newColIndex = currentPosition.colIndex + directions.getTotalCol();

        return create(newRowIndex, newColIndex);
    }

    public boolean isAtPosition(int rowIndex, int colIndex) {
        return this.rowIndex == rowIndex && this.colIndex == colIndex;
    }

}
