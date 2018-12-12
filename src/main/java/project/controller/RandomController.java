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

    @GetMapping(value = "/test")
    public List<HistoryDbEntity> getHistory(HttpServletRequest request) {
        ipService.saveIp(request.getRemoteAddr());
        return historyService.getSeveralLastHistory(request.getRemoteAddr());
    }

    @PostMapping(value = "/bets")
    public HistoryEntity getNumbers(@RequestBody TryLuckEntity tryLuckEntity, HttpServletRequest request) {
        return randomService.getLuckyTry(request.getRemoteAddr(), tryLuckEntity);
    }

    @PostMapping(value = "/statistic/range")
    public List<Statistic> getNumbers(@RequestBody StatisticRequest statisticRequest) {
        return statisticService.getStatistic(statisticRequest);
    }

}
