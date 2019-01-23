package project.service;

import project.entity.RangeStringEntity;
import project.entity.Statistic;

import java.util.List;

public interface StatisticService {
    List<Statistic> getStatistic(String ipAddress);

    List<Statistic> getStatistic(RangeStringEntity request, String ipAddress);

    List<Statistic> getStatisticLast(String ipAddress);
}
