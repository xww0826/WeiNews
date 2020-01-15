package nd.no.xww.weinews.activity.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author xww
 * @desciption : 处理 app 权限
 * @date 2019/7/20
 * @time 16:54
 */
public abstract class BasePermissionActivity extends BaseSkinActivity implements EasyPermissions.PermissionCallbacks {

    @SuppressLint("InlinedApi")
    private String[] mPermissions = {
            Manifest.permission.INTERNET //网络权限
    };

    //获取权限
    private void getPermission() {
        if (!EasyPermissions.hasPermissions(this, mPermissions)) {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要相册、摄像头、定位等权限", 1, mPermissions);
        }
    }

    @Override
    protected void initLayout(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initData() {
        getPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 权限申请成功，用户同意
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 权限申请失败，用户拒绝
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }
}
