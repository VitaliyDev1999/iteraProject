package project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.IdIpEntity;
import project.repository.IpRepository;
import project.service.IpService;

@Service
public class IpServiceImpl implements IpService {

    private static final String RANGE = "Roulette";

    @Autowired
    private IpRepository ipRepository;

    public IdIpEntity saveIp(String ipAddress){
        IdIpEntity idIpEntity = ipRepository.findByIp(ipAddress);
        if(idIpEntity == null){
            idIpEntity = new IdIpEntity();
            idIpEntity.setIp(ipAddress);
            idIpEntity.setLastRange(RANGE);
            idIpEntity = ipRepository.save(idIpEntity);
        }
        return idIpEntity;
    }
}
