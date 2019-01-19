package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.entity.*;
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

    @PostMapping(value = "/bets/range")
    public HistoryDto getRangeNumber(@RequestBody RangeLuckEntity rangeLuckEntity, HttpServletRequest request) {
        return randomService.getLuckyTry(request.getRemoteAddr(), rangeLuckEntity);
    }

    @PostMapping(value = "/bets/roulette")
    public HistoryDto getRouletteNumber(@RequestBody TryLuckEntity tryLuckEntity, HttpServletRequest request) {
        return randomService.getLuckyTry(request.getRemoteAddr(), tryLuckEntity);
    }

    @PostMapping(value = "/statistic/range")
    public List<Statistic> getStatistic(@RequestBody RangeStringEntity rangeStringEntity, HttpServletRequest request) {
        return statisticService.getStatistic(rangeStringEntity, request.getRemoteAddr());
    }

}
