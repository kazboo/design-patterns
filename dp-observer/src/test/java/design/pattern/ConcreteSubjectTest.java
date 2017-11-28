package design.pattern;

import static design.pattern.Status.*;
import static java.lang.String.*;

import org.junit.Test;

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
