package project.service;

import project.entity.HistoryEntity;
import project.entity.TryLuckEntity;

public interface RandomService {
    HistoryEntity getLuckyTry(String ipAddress, TryLuckEntity entity);
}
