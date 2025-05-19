package com.game.janggi.domain.piece.move;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
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

}
