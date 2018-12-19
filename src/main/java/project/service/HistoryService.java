package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.HistoryDbEntity;
import project.entity.HistoryDto;
import project.entity.IdIpEntity;
import project.repository.HistoryRepository;
import project.repository.IpRepository;
import project.utils.RuletteNumList;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService implements IHistoryService{

    @Autowired
    private IpRepository ipRepository;

    @Autowired
    private HistoryRepository historyRepository;

    public void saveHistory(HistoryDbEntity historyDbEntity){
        historyRepository.save(historyDbEntity);
    }

    @Transactional
    @Override
    public List<HistoryDto> getSeveralLastHistory(String ipAddress){
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if(idIpEntity == null){
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            ipRepository.save(idIpEntity);
        }
        List<HistoryDbEntity> historyDbEntities = historyRepository.findAllByIpEqualsOrderById(ipRepository.findByIp(ipAddress).getId());
        List<HistoryDto> historyDtos = new ArrayList<>();
        for (HistoryDbEntity history:
             historyDbEntities) {
            HistoryDto dto = new HistoryDto(history.getBet(), history.getRange(), Integer.parseInt(history.getResult()), history.isWin() ? "Win" : "Lose", RuletteNumList.getNumber(Integer.parseInt(history.getResult())).getColor());
            historyDtos.add(dto);
        }
        return historyDtos;
    }
}
