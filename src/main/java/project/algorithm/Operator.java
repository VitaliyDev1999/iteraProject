package project.algorithm;

import project.entity.HistoryEntity;
import project.entity.TryLuckEntity;
import project.entity.Type;
import project.utils.NumberClass;
import project.utils.RuletteNumList;

import java.util.concurrent.ThreadLocalRandom;

public class Operator {

    private static double roundNumber(double num){
        return Math.round(num * 10000.0) / 10000.0;
    }

    public static HistoryEntity checkWinReturnHistory(TryLuckEntity inputData){
        HistoryEntity history = new HistoryEntity();
        randomValueAndCalculateDegree(inputData.getDegree(), history);
        if (inputData.getType() == Type.SINGLE || inputData.getType() == Type.RANGE)
            checkNumbers(history, inputData);
        else
            checkColor(history, inputData);
        return history;
    }

    private static void randomValueAndCalculateDegree(double angle, HistoryEntity history) {
        history.setChoice(ThreadLocalRandom.current().nextInt(0, 37));
        double numberAngle;
        if (RuletteNumList.getNumber(history.getChoice()).getStartAngle() > RuletteNumList.getNumber(history.getChoice()).getEndAngle())
            numberAngle = roundNumber(ThreadLocalRandom.current().nextDouble(RuletteNumList.getNumber(history.getChoice()).getStartAngle(),
                    360.0 + RuletteNumList.getNumber(history.getChoice()).getEndAngle()));
        else
            numberAngle = roundNumber(ThreadLocalRandom.current().nextDouble(RuletteNumList.getNumber(history.getChoice()).getStartAngle(),
                    RuletteNumList.getNumber(history.getChoice()).getEndAngle()));
        if (numberAngle > 360.0)
            numberAngle -= 360.0;
        if (numberAngle - angle > 0.0)
            history.setResultDegree(numberAngle - angle);
        else
            history.setResultDegree(360.0 + numberAngle - angle);
    }

    private static void checkNumbers(HistoryEntity history, TryLuckEntity inputData){
        String bet = "";
        for (int i = 0; i < inputData.getValues().length; i++) {
            bet += inputData.getValues()[i];
            if (inputData.getValues().length - 1 != i)
                bet += ", ";
        }
        history.setBet(bet);
        history.setGame(false);
        for (int i = 0; i < inputData.getValues().length; i++) {
            if (inputData.getValues()[i] == history.getChoice()) {
                history.setGame(true);
                return;
            }
        }
    }

    private static void checkColor(HistoryEntity history, TryLuckEntity inputData){
        history.setBet(inputData.getType().toString());
        if (inputData.getType().toString().compareTo(RuletteNumList.getNumber(history.getChoice()).getColor().toString()) == 0)
            history.setGame(true);
        else
            history.setGame(false);
    }

}
