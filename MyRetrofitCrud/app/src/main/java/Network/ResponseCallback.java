package Network;

public interface ResponseCallback<T> {
    void onSuccess(T data);
    void onError(Throwable th);
}
