package Task;

public interface RefarenceTask {

    /**
     * メインスレッドとは異なる非同期処理（通信など）を実施し、ResultListener に対して
     * 実行結果を渡す処理。
     * @param userId ログインに使うユーザー情報
     */
    void execute(String userId, ResultListener listener);

}
