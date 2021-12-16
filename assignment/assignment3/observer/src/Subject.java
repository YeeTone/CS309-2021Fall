public interface Subject<T> {
    public void registerObserver(T t);

    public void removeObserver(T t);

    public void notifyObservers(char keyChar);

    public void notifyObservers();
}
