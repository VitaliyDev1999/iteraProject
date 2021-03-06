package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.algorithm.Operator;
import project.entity.*;
import project.repository.HistoryRepository;
import project.repository.IpRepository;
import project.repository.StatisticRepository;
import project.repository.StatisticRequestRepository;
import project.service.RandomService;
import project.utils.ParseRange;
import project.utils.RouletteNumList;

import java.util.List;

@Service
public class RandomServiceImpl implements RandomService {

    private static final String ROULETTE_STRING = "Roulette";
    private static final String WIN = "Win";
    private static final String RANGE = "0-36";

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
            IdIpEntity idIpEntity = findOrSaveIpEntity(ipAddress, ROULETTE_STRING);
            HistoryDto historyDto = Operator.checkWinReturnHistoryRoulette(entity);

            HistoryDbEntity historyDbEntity = new HistoryDbEntity();

            historyDbEntity.setIp(idIpEntity);
            historyDbEntity.setRange(ROULETTE_STRING);
            historyDbEntity.setWin(historyDto.getGame().compareTo(WIN) == 0);
            historyDbEntity.setType(entity.getType().toString());
            historyDbEntity.setResult(Integer.toString(historyDto.getChoice()));
            historyDbEntity.setBet(historyDto.getBet());
            historyDto.setRange(ROULETTE_STRING);

            return processHistoryAndStatistic(idIpEntity, historyDto, historyDbEntity);
        }
        return null;
    }

    @Transactional
    @Override
    public HistoryDto getLuckyTryRange(String ipAddress, RangeLuckEntity rangeLuckEntity) {
        if (ipAddress != null) {

            rangeLuckEntity.setBet();
            rangeLuckEntity.setRange();

            HistoryDto historyDto = Operator.checkWinReturnHistoryRange(rangeLuckEntity);
            HistoryDbEntity historyDbEntity = new HistoryDbEntity();

            IdIpEntity idIpEntity = findOrSaveIpEntity(ipAddress, historyDto.getRange());
            historyDbEntity.setIp(idIpEntity);
            historyDbEntity.setRange(historyDto.getRange());
            historyDbEntity.setWin(historyDto.getGame().compareTo(WIN) == 0);
            historyDbEntity.setType(Type.RANGE.toString());
            historyDbEntity.setResult(Integer.toString(historyDto.getChoice()));
            historyDbEntity.setBet(historyDto.getBet());

            return processHistoryAndStatistic(idIpEntity, historyDto, historyDbEntity);
        }
        return null;
    }

    private StatisticRequest createStatistic(IdIpEntity idIpEntity, RangeStringEntity request) {
        StatisticRequest statisticRequest = statisticRequestRepository.findByIdEquals(idIpEntity.getId(), request.getRange());
        if (statisticRequest == null) {
            List<Statistic> statistics;
            statisticRequest = new StatisticRequest();
            statisticRequest.setIp(idIpEntity);
            statisticRequest.setCount(RouletteNumList.randomCount);
            statisticRequest.setRange(request.getRange());
            statisticRequest = statisticRequestRepository.save(statisticRequest);
            if (statisticRequest.getRange().compareTo(ROULETTE_STRING) == 0){
                statistics = ParseRange.fillStatistic(ParseRange.parseRange(RANGE), statisticRequest);
            } else {
                statistics = ParseRange.fillStatistic(ParseRange.parseRange(statisticRequest.getRange()), statisticRequest);
            }
            RouletteNumList.randomNumbersInRange(statistics);
            statisticRepository.save(statistics);
        }
        return statisticRequest;
    }

    private void addRandomAndUpdateStatistic(Integer newNumber, StatisticRequest statisticRequest) {
        List<Statistic> statistics = statisticRepository.findAllByIpAndStatisticRequest(statisticRequest.getId());
        statisticRequest.setCount(statisticRequest.getCount() + 1);
        for (int j = 0; j < statistics.size(); j++) {
            if (statistics.get(j).getValue().compareTo(newNumber) == 0) {
                statistics.get(j).setCount(statistics.get(j).getCount() + 1);
                j = statistics.size();
            }
            for (Statistic statistic : statistics) {
                statistic.calculatePercent(statisticRequest.getCount());
                statisticRepository.save(statistic);
            }
        }
    }

    private HistoryDto processHistoryAndStatistic(IdIpEntity idIpEntity, HistoryDto historyDto, HistoryDbEntity historyDbEntity) {
        RangeStringEntity rangeStringEntity = new RangeStringEntity(historyDto.getRange());

        historyRepository.save(historyDbEntity);
        StatisticRequest statisticRequest = createStatistic(idIpEntity,rangeStringEntity);
        addRandomAndUpdateStatistic(historyDto.getChoice(), statisticRequest);
        return historyDto;
    }

    private IdIpEntity findOrSaveIpEntity(String ipAddress, String range) {
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if (idIpEntity == null) {
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
        }
        idIpEntity.setLastRange(range);
        idIpEntity = ipRepository.save(idIpEntity);
        return idIpEntity;
    }
}
