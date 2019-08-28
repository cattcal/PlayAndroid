package cn.hujw.demo.ui.fragment;


import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyLazyFragment;
import cn.hujw.demo.ui.activity.HomeActivity;
import cn.hujw.demo.ui.activity.PhotoActivity;
import cn.hujw.image.ImageLoader;

/**
 * @author: hujw
 * @date: 2019/8/11
 * @description: 消息页面
 * @email: hujw_android@163.com
 */
public class MessageFragment extends MyLazyFragment<HomeActivity> {

    @BindView(R.id.iv_message_image)
    ImageView mImageView;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_message_image1, R.id.btn_message_image2, R.id.btn_message_image3, R.id.btn_message_image4,
            R.id.btn_message_toast, R.id.btn_message_permission, R.id.btn_message_state_black, R.id.btn_message_state_white})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_message_image1:
                mImageView.setVisibility(View.VISIBLE);
                ImageLoader.with(this)
                        .load("http://img5q.duitang.com/uploads/item/201506/12/20150612125020_4zkXa.thumb.700_0.jpeg")
                        .into(mImageView);
                break;
            case R.id.btn_message_image2:
                mImageView.setVisibility(View.VISIBLE);
                ImageLoader.with(this)
                        .circle()
                        .load("http://img5q.duitang.com/uploads/item/201506/12/20150612125020_4zkXa.thumb.700_0.jpeg")
                        .into(mImageView);
                break;
            case R.id.btn_message_image3:
                mImageView.setVisibility(View.VISIBLE);
                ImageLoader.with(this)
                        .circle((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, this.getResources().getDisplayMetrics()))
                        .load("http://img5q.duitang.com/uploads/item/201506/12/20150612125020_4zkXa.thumb.700_0.jpeg")
                        .into(mImageView);
                break;
            case R.id.btn_message_image4:
                PhotoActivity.start(getAttachActivity(), new PhotoActivity.OnPhotoSelectListener() {

                    @Override
                    public void onSelect(List<String> data) {
                        mImageView.setVisibility(View.VISIBLE);
                        ImageLoader.with(getAttachActivity())
                                .load(data.get(0))
                                .into(mImageView);
                    }

                    @Override
                    public void onCancel() {
                        toast("取消了");
                    }
                });
                break;
            case R.id.btn_message_toast:
                toast("我是吐司");
                break;
            case R.id.btn_message_permission:
                XXPermissions.with(getAttachActivity())
                        // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                        //.constantRequest()
                        // 支持请求6.0悬浮窗权限8.0请求安装权限
                        //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
                        // 不指定权限则自动获取清单中的危险权限
                        .permission(Permission.CAMERA)
                        .request(new OnPermission() {

                            @Override
                            public void hasPermission(List<String> granted, boolean isAll) {
                                if (isAll) {
                                    toast("获取权限成功");
                                } else {
                                    toast("获取权限成功，部分权限未正常授予");
                                }
                            }

                            @Override
                            public void noPermission(List<String> denied, boolean quick) {
                                if (quick) {
                                    toast("被永久拒绝授权，请手动授予权限");
                                    //如果是被永久拒绝就跳转到应用权限系统设置页面
                                    XXPermissions.gotoPermissionSettings(getAttachActivity());
                                } else {
                                    toast("获取权限失败");
                                }
                            }
                        });
                break;
            case R.id.btn_message_state_black:
                getAttachActivity().getStatusBarConfig().statusBarDarkFont(true).init();
                break;
            case R.id.btn_message_state_white:
                getAttachActivity().getStatusBarConfig().statusBarDarkFont(false).init();
                break;
            default:
                break;
        }
    }
}
