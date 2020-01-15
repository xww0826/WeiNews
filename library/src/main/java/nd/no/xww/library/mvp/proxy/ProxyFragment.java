package nd.no.xww.library.mvp.proxy;


import nd.no.xww.library.mvp.base.IBaseView;

public class ProxyFragment<V extends IBaseView> extends ProxyImpl {
    public ProxyFragment(V view) {
        super(view);
    }
}
