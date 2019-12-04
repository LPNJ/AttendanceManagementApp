package Task;

import entity.UserInfo;

public interface LogoutTask {
    /**
     * メインスレッドとは異なる非同期処理（通信など）を実施し、ResultListener に対して
     * 実行結果を渡す処理。
     * @param userInfo ログインに使うユーザー情報
     */
    void execute(UserInfo userInfo, ResultListener listener);
}
