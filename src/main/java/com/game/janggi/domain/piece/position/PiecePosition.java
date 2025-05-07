package com.game.janggi.domain.piece.position;

import com.game.janggi.exception.NeedStopException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class PiecePosition {
    private final int rowIndex;
    private final int colIndex;

    public static PiecePosition create(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex > 9 || colIndex < 0 || colIndex > 8) {
            throw new NeedStopException("Invalid position: ("+rowIndex+","+colIndex+")");
        }

        return new PiecePosition(rowIndex, colIndex);
    }

    public boolean isAtPosition(int rowIndex, int colIndex) {
        return this.rowIndex == rowIndex && this.colIndex == colIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PiecePosition that = (PiecePosition) o;
        return rowIndex == that.rowIndex && colIndex == that.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }
}
