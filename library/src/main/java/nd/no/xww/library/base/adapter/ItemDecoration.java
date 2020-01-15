package nd.no.xww.library.base.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author xww
 * @desciption : RecyclerView 添加分割线
 * @date 2019/12/3
 * @time 15:16
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {

    //网格布局
    private final int GRID_VERTICAL = 3;
    private final int GRID_HORIZONTAL = 4;
    private int mOrientation;

    private Drawable mDivider;

    public ItemDecoration(Context context, int orientation) {
        this.mOrientation = orientation;
        setDividerStyle(context);
    }

    public ItemDecoration(Context context, int orientation, int columnCount) {
        this.mOrientation = orientation;
        setDividerStyle(context);
        setGridOrientation();
    }

    private void setDividerStyle(Context context) {
        // 添加系统默认的分割线样式
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawLineDivider(c, parent);
    }

    private void drawLineDivider(Canvas canvas, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        View itemView = parent.getChildAt(0);
        if (itemView != null && childCount > 1) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();

            if (mOrientation == LinearLayoutManager.VERTICAL) {
                int left, top, right, bottom;
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
                for (int i = 0; i < childCount; i++) {
                    View childView = parent.getChildAt(i);
                    top = childView.getBottom() + params.bottomMargin;
                    bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
            } else if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                int left, top, right, bottom;
                top = parent.getPaddingTop();
                bottom = parent.getHeight() - parent.getPaddingBottom();
                for (int i = 0; i < childCount; i++) {
                    View childView = parent.getChildAt(i);
                    left = childView.getRight() + params.rightMargin;
                    right = left + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(canvas);
                }
            }
        }
    }

    private void setGridOrientation() {
        if (mOrientation == GridLayoutManager.VERTICAL) {
            mOrientation = GRID_VERTICAL;
        } else if (mOrientation == GridLayoutManager.HORIZONTAL) {
            mOrientation = GRID_HORIZONTAL;
        }
    }
}
