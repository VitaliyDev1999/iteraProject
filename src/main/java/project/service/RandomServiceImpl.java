package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.algorithm.Operator;
import project.entity.HistoryDbEntity;
import project.entity.HistoryDto;
import project.entity.IdIpEntity;
import project.entity.TryLuckEntity;
import project.repository.HistoryRepository;
import project.repository.IpRepository;

@Service
public class RandomServiceImpl implements RandomService {

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Transactional
    @Override
    public HistoryDto getLuckyTry(String ipAddress, TryLuckEntity entity) {
        if(ipAddress != null){
            IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
            if(idIpEntity == null){
                idIpEntity = new IdIpEntity();
                idIpEntity.setIp(ipAddress);
                ipRepository.save(idIpEntity);
            }
            HistoryDto historyDto = Operator.checkWinReturnHistory(entity);

            HistoryDbEntity historyDbEntity = new HistoryDbEntity();

            historyDbEntity.setIp(idIpEntity);
            historyDbEntity.setWin(historyDto.isGame());
            historyDbEntity.setType(entity.getType().toString());
            historyDbEntity.setResult(Integer.toString(historyDto.getChoice()));
            historyDbEntity.setBet(historyDto.getBet());

            historyRepository.save(historyDbEntity);

            return historyDto;
        }
        return null;
    }
}
