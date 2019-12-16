package com.example.ojttask;

import android.os.Build;
import android.widget.Button;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import Task.LoginTask;
import Task.RegisterTask;
import Task.ResultListener;
import entity.EditableUserInfo;
import entity.UserInfo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.O)
public class RegisterActivityTest {
    private RegisterActivity mRegisterActivity;
    private RegisterTask mTask;
    /**
     * @Before がついたメソッドは、すべてのテストメソッド(@Testがついたメソッド)の
     * 実施前に呼び出される。
     */
    @Before
    public void setup() {
        // android.utils.Log を使ったログを出力する
        ShadowLog.stream = System.out;
        // Activityを生成
        mRegisterActivity = TestUtils.createActivity(RegisterActivity.class, null);
        // RegisterTaskをモックする
        mTask = Mockito.mock(RegisterTask.class);
    }

    /**
     * @After がついたメソッドは、すべてのテストメソッド(@Testがついたメソッド)の
     * 実施後に呼び出される。
     */
    @After
    public void teardown() {
    }

    /**
     * 登録成功でダイアログ表示の確認
     */
    @Test
    public void registerSuccess() {
        // ●setup(準備)
        // IDとPassを設定
        enterIdAndPass("aaaaaaaa", "bbbbbbbb","bbbbbbbb");
        // LoginTaskで必ず成功が返るように設定
        RegisterTask task = createTask( 0);
        mRegisterActivity.setTask(task);
        Button registerButton = mRegisterActivity.findViewById(R.id.registration);

        // ●execute(テストの実施)
        // 登録ボタンを押下
        registerButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.decision)));
    }

    /**
     * 登録失敗でダイアログ表示の確認
     */
    @Test
    public void registerFailedByEmpty() {
        // ●setup(準備)
        // IDとPassを設定
        enterIdAndPass("", "","");
        // LoginTaskで必ず成功が返るように設定
        RegisterTask task = createTask( 0);
        mRegisterActivity.setTask(task);
        Button registerButton = mRegisterActivity.findViewById(R.id.registration);

        // ●execute(テストの実施)
        // 登録ボタンを押下
        registerButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.login_error)));
    }

    /**
     * 登録失敗でダイアログ表示の確認
     */
//    @Test
//    public void registerFailedByIdInputOver() {
//        // ●setup(準備)
//        // IDとPassを設定
//        String id = new String();
//        for(int i=0;i<=200;i++){
//            id += "a";
//        }
//
//        enterIdAndPass(id, "bbbbbbbb","bbbbbbbb");
//        // LoginTaskで必ず成功が返るように設定
//        RegisterTask task = createTask( 1);
//        mRegisterActivity.setTask(task);
//        Button registerButton = mRegisterActivity.findViewById(R.id.registration);
//
//        // ●execute(テストの実施)
//        // 登録ボタンを押下
//        registerButton.performClick();
//
//        // ●verify(検証)
//        String message = TestUtils.getLatestAlertDialogMessage();
//        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.id_input_long)));
//    }

    /**
     * 新規アカウント作成失敗でダイアログ表示の確認
     */
        @Test
        public void registerFailedByIdInputShort() {
            // ●setup(準備)
            // IDとPassを設定

            enterIdAndPass("aaaaa", "bbbbbbbb","bbbbbbbb");
            // LoginTaskで必ず成功が返るように設定
            RegisterTask task = createTask( 1);
            mRegisterActivity.setTask(task);
            Button registerButton = mRegisterActivity.findViewById(R.id.registration);

            // ●execute(テストの実施)
            // 登録ボタンを押下
            registerButton.performClick();

            // ●verify(検証)
            String message = TestUtils.getLatestAlertDialogMessage();
            assertThat(message, is(RuntimeEnvironment.application.getString(R.string.id_input_short)));
        }

    /**
     * 登録失敗でダイアログ表示の確認
     */
    @Test
    public void registerFailedByPassInputShort() {
        // ●setup(準備)
        // IDとPassを設定

        enterIdAndPass("aaaaaa", "bbbbbbb","bbbbbbb");
        // LoginTaskで必ず成功が返るように設定
        RegisterTask task = createTask( 1);
        mRegisterActivity.setTask(task);
        Button registerButton = mRegisterActivity.findViewById(R.id.registration);

        // ●execute(テストの実施)
        // 登録ボタンを押下
        registerButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.pass_input_short)));
    }

    /**
     * 登録失敗でダイアログ表示の確認
     */
    @Test
    public void registerFailedByPassSpecial() {
        // ●setup(準備)
        // IDとPassを設定

        enterIdAndPass("aaaaaa", "bbbbbbb","bbbbbbb");
        // LoginTaskで必ず成功が返るように設定
        RegisterTask task = createTask( 1);
        mRegisterActivity.setTask(task);
        Button registerButton = mRegisterActivity.findViewById(R.id.registration);

        // ●execute(テストの実施)
        // 登録ボタンを押下
        registerButton.performClick();

        // ●verify(検証)
        String message = TestUtils.getLatestAlertDialogMessage();
        assertThat(message, is(RuntimeEnvironment.application.getString(R.string.pass_input_short)));
    }



    /**
     * ログインタスクの結果を設定します。
     * @param result
     * @return
     */
    private RegisterTask createTask(final int result) {
        return new RegisterTask() {
            @Override
            public void execute(UserInfo userInfo, ResultListener listener) {
                listener.onResult(result);
            }
        };
    }

    /**
     * 引数に指定したId, Passを設定します。
     * @param id
     * @param pass
     * @param confirmationPass
     */
    private void enterIdAndPass(String id, String pass ,String confirmationPass) {
        EditText idText = mRegisterActivity.findViewById(R.id.register_ID);
        EditText passText = mRegisterActivity.findViewById(R.id.register_PASS);
        EditText comfirmationPassText = mRegisterActivity.findViewById(R.id.register_COMFIRMATION_PASS);
        idText.setText(id);
        passText.setText(pass);
        comfirmationPassText.setText(confirmationPass);
    }

}