package design.pattern;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class RegisterNoteTest {

    @Test
    public void getInstance_取得したインスタンスが等しい() {
        // setup
        RegisterNote note1 = RegisterNote.getInstance();
        RegisterNote note2 = RegisterNote.getInstance();

        // verify
        assertThat(note1, is(note2));
        // 2つのオブジェクトがequals()メソッドで等しいと判定された場合、
        // 両者のハッシュコードは等しくなければならない。
        assertThat(note1.hashCode(), is(note2.hashCode()));
    }
}
