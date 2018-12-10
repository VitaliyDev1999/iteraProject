package project.service;

import org.springframework.stereotype.Service;
import project.entity.Statistic;
import project.entity.StatisticRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {

    public List<Statistic> getStatistic(StatisticRequest request){
        return new ArrayList<Statistic>(){{
            new Statistic();
            new Statistic();
        }};
    }
}
