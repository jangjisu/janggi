package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.Piece;
import com.game.janggi.domain.piece.position.PiecePosition;
import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Movements {
    @Getter
    private final List<Movement> values;

    public static Movements create(List<Movement> movements) {
        return new Movements(new ArrayList<>(movements));
    }

    public static Movements empty() {
        return new Movements(new ArrayList<>());
    }

    public boolean isUnified() {
        return allMovementsInternallyUnified() &&
                allMovementsHaveSameDirection();
    }

    private boolean allMovementsInternallyUnified() {
        return values.stream().allMatch(Movement::isUnified);
    }

    private boolean allMovementsHaveSameDirection() {
        Direction standard = values.get(0).getFirst();
        return values.stream()
                .map(Movement::getFirst)
                .allMatch(dir -> dir.equals(standard));
    }

    public Movements filterUntilBlockedByPiece(Map<PiecePosition, Piece> pieces, PiecePosition currentPosition) {
        return Movements.create(this.sortByLengthAsc()
                .values.stream()
                .takeWhile(movement ->
                        pieces.get(PiecePosition.create(currentPosition, movement)) == null
                )
                .toList());
    }

    private Movements sortByLengthAsc() {
        return Movements.create(values.stream()
                .sorted(Comparator.comparingInt(Movement::getSize))
                .toList());
    }

    public void append(Movement movement) {
        if (movement.haveAnyDirection()) {
            if (nonExist(movement)) {
                this.values.add(movement);
            }
        }
    }

    private boolean isExist(Movement movement) {
        for (Movement value : values) {
            if (value.equals(movement)) {
                return true;
            }
        }

        return false;
    }

    private boolean nonExist(Movement movement) {
        return !isExist(movement);
    }

    public void concatAll(Movement movement) {
        values.replaceAll(m -> Movement.concat(m, movement));
    }


}
