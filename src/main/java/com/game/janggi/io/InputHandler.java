package com.game.janggi.io;

import com.game.janggi.domain.formation.FormationType;
import com.game.janggi.domain.piece.position.PiecePosition;
import com.game.janggi.util.ConvertUtil;

import java.util.Scanner;

public class InputHandler {
    public static final Scanner scanner = new Scanner(System.in);

    public FormationType selectFormationType() {
        String userInput = scanner.nextLine();

        int userInputNumber = ConvertUtil.convertToInt(userInput, () -> 0);

        return FormationType.getFromInputNumber(userInputNumber);
    }

    public PiecePosition selectPiecePosition() {
        String userInput = scanner.nextLine();

        return ConvertUtil.convertToPiecePosition(userInput);
    }
}
