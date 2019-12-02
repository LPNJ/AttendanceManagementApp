package validator;

/**
 * データのバリデーションを行うクラスが実装すべきI/F
 * @param <T> バリデーションするデータの型
 */
public interface Validator<T> {
    /** 成功コード */
    int SUCCESS = 0;
    /**
     * 渡されたデータのバリデーションを行います。
     * @param data バリデーションしたいデータ
     * @return バリデーション結果をリターンコードで返す。成功:0 それ以外：不正
     */
    int validate(T data);
}
