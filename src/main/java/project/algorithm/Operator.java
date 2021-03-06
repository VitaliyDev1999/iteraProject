package project.algorithm;

import project.entity.HistoryDto;
import project.entity.RangeLuckEntity;
import project.entity.TryLuckEntity;
import project.entity.Type;
import project.utils.RouletteNumList;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Operator {

    private static double roundNumber(double num) {
        return Math.round(num * 10000.0) / 10000.0;
    }

    public static HistoryDto checkWinReturnHistoryRange(RangeLuckEntity inputData) {
        HistoryDto history = new HistoryDto();
        history.setRange(inputData.getRangeInput());
        history.setBet(inputData.getBetInput());
        history.setResultDegree(0.0);
        history.setChoice(makeRandomInt(inputData.getRange().get(0), inputData.getRange().get(inputData.getRange().size() - 1)));
        history.setGame(checkWin(history.getChoice(), inputData.getBet()));
        history.setColor(Color.BLACK);
        return history;
    }

    public static HistoryDto checkWinReturnHistoryRoulette(TryLuckEntity inputData) {
        HistoryDto history = new HistoryDto();
        randomValueAndCalculateDegree(inputData.getDegree(), history);
        if (inputData.getType() == Type.SINGLE || inputData.getType() == Type.RANGE)
            checkNumbers(history, inputData);
        else
            checkColor(history, inputData);
        return history;
    }

    private static String  checkWin(int choice, List<Integer> betRange){
        for (int i = 0; i < betRange.size(); i++) {
            if (betRange.get(i).compareTo(choice) == 0)
                return "Win";
        }
        return "Lose";
    }

    private static void randomValueAndCalculateDegree(double angle, HistoryDto history) {
        history.setChoice(ThreadLocalRandom.current().nextInt(0, 37));
        double numberAngle;
        if (RouletteNumList.getNumber(history.getChoice()).getStartAngle() > RouletteNumList.getNumber(history.getChoice()).getEndAngle())
            numberAngle = roundNumber(ThreadLocalRandom.current().nextDouble(RouletteNumList.getNumber(history.getChoice()).getStartAngle(),
                    360.0 + RouletteNumList.getNumber(history.getChoice()).getEndAngle()));
        else
            numberAngle = roundNumber(ThreadLocalRandom.current().nextDouble(RouletteNumList.getNumber(history.getChoice()).getStartAngle(),
                    RouletteNumList.getNumber(history.getChoice()).getEndAngle()));
        if (numberAngle > 360.0)
            numberAngle -= 360.0;
        if (numberAngle - angle > 0.0)
            history.setResultDegree(numberAngle - angle);
        else
            history.setResultDegree(360.0 + numberAngle - angle);
        history.setColor(RouletteNumList.getNumber(history.getChoice()).getColor());
    }

    private static void checkNumbers(HistoryDto history, TryLuckEntity inputData) {
        String bet = "";
        for (int i = 0; i < inputData.getValues().length; i++) {
            bet += inputData.getValues()[i];
            if (inputData.getValues().length - 1 != i)
                bet += ", ";
        }
        history.setBet(bet);
        history.setGame("Lose");
        for (int i = 0; i < inputData.getValues().length; i++) {
            if (inputData.getValues()[i] == history.getChoice()) {
                history.setGame("Win");
                return;
            }
        }
    }

    private static void checkColor(HistoryDto history, TryLuckEntity inputData) {
        history.setBet(inputData.getType().toString());
        if (inputData.getType().toString().compareTo(RouletteNumList.getNumber(history.getChoice()).getColor().toString()) == 0)
            history.setGame("Win");
        else
            history.setGame("Lose");
    }

    private static int makeRandomInt(int first, int second){
        return ThreadLocalRandom.current().nextInt(first, second + 1);
    }

}
