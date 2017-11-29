package design.pattern;

import static design.pattern.Status.*;
import static java.lang.String.*;

import org.junit.Test;

/**
 * 抽象クラスSubjectとObserver間の依存関係と、
 * 具象クラスであるConcreteSubjectとConcreteObserver間の依存関係が逆転している。
 *
 * 変更通知のタイミングはConcreteSubjectが握っているが、
 * 変更をどう扱うかという解釈はConcreteObserver側に任されている。
 *
 * ConcreteObserverは変更通知を無視することもできる。
 * また、別の第三者に通知することも可能。
 *
 * MVCフレームワークでは、ConcreteSubject = Model,
 * ConcreteObserver = View としてこのパターンが利用される。
 *
 * ModelはViewについての知識を持たず、
 * 更新があったタイミングのみをupdate()によって通知する
 *
 * 逆にViewはModelについての知識を持ち、
 * 更新の内容を解釈することを任されている。
 *
 * Observerパターンの特徴は「逆転」にある。
 * 1. 抽象クラス間の依存関係と、具体クラスの依存関係の逆転
 * 2. タイミングの責任者と、解釈の責任者の逆転
 * 3. 具体クラス間での呼び出し方向と依存関係の逆転
 *
 * [デッドロック問題]
 *
 * マルチスレッド環境でObserverパターンを使用する場合は、
 * デッドロックの可能性が常にある。逆転関係がその可能性を示唆している。
 *
 * [デッドロック回避]
 *
 * フレームワーク実装者への指針
 * 1. SubjectのnotifyObservers()はsynchronizedメソッドにしてはいけない。
 * また、notifyObservers()中のObserver#update()呼び出しは
 * synchronizedブロックの中で行ってはいけない。
 *
 * 2. ほかのメソッドでも、 Subject -> Observerへの呼び出しは
 * ロックを取得中に行ってはならない。
 *
 * 3. Observer#update()中のSubjectへの呼び出しは、
 * synchronizedブロック/メソッド中に行っても構わない。
 *
 * アプリケーション実装者への指針
 * 1. ConcreteSubjectは、synchronizedブロック/メソッドの中でnotify()を
 * 呼び出してはいけない。
 *
 * 2. ConcreteObserverは、synchronizedブロック/メソッドの中で
 * ConcreteSubjectのsynchronizedメソッドを呼び出してもよい。
 *
 */
public class ConcreteSubjectTest {

    @Test
    public void test() {

        // 1. 観察対象の生成
        ConcreteSubject subject = new ConcreteSubject();

        // 2. 観察者の生成と登録
        subject.registerObserver(() -> {
            Status status = subject.getStatus();
            // ログファイル出力処理
            log(format("[INFO] status changed to %s", status));
        });

        subject.registerObserver(() -> {
            Status status = subject.getStatus();
            // データベース更新処理
            updateRecord(status);
        });

        // 3. ClientがConcreteSubjectの状態を変更
        subject.updateStatus(COMPLETE);
        subject.updateStatus(CLIENT_ERROR);
    }

    private void log(String msg) {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println(format("%s - %s", currentThreadName, msg));
    }

    private void updateRecord(Status status) {
        String msg = format("update status to %s", status);
        String currentThreadName = Thread.currentThread().getName();

        System.out.println(format("%s - %s", currentThreadName, msg));
    }

}
