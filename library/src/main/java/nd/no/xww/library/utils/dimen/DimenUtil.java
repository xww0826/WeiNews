package nd.no.xww.library.utils.dimen;

import android.content.Context;

/**
 * 功能：获取屏幕的尺寸（宽、高）
 *
 * @author : xww
 * @created at : 19-3-17
 * @time : 下午7:30
 */
public final class DimenUtil {

    /**
     * @return 屏幕的宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * @return 屏幕的高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
