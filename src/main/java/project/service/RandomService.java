package project.service;

import project.entity.HistoryDto;
import project.entity.TryLuckEntity;

public interface RandomService {
    HistoryDto getLuckyTry(String ipAddress, TryLuckEntity entity);
}
