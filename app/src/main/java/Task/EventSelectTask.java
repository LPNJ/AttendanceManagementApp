package Task;

import entity.EditableUserInfo;
import entity.UserID;
import entity.UserInfo;

public interface EventSelectTask {

    /**
     * メインスレッドとは異なる非同期処理（通信など）を実施し、ResultListener に対して
     * 実行結果を渡す処理。
     * @param userId ログインに使うユーザー情報
     */
    void execute(String userId, ResultListener listener);

}
