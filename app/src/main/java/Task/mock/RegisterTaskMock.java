package Task.mock;

import Task.RegisterTask;
import Task.ResultListener;
import entity.EditableUserInfo;
import entity.UserInfo;

/**
 * 画面遷移の動作を確認するための仮実装（テストにも流用可）
 */
public class RegisterTaskMock implements RegisterTask {
    @Override
    public void execute(UserInfo userInfo, ResultListener resultListener) {
        if("failure".equals(userInfo.getUserId())) {
            resultListener.onResult(1);
        } else {
            resultListener.onResult(0);
        }
    }
}
