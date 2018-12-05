package project.controller;

import org.springframework.web.bind.annotation.*;
import project.algorithm.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import project.entity.HistoryEntity;
import project.entity.TryLuckEntity;
import project.entity.Type;

@RestController
@RequestMapping("/api")
public class RandomController {


    @PostMapping(value = "/bets")
    public HistoryEntity getNumbers(@RequestBody TryLuckEntity tryLuckEntity) {
        return Operator.checkWinReturnHistory(tryLuckEntity);
    }
}
