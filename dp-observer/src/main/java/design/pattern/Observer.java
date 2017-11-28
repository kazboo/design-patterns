package design.pattern;

/**
 * 観察者を表現するインターフェース。
 * 観察対象の状態変化を監視する。
 *
 */
@FunctionalInterface
public interface Observer {

    /**
     * 観察対象からの通知受信メソッドを定義する。
     */
    public void update();
}
