package com.example.ojttask.validator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import entity.CandidateDate;
import entity.EventInfo;
import validator.EventCreateValidator;
import validator.Validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EventCreateValidatorTest {

    @Test
    public void validateNameNull() {
        List<CandidateDate> list = new ArrayList<>();
        list.add(new CandidateDate("12/12","19:00"));
        EventInfo ei = new EventInfo(null,"開催は新横浜","0011",list);
        Validator validater = new EventCreateValidator();
        int num = validater.validate(ei);
        assertThat(num , is(1));
    }

    @Test
    public void validateNameEmpty() {
        EventInfo ei = new EventInfo("", "開催は新横浜", "0011", null);
        Validator validater = new EventCreateValidator();
        int num = validater.validate(ei);
        assertThat(num, is(1));
    }

    @Test
    public void validateCandidateDatesEmpty() {
        List<CandidateDate> list = new ArrayList<>();
        EventInfo ei = new EventInfo("忘年会", "開催は新横浜", "0011",list);
        Validator validater = new EventCreateValidator();
        int num = validater.validate(ei);
        assertThat(num, is(1));
    }

    @Test
    public void validateEmpty() {
        List<CandidateDate> list = new ArrayList<>();
        EventInfo ei = new EventInfo("", "開催は新横浜", "0011",list);
        Validator validater = new EventCreateValidator();
        int num = validater.validate(ei);
        assertThat(num, is(1));
    }

    @Test
    public void validateNameTooLong() {
        List<CandidateDate> list = new ArrayList<>();
        list.add(new CandidateDate("12/12","19:00"));

        String str = "";
        for(int i = 0;i<=50;i++){
            str += "a";
        }

        EventInfo ei = new EventInfo(str, "開催は新横浜", "0011",list);
        Validator validater = new EventCreateValidator();
        int num = validater.validate(ei);
        assertThat(num, is(8));
    }

    @Test
    public void validateDetailsTooLong() {
        List<CandidateDate> list = new ArrayList<>();
        list.add(new CandidateDate("12/12","19:00"));

        String str = "";
        for(int i = 0;i<=1001;i++){
            str += "a";
        }

        EventInfo ei = new EventInfo("忘年会", str ,"0011",list);
        Validator validater = new EventCreateValidator();
        int num = validater.validate(ei);
        assertThat(num, is(9));
    }



}