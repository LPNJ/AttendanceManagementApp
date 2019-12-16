package com.example.ojttask.validator;

import org.junit.Test;

import entity.AttendanceInfo;

import validator.AttendanceValidator;
import validator.Validator;

import static entity.AttendanceType.ATTENDANCE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AttendanceValidatorTest {

    @Test
    public void validateNameNull() {
        AttendanceInfo ai = new AttendanceInfo(null,ATTENDANCE);
        Validator validater = new AttendanceValidator();
        int num = validater.validate(ai);
        assertThat(num , is(1));
    }

    @Test
    public void validateAttendNull() {
        AttendanceInfo ai = new AttendanceInfo("",null);
        Validator validater = new AttendanceValidator();
        int num = validater.validate(ai);
        assertThat(num , is(1));
    }

    @Test
    public void validateNull() {
        AttendanceInfo ai = new AttendanceInfo(null,null);
        Validator validater = new AttendanceValidator();
        int num = validater.validate(ai);
        assertThat(num , is(1));
    }

    @Test
    public void validateNameLong() {

        String str = "";
        for(int i = 0;i<=21;i++){
            str += "a";
        }

        AttendanceInfo ai = new AttendanceInfo(str,null);
        Validator validater = new AttendanceValidator();
        int num = validater.validate(ai);
        assertThat(num , is(7));
    }

}