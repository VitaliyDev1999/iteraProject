package project.service;

import project.entity.IdIpEntity;

public interface IpService {
    IdIpEntity saveIp(String ipAddress);
}
