package Task;

/**
 * メインスレッドで受けてダイアログを出力するため。
 * @param <E> Task の実行結果の型（エラーコードであったり、結果用のエンティティであったり）
 */
public interface ResultListener<E> {
    /**
     * タスクで結果が出た際にメインスレッドで行う処理を記述する
     * @param e Task の実行結果
     */
    void onResult(E e);
}
