package com.game.janggi.domain.piece.move;

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
public class Directions {
    private final List<Direction> directions;

    public static Directions create(Direction... directionList) {
        return new Directions(List.of(directionList));
    }

    public static List<Directions> sortByLengthAsc(List<Directions> directions) {
        return directions.stream()
                .sorted(Comparator.comparingInt(Directions::getDirectionSize))
                .toList();
    }

    public static Directions concat(Directions standardDirection, Directions directions) {
        List<Direction> combined = Stream.concat(
                standardDirection.directions.stream(),
                directions.directions.stream()
        ).toList();

        return Directions.create(combined.toArray(new Direction[0]));
    }

    public Directions append(Direction next) {
        return new Directions(
                Stream.concat(directions.stream(), Stream.of(next)).toList()
        );
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

    public List<Directions> getMiddleDirections() {
        if (directions.size() < 2) {
            return List.of();
        }
        return IntStream.range(1, directions.size())
                .mapToObj(i -> new Directions(List.copyOf(directions.subList(0, i))))
                .toList();
    }

    public int getDirectionSize() {
        return directions.size();
    }

    public static boolean canMakeNextDirections(List<Directions> directions, PiecePosition currentPosition, Direction directionType) {
        if (directions.isEmpty()) {
            return currentPosition.canMove(Directions.create(directionType));
        }

        Directions plusOneDirections = getPlusOneDirections(directions, directionType);
        return currentPosition.canMove(plusOneDirections);
    }

    public static boolean canNotMakeNextDirections(List<Directions> directions, PiecePosition currentPosition, Direction directionType) {
        return !canMakeNextDirections(directions, currentPosition, directionType);
    }

    public static Directions getPlusOneDirections(List<Directions> directions, Direction directionType) {
        if (directions.isEmpty()) {
            return Directions.create(directionType);
        }

        return getMaxSizeDirections(directions).append(directionType);
    }

    private static Directions getMaxSizeDirections(List<Directions> directions) {
        if (directions.isEmpty()) {
            return Directions.create();
        }

        return sortByLengthAsc(directions).stream()
                .max(Comparator.comparingInt(Directions::getDirectionSize))
                .orElseThrow(() -> new NeedStopException("getMaxSizeDirections failed"));
    }

}
