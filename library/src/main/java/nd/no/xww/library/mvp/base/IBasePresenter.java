package nd.no.xww.library.mvp.base;

public interface IBasePresenter {

    void attach(IBaseView view);

    void detach();
}
