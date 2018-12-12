package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.service.StatisticService;
import project.algorithm.Operator;
import project.entity.HistoryEntity;
import project.entity.Statistic;
import project.entity.StatisticRequest;
import project.entity.TryLuckEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RandomController {

    @Autowired
    private StatisticService statisticService;

    @PostMapping(value = "/bets")
    public HistoryEntity getNumbers(@RequestBody TryLuckEntity tryLuckEntity, HttpServletRequest request) {
        System.out.println(request.getRemoteAddr());
        return Operator.checkWinReturnHistory(tryLuckEntity);
    }

    @PostMapping(value = "/statistic/range")
    public List<Statistic> getNumbers(@RequestBody StatisticRequest statisticRequest) {
        return statisticService.getStatistic(statisticRequest);
    }

}
