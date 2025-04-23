package com.game.janggi.domain.board;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.team.Cho;
import com.game.janggi.domain.team.Han;
import com.game.janggi.domain.team.Team;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GameBoard {
    private final List<Team> teams;

    public static GameBoard create() {
        return new GameBoard(initalizeTeams());
    }

    private static List<Team> initalizeTeams() {
        //TODO 받아온 Formation Type
        FormationType hanFormation = FormationType.SANG_MA_MA_SANG;
        FormationType choFormation = FormationType.SANG_MA_MA_SANG;

        Cho cho = Cho.create(choFormation);
        Han han = Han.create(hanFormation);

        return List.of(cho, han);
    }
}
