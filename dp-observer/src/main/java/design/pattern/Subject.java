package design.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 観察対象クラス。
 * 自分がどのObserverに観察されているかは意識しない。
 *
 */
public abstract class Subject {

    private List<Observer> _observerList = new ArrayList<>();

    /**
     * 観察者の追加
     * @param observer
     */
    public void registerObserver(Observer observer) {
        _observerList.add(observer);
    }

    /**
     * 観察者の削除
     * @param observer
     */
    public void removeObserver(Observer observer) {
        _observerList.remove(observer);
    }

    /**
     * 観察者への通知
     */
    void notifyObservers() {
        for(Observer observer : _observerList) {
            observer.update();
        }
    }
}
