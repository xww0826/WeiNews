package nd.no.xww.library.base.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author xww
 * @desciption :
 * @date 2019/12/3
 * @time 20:02
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public View findView(@IdRes int viewId) {
        return itemView.findViewById(viewId);
    }
}
