package com.game.janggi.domain.piece.move;

import com.game.janggi.domain.piece.position.GongPiecePosition;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.exception.NeedStopException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Comparator;
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

    public static Movement concat(Movement standardDirection, Movement movement) {
        if (standardDirection == null || movement == null) {
            throw new IllegalArgumentException("Standard direction and movement cannot be null");
        }

        List<Direction> combined = Stream.concat(
                standardDirection.directions.stream(),
                movement.directions.stream()
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

    public static boolean isNextAvailable(List<Movement> movementList, PiecePosition currentPosition, Direction directionType) {
        if (movementList.isEmpty()) {
            return currentPosition.canMove(Movement.create(directionType));
        }

        Movement plusOneMovement = appendSameToMaxDirection(movementList, directionType);
        return currentPosition.canMove(plusOneMovement);
    }

    public static boolean isNextAvailableAndInGong(List<Movement> movementList, PiecePosition currentPosition, Direction directionType) {
        if (movementList.isEmpty()) {
            return currentPosition.canMove(Movement.create(directionType)) &&
                   GongPiecePosition.isInGongPosition(PiecePosition.create(currentPosition, Movement.create(directionType)));
        }

        Movement plusOneMovement = appendSameToMaxDirection(movementList, directionType);
        return currentPosition.canMove(plusOneMovement) &&
               GongPiecePosition.isInGongPosition(PiecePosition.create(currentPosition, plusOneMovement));
    }

    public static boolean isNextNotAvailable(List<Movement> movementList, PiecePosition currentPosition, Direction directionType) {
        return !isNextAvailable(movementList, currentPosition, directionType);
    }

    public static List<Movement> sortByLengthAsc(List<Movement> movementList) {
        return movementList.stream()
                .sorted(Comparator.comparingInt(Movement::getSize))
                .toList();
    }

    public static Movement appendSameToMaxDirection(List<Movement> movementList, Direction directionType) {
        if (movementList.isEmpty()) {
            return Movement.create(directionType);
        }

        return getMaxUniformDirections(movementList).append(directionType);
    }

    private static Movement getMaxUniformDirections(List<Movement> movementList) {
        if (movementList.isEmpty()) {
            return Movement.create();
        }

        return sortByLengthAsc(movementList).stream()
                .max(Comparator.comparingInt(Movement::getSize))
                .orElseThrow(() -> new NeedStopException("getMaxSizeDirections failed"));
    }
}
