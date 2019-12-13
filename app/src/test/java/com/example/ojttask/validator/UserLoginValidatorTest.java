package com.example.ojttask.validator;

import org.junit.Test;

import entity.UserInfo;
import validator.UserLoginValidator;
import validator.Validator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static validator.Validator.SUCCESS;

public class UserLoginValidatorTest {

    @Test
    public void validateIdNull() {

        UserInfo ui = new UserInfo(null,"komikomi");
        Validator validater = new UserLoginValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validatePassNull() {

        UserInfo ui = new UserInfo("komiyama",null);
        Validator validater = new UserLoginValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validateNull() {

        UserInfo ui = new UserInfo(null,null);
        Validator validater = new UserLoginValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validateNormal() {

        UserInfo ui = new UserInfo("komiyama","komikomi");
        Validator validater = new UserLoginValidator();
        int num = validater.validate(ui);
        assertThat(num , is(SUCCESS));
    }

    @Test
    public void validateIdEmpty() {

        UserInfo ui = new UserInfo("","komikomi");
        Validator validater = new UserLoginValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validatePassEmpty() {

        UserInfo ui = new UserInfo("komiyama","");
        Validator validater = new UserLoginValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validateEmpty() {

        UserInfo ui = new UserInfo("","");
        Validator validater = new UserLoginValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }



}