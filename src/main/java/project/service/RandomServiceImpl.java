package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.algorithm.Operator;
import project.entity.*;
import project.repository.HistoryRepository;
import project.repository.IpRepository;
import project.repository.StatisticRepository;
import project.repository.StatisticRequestRepository;
import project.utils.ParseRange;
import project.utils.RuletteNumList;

import java.util.ArrayList;
import java.util.List;

@Service
public class RandomServiceImpl implements RandomService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private StatisticRepository statisticRepository;

    @Autowired
    private StatisticRequestRepository statisticRequestRepository;

    @Transactional
    @Override
    public HistoryDto getLuckyTry(String ipAddress, TryLuckEntity entity) {
        if (ipAddress != null) {
            IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
            if (idIpEntity == null) {
                idIpEntity = new IdIpEntity();
                idIpEntity.setIp(ipAddress);
                ipRepository.save(idIpEntity);
            }
            HistoryDto historyDto = Operator.checkWinReturnHistoryRoulette(entity);

            HistoryDbEntity historyDbEntity = new HistoryDbEntity();

            historyDbEntity.setIp(idIpEntity);
            historyDbEntity.setRange("Roulette");
            historyDbEntity.setWin(historyDto.getGame().compareTo("Win") == 0 ? true : false);
            historyDbEntity.setType(entity.getType().toString());
            historyDbEntity.setResult(Integer.toString(historyDto.getChoice()));
            historyDbEntity.setBet(historyDto.getBet());
            historyDto.setRange("Roulette");

            historyRepository.save(historyDbEntity);

            createStatistic(idIpEntity, new RangeStringEntity(historyDto.getRange()));

            addRandomAndUpdateStatistic(historyDto.getChoice(), idIpEntity, new RangeStringEntity(historyDto.getRange()));

            return historyDto;
        }
        return null;
    }

    @Transactional
    @Override
    public HistoryDto getLuckyTry(String ipAddress, RangeLuckEntity rangeLuckEntity) {
        if (ipAddress != null) {
            IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
            if (idIpEntity == null) {
                idIpEntity = new IdIpEntity();
                idIpEntity.setIp(ipAddress);
                ipRepository.save(idIpEntity);
            }
            rangeLuckEntity.setBet();
            rangeLuckEntity.setRange();
            HistoryDto historyDto = Operator.checkWinReturnHistoryRange(rangeLuckEntity);

            HistoryDbEntity historyDbEntity = new HistoryDbEntity();

            historyDbEntity.setIp(idIpEntity);
            historyDbEntity.setRange(historyDto.getRange());
            historyDbEntity.setWin(historyDto.getGame().compareTo("Win") == 0 ? true : false);
            historyDbEntity.setType(Type.RANGE.toString());
            historyDbEntity.setResult(Integer.toString(historyDto.getChoice()));
            historyDbEntity.setBet(historyDto.getBet());

            historyRepository.save(historyDbEntity);

            createStatistic(idIpEntity, new RangeStringEntity(historyDto.getRange()));

            addRandomAndUpdateStatistic(historyDto.getChoice(), idIpEntity, new RangeStringEntity(historyDto.getRange()));

            return historyDto;
        }
        return null;
    }

    private void createStatistic(IdIpEntity idIpEntity, RangeStringEntity request) {
        StatisticRequest statisticRequest = statisticRequestRepository.findByIpEquals(idIpEntity.getId(), request.getRange());
        if (statisticRequest == null) {
            List<Statistic> statistics;
            statisticRequest = new StatisticRequest();
            statisticRequest.setIp(idIpEntity);
            statisticRequest.setCount(RuletteNumList.randomCount);
            statisticRequest.setRange(request.getRange());
            statisticRequestRepository.save(statisticRequest);
            if (statisticRequest.getRange().compareTo("Roulette") == 0)
                statistics = ParseRange.fillStatistic(ParseRange.parseRange("0-36"), statisticRequest);
            else
                statistics = ParseRange.fillStatistic(ParseRange.parseRange(statisticRequest.getRange()), statisticRequest);
            RuletteNumList.randomNumbersInRange(statistics);
            for (Statistic statistic :
                    statistics) {
                statisticRepository.save(statistic);
            }
        }
    }

    private void addRandomAndUpdateStatistic(Integer newNumber, IdIpEntity idIpEntity, RangeStringEntity request) {
        StatisticRequest statisticRequest = statisticRequestRepository.findByIpEquals(idIpEntity.getId(), request.getRange());
        List<Statistic> statistics;
        statistics = statisticRepository.findAllByIpAndStatisticRequest(statisticRequest.getId());
        statisticRequest.setCount(statisticRequest.getCount() + 1);
        for (int j = 0; j < statistics.size(); j++) {
            if (statistics.get(j).getValue().compareTo(newNumber) == 0) {
                statistics.get(j).setCount(statistics.get(j).getCount() + 1);
                j = statistics.size();
            }
            for (Statistic statistic :
                    statistics) {
                statistic.calculatePercent(statisticRequest.getCount());
                statisticRepository.save(statistic);
            }
        }
    }
}
