package nd.no.xww.weinews.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nd.no.xww.library.base.adapter.BaseViewHolder;
import nd.no.xww.weinews.R;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/8
 * @time 13:55
 */
public class OnePictureViewHolder extends BaseViewHolder {

    TextView title;
    TextView author;
    TextView date;
    ImageView img1;

    public OnePictureViewHolder(View view) {
        super(view);
        title = view.findViewById(R.id.rv_item_news_title);
        author = view.findViewById(R.id.rv_item_news_author);
        date = view.findViewById(R.id.rv_item_news_date);
        img1 = view.findViewById(R.id.rv_item_news_img_1);
    }
}
