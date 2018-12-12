package project.service;

import project.entity.HistoryDbEntity;

import java.util.List;

public interface IHistoryService {
    List<HistoryDbEntity> getSeveralLastHistory(String ipAdress);
}
