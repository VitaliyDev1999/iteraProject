package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.entity.*;
import project.service.HistoryService;
import project.service.IpService;
import project.service.StatisticService;
import project.algorithm.Operator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RandomController {

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IpService ipService;

    @GetMapping(value = "/test")
    public void createNumbers() {
        HistoryDbEntity historyDbEntity = new HistoryDbEntity();
        historyDbEntity.setBet("1");
        historyDbEntity.setResult("2");
        historyDbEntity.setType("3");
        IdIpEntity idIpEntity = new IdIpEntity();
        idIpEntity.setIp("324234");
        ipService.saveIp(idIpEntity);
        historyDbEntity.setIp(idIpEntity);
        historyDbEntity.setWin(true);
        historyService.saveHistory(historyDbEntity);
    }

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
