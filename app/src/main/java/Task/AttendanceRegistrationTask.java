package Task;

import entity.EditableUserInfo;

public interface AttendanceRegistrationTask {

    /**
     * メインスレッドとは異なる非同期処理（通信など）を実施し、ResultListener に対して
     * 実行結果を渡す処理。
     * @param userInfo ログインに使うユーザー情報
     * @param resultListener ログインタスクの結果を受けて処理を実行するリスナー（画面）
     */
    void execute(EditableUserInfo userInfo, ResultListener resultListener);

}
