package project.service;

import project.entity.HistoryDto;

import java.util.List;

public interface IHistoryService {
    List<HistoryDto> getSeveralLastHistory(String ipAdress);
}
