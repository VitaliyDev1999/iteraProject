package project.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import project.entity.IdIpEntity;
import project.entity.RangeStringEntity;
import project.entity.Statistic;
import project.entity.StatisticRequest;
import project.repository.IpRepository;
import project.repository.StatisticRepository;
import project.repository.StatisticRequestRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceImplTest {

    private static final String TEST_IP = "000.000.000.000";
    private static final Long TEST_ID = 99999L;
    private static final IdIpEntity ID_IP_ENTITY = new IdIpEntity();
    private static final IdIpEntity NEW_IP_ENTITY = new IdIpEntity();
    private static final RangeStringEntity REQUEST = new RangeStringEntity("0-100");
    private static final StatisticRequest RESULT_REQUEST = new StatisticRequest();
    private static final Statistic MAX_STATISTIC = new Statistic();
    private static final Statistic MIN_STATISTIC = new Statistic();
    private static List<Statistic> resultList = new ArrayList<>();

    @InjectMocks
    private StatisticServiceImpl statisticService;

    @Mock
    private IpRepository ipRepository;

    @Mock
    private StatisticRepository statisticRepository;

    @Mock
    private StatisticRequestRepository statisticRequestRepository;

    @Before
    public void setUp() {
        MAX_STATISTIC.setPercent(100.00);
        MIN_STATISTIC.setPercent(0.00);
        ID_IP_ENTITY.setIp(TEST_IP);
        ID_IP_ENTITY.setId(TEST_ID);
        RESULT_REQUEST.setIp(ID_IP_ENTITY);
        resultList.add(MAX_STATISTIC);
        resultList.add(MIN_STATISTIC);

        when(ipRepository.findByIp(TEST_IP)).thenReturn(ID_IP_ENTITY);
        when(statisticRepository.findAllByIdMax(RESULT_REQUEST.getId())).thenReturn(MAX_STATISTIC);
        when(statisticRepository.findAllByIdMin(RESULT_REQUEST.getId())).thenReturn(MIN_STATISTIC);
        when(statisticRequestRepository.findByIdEquals(ID_IP_ENTITY.getId(), REQUEST.getRange())).thenReturn(RESULT_REQUEST);
        when(statisticRequestRepository.findByIdEquals(ID_IP_ENTITY.getId(), "Roulette")).thenReturn(RESULT_REQUEST);
    }

    @After
    public void tearDown() {
        resultList = new ArrayList<>();
    }

    @Test
    public void getStatisticResult() {
        List<Statistic> actualList = statisticService.getStatistic(TEST_IP);

        assertEquals(MAX_STATISTIC.getPercent(), actualList.get(0).getPercent());
        assertEquals(MIN_STATISTIC.getPercent(), actualList.get(1).getPercent());
        assertEquals(resultList.size(), actualList.size());
    }

    @Test
    public void getStatisticResultWithEmptyIp() {
        List<Statistic> actualList = statisticService.getStatistic(null);
        assertNull(actualList);
    }

    @Test
    public void getStatisticResultWithNewIp() {

        NEW_IP_ENTITY.setIp("1.1.1.1");
        NEW_IP_ENTITY.setId(7777L);

        when(ipRepository.findByIp("1.1.1.1")).thenReturn(null);
        when(ipRepository.save(any(IdIpEntity.class))).thenReturn(NEW_IP_ENTITY);
        when(statisticRequestRepository.findByIdEquals(NEW_IP_ENTITY.getId(), "Roulette")).thenReturn(RESULT_REQUEST);

        List<Statistic> actualList = statisticService.getStatistic("1.1.1.1");

        assertEquals(resultList.size(), actualList.size());
    }

}