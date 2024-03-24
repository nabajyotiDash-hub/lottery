package com.poppulo.lotteryProject.dto;

import javax.validation.constraints.Positive;

/**
 * Request format for Creating & Amending tickets
 */
public class LotteryDto {

    @Positive(message = "Value must be positive")
    int numberOfLines;

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }
}
