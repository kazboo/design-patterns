package design.pattern;

public class ConcreteSubject extends Subject {

    private Status _status;

    public Status getStatus() {
        return _status;
    }

    public void updateStatus(Status status) {
        _status = status;
        notifyObservers();
    }
}
