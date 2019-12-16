package Task;

import entity.EventInfo;

public interface NoticeTask {

    /**
     * メインスレッドとは異なる非同期処理（通信など）を実施し、ResultListener に対して
     * 実行結果を渡す処理。
     * @param request ログインに使うユーザー情報
     */
    void execute(EventInfo request, ResultListener listener);

}
