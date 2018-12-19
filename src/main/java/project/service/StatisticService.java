package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.*;
import project.repository.IpRepository;
import project.repository.StatisticRepository;
import project.repository.StatisticRequestRepository;
import project.utils.ParseRange;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private StatisticRequestRepository statisticRequestRepository;

    public List<Statistic> getStatistic(RangeStringEntity request, String ipAddress) {
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if (idIpEntity == null) {
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            ipRepository.save(idIpEntity);
        }
        StatisticRequest statisticRequest = statisticRequestRepository.findByIpEquals(idIpEntity.getId(), request.getRange());
        if (statisticRequest == null) {
            statisticRequest = new StatisticRequest();
            statisticRequest.setIp(idIpEntity);
            statisticRequest.setCount(0);
            statisticRequest.setRange(request.getRange());
            statisticRequestRepository.save(statisticRequest);
            List<Statistic> statistics = ParseRange.fillStatistic(ParseRange.parseRange(statisticRequest.getRange()), statisticRequest);
            for (Statistic statistic:
                 statistics) {
                statisticRepository.save(statistic);
            }
        }
        List<Statistic> statisticResult = new ArrayList<>();
        statisticResult.add(statisticRepository.findAllByIdMax(statisticRequest.getId()));
        statisticResult.add(statisticRepository.findAllByIdMin(statisticRequest.getId()));
        return statisticResult;
    }
}
