package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.entity.*;
import project.exception.EqualNumbersForRangeException;
import project.exception.NumberAreNotSuitableForRouletteException;
import project.exception.SecondRangeIsOutOfFirstBoundException;
import project.service.HistoryService;
import project.service.IpService;
import project.service.RandomService;
import project.service.StatisticService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RandomController {

    @Autowired
    private RandomService randomService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IpService ipService;

    @GetMapping(value = "/history")
    public List<HistoryDto> getHistory(HttpServletRequest request) {
        return historyService.getSeveralLastHistory(ipService.saveIp(request.getRemoteAddr()).getIp());
    }

    @GetMapping(value = "/statistic")
    public List<Statistic> getStatistic(HttpServletRequest request) {
        return statisticService.getStatistic(request.getRemoteAddr());
    }

    @PostMapping(value = "/statistic/range")
    public List<Statistic> getStatistic(@RequestBody RangeStringEntity rangeStringEntity, HttpServletRequest request) {
        return statisticService.getStatistic(rangeStringEntity, request.getRemoteAddr());
    }

    @PostMapping(value = "/bets/range")
    public HistoryDto getRangeNumber(@RequestBody RangeLuckEntity rangeLuckEntity, HttpServletRequest request) {
        rangeLuckEntity.setRange();
        rangeLuckEntity.setBet();
        if (rangeLuckEntity.getRange().size() == 1){
            throw new EqualNumbersForRangeException("Numbers in range are equal!");
        }
        if (rangeLuckEntity.getRange().get(0) > rangeLuckEntity.getBet().get(0) ||
                rangeLuckEntity.getRange().get(rangeLuckEntity.getRange().size() - 1) <
                        rangeLuckEntity.getBet().get(rangeLuckEntity.getBet().size() - 1)){
            throw new SecondRangeIsOutOfFirstBoundException("Your bet is out of range!");
        }
        return randomService.getLuckyTryRange(request.getRemoteAddr(), rangeLuckEntity);
    }

    @PostMapping(value = "/bets/roulette")
    public HistoryDto getRouletteNumber(@RequestBody TryLuckEntity tryLuckEntity, HttpServletRequest request) {
        for (int num: tryLuckEntity.getValues()) {
            if (num < 0 || num > 36) {
                throw new NumberAreNotSuitableForRouletteException("Roulette operate only with 0-36 values!");
            }
        }
        return randomService.getLuckyTry(request.getRemoteAddr(), tryLuckEntity);
    }
}
