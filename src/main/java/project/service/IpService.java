package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.IdIpEntity;
import project.repository.IpRepository;

@Service
public class IpService {

    @Autowired
    private IpRepository ipRepository;

    public void saveIp(IdIpEntity idIpEntity){
        ipRepository.save(idIpEntity);
    }

    public IdIpEntity getIP(String ipAdress){
        return ipRepository.findByIp(ipAdress);
    }
}
