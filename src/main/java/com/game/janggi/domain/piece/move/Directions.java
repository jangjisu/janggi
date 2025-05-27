package com.game.janggi.domain.piece.move;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class Directions {
    private final List<Direction> directions;

    public static Directions create(Direction... directionList) {
        return new Directions(List.of(directionList));
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
        return java.util.stream.IntStream.range(1, directions.size())
                .mapToObj(i -> new Directions(new java.util.ArrayList<>(directions.subList(0, i))))
                .toList();
    }

}
