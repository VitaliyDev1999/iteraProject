package project.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.HistoryDbEntity;
import project.entity.IdIpEntity;
import project.repository.HistoryRepository;
import project.repository.IpRepository;

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
    public List<HistoryDbEntity> getSeveralLastHistory(String ipAddress){
        if(ipAddress != null) {
            IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
            List<HistoryDbEntity> historyDB = historyRepository.findAllByIpEqualsOrderById(idIpEntity.getId());
            return historyDB;
        }
        return null;
    }
}
