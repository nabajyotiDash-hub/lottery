package com.poppulo.lotteryProject.service;

import com.poppulo.lotteryProject.model.Line;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class LotteryServiceImplTest {

    @Autowired
    LotteryServiceImpl lotteryServiceImpl;


    @Test
    public void testCalculateResult_Sum2() {
        assertEquals(10, lotteryServiceImpl.calculateResult(new Line(1, 0, 1)));
    }


    @Test
    public void testCalculateResult_AllSame() {
        assertEquals(5, lotteryServiceImpl.calculateResult(new Line(2, 2, 2)));
    }

    @Test
    public void testCalculateResult_DifferentSecondThird() {
        assertEquals(1, lotteryServiceImpl.calculateResult(new Line(0, 1, 2)));
    }

    @Test
    public void testCalculateResult_AllDifferent() {
        assertEquals(0, lotteryServiceImpl.calculateResult(new Line(0, 1, 0)));
    }

}
