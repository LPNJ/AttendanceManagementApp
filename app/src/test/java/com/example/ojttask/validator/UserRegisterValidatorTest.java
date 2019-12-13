package com.example.ojttask.validator;

import org.junit.Test;

import entity.EditableUserInfo;
import validator.UserRegisterValidator;
import validator.Validator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static validator.UserRegisterValidator.ERROR_ID_TOO_LONG;
import static validator.UserRegisterValidator.ERROR_ID_TOO_SHORT;
import static validator.UserRegisterValidator.ERROR_NOT_INPUT;
import static validator.UserRegisterValidator.ERROR_PASS_NOT_MATCH;
import static validator.UserRegisterValidator.ERROR_PASS_TOO_LONG;
import static validator.UserRegisterValidator.ERROR_PASS_TOO_SHORT;
import static validator.Validator.SUCCESS;

public class UserRegisterValidatorTest {

    @Test
    public void validateIdNull() {
        EditableUserInfo ui = new EditableUserInfo(null,"komikomi","komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validatePassNull() {
        EditableUserInfo ui = new EditableUserInfo("komiyama",null,"komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validateConfirmationPassNull() {
        EditableUserInfo ui = new EditableUserInfo("komiyama","komikomi",null);
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validateNull() {
        EditableUserInfo ui = new EditableUserInfo(null,null,null);
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validateIdEmpty() {
        EditableUserInfo ui = new EditableUserInfo("","komikomi","komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validatePassEmpty() {
        EditableUserInfo ui = new EditableUserInfo("komiyama",null,"komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validateConfirmationPassEmpty() {
        EditableUserInfo ui = new EditableUserInfo("komiyama","komikomi",null);
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(1));
    }

    @Test
    public void validateNormal() {
        EditableUserInfo ui = new EditableUserInfo("komiyama","komikomi","komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(SUCCESS));
    }

    @Test
    public void validateIdInputTooOver() {
        String str = "";
        for(int i = 0;i<=129;i++){
            str += "a";
        }

        EditableUserInfo ui = new EditableUserInfo(str,"komikomi","komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(6));
    }

    @Test
    public void validatePassInputTooOver() {
        String str = "";
        for(int i = 0;i<=129;i++){
            str += "a";
        }

        EditableUserInfo ui = new EditableUserInfo("komiyama",str,"komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(7));
    }

    @Test
    public void validateIdInputTooShort() {
        EditableUserInfo ui = new EditableUserInfo("komi","komikomi","komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(11));
    }

    @Test
    public void validatePassInputTooShort() {
        EditableUserInfo ui = new EditableUserInfo("komiyama","komi","komikomi");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(12));
    }

    @Test
    public void validatePassNotMatch() {
        EditableUserInfo ui = new EditableUserInfo("komiyama","komikomi","komihiro");
        Validator validater = new UserRegisterValidator();
        int num = validater.validate(ui);
        assertThat(num , is(13));
    }
}