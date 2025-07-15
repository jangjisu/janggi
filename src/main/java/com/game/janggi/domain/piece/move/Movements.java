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
        return new Movements(List.copyOf(movements));
    }

    public static Movements empty() {
        return new Movements(List.of());
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

    public Movements sortByLengthAsc() {
        return Movements.create(values.stream()
                .sorted(Comparator.comparingInt(Movement::getSize))
                .toList());
    }

    public Movements append(Movement movement) {
        if (!movement.haveAnyDirection() || values.contains(movement)) {
            return this;
        }

        List<Movement> newValues = new ArrayList<>(this.values);
        newValues.add(movement);
        return Movements.create(newValues);
    }

    public Movements concatAll(Movement movement) {
        List<Movement> newValues = values.stream()
                .map(m -> Movement.concat(m, movement))
                .toList();

        return Movements.create(newValues);
    }


    public Movement getMax() {
        return values.stream()
                .max(Comparator.comparingInt(Movement::getSize))
                .orElse(Movement.empty());
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }
}
