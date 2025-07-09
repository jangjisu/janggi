package com.game.janggi.domain.piece.position;

import com.game.janggi.domain.piece.move.Movement;

public record PiecePosition(int rowIndex, int colIndex) implements MoveAble {
    @Override
    public boolean canMove(Movement movement) {
        int newRowIndex = this.rowIndex + movement.getTotalRow();
        int newColIndex = this.colIndex + movement.getTotalCol();

        return checkCanMakeRowCol(newRowIndex, newColIndex);
    }

    public static PiecePosition create(int rowIndex, int colIndex) {
        if (checkCanNotMakeRowCol(rowIndex, colIndex)) {
            throw new IllegalArgumentException("Invalid position: (" + rowIndex + "," + colIndex + ")");
        }

        return new PiecePosition(rowIndex, colIndex);
    }

    private static boolean checkCanMakeRowCol(int rowIndex, int colIndex) {
        return rowIndex >= 0 && rowIndex <= 8 && colIndex >= 0 && colIndex <= 9;
    }

    private static boolean checkCanNotMakeRowCol(int rowIndex, int colIndex) {
        return !checkCanMakeRowCol(rowIndex, colIndex);
    }

    public static PiecePosition create(PiecePosition currentPosition, Movement movement) {
        int newRowIndex = currentPosition.rowIndex + movement.getTotalRow();
        int newColIndex = currentPosition.colIndex + movement.getTotalCol();

        return create(newRowIndex, newColIndex);
    }
}
