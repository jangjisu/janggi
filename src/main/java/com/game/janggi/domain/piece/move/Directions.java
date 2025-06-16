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
    private final List<Direction> directionList;

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
                standardDirection.directionList.stream(),
                directions.directionList.stream()
        ).toList();

        return Directions.create(combined.toArray(new Direction[0]));
    }

    public static Directions empty() {
        return new Directions(List.of());
    }

    public boolean isNotEmpty() {
        return !directionList.isEmpty();
    }

    public Directions append(Direction next) {
        return new Directions(
                Stream.concat(directionList.stream(), Stream.of(next)).toList()
        );
    }

    public int getTotalRow() {
        return directionList.stream()
                .mapToInt(Direction::getRow)
                .sum();
    }

    public int getTotalCol() {
        return directionList.stream()
                .mapToInt(Direction::getCol)
                .sum();
    }

    public List<Directions> getMiddleDirections() {
        if (directionList.size() < 2) {
            return List.of();
        }
        return IntStream.range(1, directionList.size())
                .mapToObj(i -> new Directions(List.copyOf(directionList.subList(0, i))))
                .toList();
    }

    public int getDirectionSize() {
        return directionList.size();
    }

    public static boolean isNextAvailable(List<Directions> directions, PiecePosition currentPosition, Direction directionType) {
        if (directions.isEmpty()) {
            return currentPosition.canMove(Directions.create(directionType));
        }

        Directions plusOneDirections = appendNextStep(directions, directionType);
        return currentPosition.canMove(plusOneDirections);
    }

    public static boolean isNextNotAvailable(List<Directions> directions, PiecePosition currentPosition, Direction directionType) {
        return !isNextAvailable(directions, currentPosition, directionType);
    }

    public static Directions appendNextStep(List<Directions> directions, Direction directionType) {
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
