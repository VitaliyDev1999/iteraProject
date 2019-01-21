package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.IdIpEntity;
import project.entity.RangeStringEntity;
import project.entity.Statistic;
import project.entity.StatisticRequest;
import project.repository.IpRepository;
import project.repository.StatisticRepository;
import project.repository.StatisticRequestRepository;
import project.service.StatisticService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    private static final String ROULETTE_RANGE = "Roulette";

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private StatisticRequestRepository statisticRequestRepository;

    public List<Statistic> getSeveralLastStatistic(String ipAddress){
        if(ipAddress != null) {
            IdIpEntity idIpEntity = findOrSaveIpEntity(ipAddress);
            StatisticRequest statisticRequest =
                    statisticRequestRepository.findByIdEquals(idIpEntity.getId(), ROULETTE_RANGE);
            List<Statistic> statisticResult = new ArrayList<>();
            statisticResult.add(statisticRepository.findAllByIdMax(statisticRequest.getId()));
            statisticResult.add(statisticRepository.findAllByIdMin(statisticRequest.getId()));
            return statisticResult;
        }
        return null;
    }

    public List<Statistic> getStatistic(RangeStringEntity request, String ipAddress) {
        if(ipAddress != null) {
            IdIpEntity idIpEntity = findOrSaveIpEntity(ipAddress);
            StatisticRequest statisticRequest =
                    statisticRequestRepository.findByIdEquals(idIpEntity.getId(), request.getRange());
            List<Statistic> statisticResult = new ArrayList<>();
            statisticResult.add(statisticRepository.findAllByIdMax(statisticRequest.getId()));
            statisticResult.add(statisticRepository.findAllByIdMin(statisticRequest.getId()));
            return statisticResult;
        }
        return null;
    }

    private IdIpEntity findOrSaveIpEntity(String ipAddress) {
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if (idIpEntity == null) {
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            idIpEntity = ipRepository.save(idIpEntity);
        }
        return idIpEntity;
    }
}
