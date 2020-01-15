package nd.no.xww.library.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author xww
 * @desciption :
 * @date 2019/12/3
 * @time 20:01
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mLayoutInflater;
    private onItemClickListener onItemClickListener;
    private onItemLongClickListener onItemLongClickListener;

    public BaseRecyclerAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateHolder(parent, viewType);
    }

    protected abstract BaseViewHolder onCreateHolder(@NonNull ViewGroup viewGroup, int viewType);


    protected void bindData(@NonNull BaseViewHolder baseHolder, T data, final int position) {
        baseHolder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(position);
        });
        baseHolder.itemView.setOnLongClickListener(v -> {
            if (onItemLongClickListener != null)
                onItemLongClickListener.onItemLongClick(position);
            return false;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        bindData(viewHolder, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addData(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void refreshData(List<T> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public interface onItemLongClickListener {
        void onItemLongClick(int position);
    }

    public void setOnItemClickListener(BaseRecyclerAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(BaseRecyclerAdapter.onItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
