package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Movement {
    private final List<Direction> directions;

    public static Movement create(Direction... directions) {
        return new Movement(List.of(directions));
    }

    public static Movement create(List<Direction> directions) {
        return new Movement(directions);
    }

    public static Movement empty() {
        return new Movement(List.of());
    }

    public boolean haveAnyDirection() {
        return !directions.isEmpty();
    }

    public boolean haveNoDirection() {
        return directions.isEmpty();
    }

    public Direction getFirst() {
        if (directions.isEmpty()) {
            throw new IllegalStateException("No directions available");
        }
        return directions.get(0);
    }

    public int getSize() {
        return directions.size();
    }

    public boolean isUnified() {
        if (directions.isEmpty()) {
            return true;
        }

        Direction firstDirection = directions.get(0);

        return directions.stream().allMatch(direction -> direction.equals(firstDirection));
    }

    public int getTotalRow() {
        return directions.stream()
                .mapToInt(Direction::getRow)
                .sum();
    }

    public int getTotalCol() {
        return directions.stream()
                .mapToInt(Direction::getCol)
                .sum();
    }

    public Movement append(Direction next) {
        if (next == null) {
            throw new IllegalArgumentException("Next direction cannot be null");
        }

        return new Movement(
                Stream.concat(directions.stream(), Stream.of(next)).toList()
        );
    }

    public static Movement concat(Movement movement1, Movement movement2) {
        if (movement1 == null || movement2 == null) {
            throw new IllegalArgumentException("movement1 and movement2 cannot be null");
        }

        List<Direction> combined = Stream.concat(
                movement1.directions.stream(),
                movement2.directions.stream()
        ).toList();

        return Movement.create(combined.toArray(new Direction[0]));
    }

    public List<Movement> getPartialMovements() {
        if (directions.size() < 2) {
            return List.of();
        }
        return IntStream.range(1, directions.size())
                .mapToObj(i -> new Movement(List.copyOf(directions.subList(0, i))))
                .toList();
    }

    public static boolean isNextAvailable(Movements movements, PiecePosition currentPosition, Direction directionType) {
        if (movements.isEmpty()) {
            return currentPosition.canMove(Movement.create(directionType));
        }

        Movement plusOneMovement = appendSameToMaxDirection(movements, directionType);
        return currentPosition.canMove(plusOneMovement);
    }

    public static boolean isNextAvailableAndInGong(Movements movements, PiecePosition currentPosition, Direction directionType) {
        if (movements.isEmpty()) {
            return currentPosition.canMove(Movement.create(directionType)) &&
                    GongPiecePosition.isInGongPosition(PiecePosition.create(currentPosition, Movement.create(directionType)));
        }

        Movement plusOneMovement = appendSameToMaxDirection(movements, directionType);
        return currentPosition.canMove(plusOneMovement) &&
                GongPiecePosition.isInGongPosition(PiecePosition.create(currentPosition, plusOneMovement));
    }

    public static boolean isNextNotAvailable(Movements movements, PiecePosition currentPosition, Direction directionType) {
        return !isNextAvailable(movements, currentPosition, directionType);
    }

    public static Movement appendSameToMaxDirection(Movements movements, Direction directionType) {
        if (movements.isEmpty()) {
            return Movement.create(directionType);
        }

        return movements.getMax().append(directionType);
    }
}
