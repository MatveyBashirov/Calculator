package ru.neoflex.practice.database;

import jakarta.persistence.*;

@Entity
@Table(name = "RESULTS")
public class resultTable {
    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private long ID;

    @Column(name = "first_number")
    private int firstNum;

    @Column(name = "action")
    private String action;

    @Column(name = "second_number")
    private int secondNum;

    @Column(name = "result")
    private int result;

    /*Комментарий*/
    public resultTable(){

    }

    public resultTable(Integer a, String s, Integer b, int result) {
        this.firstNum = a;
        this.secondNum = b;
        this.action = s;
        this.result = result;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
