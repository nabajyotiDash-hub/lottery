package com.poppulo.lotteryProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Random;

@Entity
public class Line implements Comparable<Line>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    public Line(){
        this.number1=new Random().nextInt(3);
        this.number2=new Random().nextInt(3);
        this.number3=new Random().nextInt(3);
    }
    public Line(int number1, int number2, int number3){
        this.number1=number1;
        this.number2=number2;
        this.number3=number3;
    }


    @Column
    private int number1;
    @Column
    private int number2;
    @Column
    private int number3;
    private int result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LotteryLine{" +
                "id=" + id +
                ", number1=" + number1 +
                ", number2=" + number2 +
                ", number3=" + number3 +
                ", result=" + result +
                '}';
    }

    @Override
    public int compareTo(Line line) {
        if(this.result == line.result){
            return 0;
        } else if(this.result > line.result){
            return -1;
        } else {
            return 1;
        }
    }
}
