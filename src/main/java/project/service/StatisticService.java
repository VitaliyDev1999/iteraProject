package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.IdIpEntity;
import project.entity.Statistic;
import project.entity.StatisticRequest;
import project.repository.IpRepository;
import project.repository.StatisticRepository;
import project.repository.StatisticRequestRepository;

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

    public List<StatisticRequest> getStatistic(String request, String ipAddress){
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if(idIpEntity == null) {
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            ipRepository.save(idIpEntity);
            StatisticRequest statisticRequest = statisticRequestRepository.findByIpEquals(idIpEntity.getId(), request);
            return new ArrayList<StatisticRequest>() {{
                new Statistic();
                new Statistic();
            }};
        }
        return null;
    }
}
