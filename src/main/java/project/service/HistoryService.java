package project.service;

import project.entity.HistoryDto;

import java.util.List;

public interface HistoryService{
    List<HistoryDto> getSeveralLastHistory(String ipAdress);
}
