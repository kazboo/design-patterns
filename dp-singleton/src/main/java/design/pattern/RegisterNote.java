package design.pattern;

public class RegisterNote {

    /**
     * クラスが初期化されるタイミングはJavaの言語仕様で定義されている。
     * 初めてインスタンス化されたとき、初めてstaticメソッドが呼び出されたとき等。
     *
     * この場合、「初めてstaticメソッドが呼び出されたとき」初めてRegisterNoteクラス
     * が初期化され、その際一度だけインスタンスが生成される。
     */
    private static RegisterNote _registerNote = new RegisterNote();

    private RegisterNote() {
        // private constructor
    }

    public static RegisterNote getInstance() {
        return _registerNote;
    }
}
