package nd.no.xww.library.mvp.proxy;


import nd.no.xww.library.mvp.base.IBaseView;

public class ProxyActivity<V extends IBaseView> extends ProxyImpl {
    public ProxyActivity(V view) {
        super(view);
    }
}
