package nd.no.xww.weinews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import nd.no.xww.library.base.adapter.BaseRecyclerAdapter;
import nd.no.xww.library.base.adapter.BaseViewHolder;
import nd.no.xww.weinews.R;
import nd.no.xww.weinews.activity.NewsDetailActivity;
import nd.no.xww.weinews.bean.NewsBean;

/**
 * @author xww
 * @desciption :
 * @date 2020/1/7
 * @time 22:47
 */
public class NewsAdapter extends BaseRecyclerAdapter<NewsBean> {

    private static final String TAG = "NewsAdapter";

    // 多布局
    private final int ONE_PICTURE = 1;
    private final int TWO_PICTURE = 2;
    private final int THREE_PICTURE = 3;

    public NewsAdapter(Context mContext, List<NewsBean> mData) {
        super(mContext, mData);
    }

    @Override
    protected BaseViewHolder onCreateHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ONE_PICTURE:
                view = LayoutInflater.from(mContext).inflate(R.layout.recy_news_one_pic_item, parent, false);
                return new OnePictureViewHolder(view);
            case TWO_PICTURE:
                view = LayoutInflater.from(mContext).inflate(R.layout.recy_news_two_pic_item, parent, false);
                return new TwoPictureViewHolder(view);
            case THREE_PICTURE:
                view = LayoutInflater.from(mContext).inflate(R.layout.recy_news_three_pic_item, parent, false);
                return new ThreePictureViewHolder(view);
        }
        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bindData(@NonNull BaseViewHolder baseHolder, NewsBean data, int position) {
        final NewsBean item = mData.get(position);
        if (baseHolder instanceof OnePictureViewHolder) {
            OnePictureViewHolder holder = (OnePictureViewHolder) baseHolder;
            holder.title.setText(item.getTitle());
            holder.author.setText("公众号:" + item.getAuthor());
            holder.date.setText(item.getDate());
            Glide.with(mContext).load(item.getImage1()).into(holder.img1);
        } else if (baseHolder instanceof TwoPictureViewHolder) {
            TwoPictureViewHolder holder = (TwoPictureViewHolder) baseHolder;
            holder.title.setText(item.getTitle());
            holder.author.setText("公众号:" + item.getAuthor());
            holder.date.setText(item.getDate());
            Glide.with(mContext).load(item.getImage1()).into(holder.img1);
            Glide.with(mContext).load(item.getImage2()).into(holder.img2);
        } else if (baseHolder instanceof ThreePictureViewHolder) {
            ThreePictureViewHolder holder = (ThreePictureViewHolder) baseHolder;
            holder.title.setText(item.getTitle());
            holder.author.setText("公众号:" + item.getAuthor());
            holder.date.setText(item.getDate());
            Glide.with(mContext).load(item.getImage1()).into(holder.img1);
            Glide.with(mContext).load(item.getImage2()).into(holder.img2);
            Glide.with(mContext).load(item.getImage3()).into(holder.img3);
        }

        baseHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, NewsDetailActivity.class);
            intent.putExtra("news_url", data.getUrl());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getImage1() != null && mData.get(position).getImage2() != null && mData.get(position).getImage3() != null) {
            return THREE_PICTURE;
        } else if (mData.get(position).getImage1() != null && mData.get(position).getImage2() != null && mData.get(position).getImage3() == null) {
            return TWO_PICTURE;
        } else if (mData.get(position).getImage1() != null && mData.get(position).getImage2() == null && mData.get(position).getImage3() == null) {
            return ONE_PICTURE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
