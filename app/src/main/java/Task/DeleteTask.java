package Task;

import entity.DeleteEventRequest;

public interface DeleteTask {

    /**
     * メインスレッドとは異なる非同期処理（通信など）を実施し、ResultListener に対して
     * 実行結果を渡す処理。
     * @param request イベント削除に必要な情報
     */
    void execute(DeleteEventRequest request, ResultListener listener);

}
