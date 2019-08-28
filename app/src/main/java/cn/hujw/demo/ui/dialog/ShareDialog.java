package cn.hujw.demo.ui.dialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.hujw.base.BaseRecyclerViewAdapter;
import cn.hujw.demo.R;
import cn.hujw.demo.common.MyDialogFragment;
import cn.hujw.demo.common.MyRecyclerViewAdapter;
import cn.hujw.umeng.Platform;
import cn.hujw.umeng.UmengClient;
import cn.hujw.umeng.UmengShare;

/**
 * @author: hujw
 * @date: 2019/8/22
 * @description: 分享对话框
 * @email: hujw_android@163.com
 */
public final class ShareDialog {

    public static final class Builder
            extends MyDialogFragment.Builder<Builder>
            implements MyRecyclerViewAdapter.OnItemClickListener {

        private final ShareAdapter mAdapter;
        private final RecyclerView mRecyclerView;

        private final UmengShare.ShareData mData;

        private UmengShare.OnShareListener mListener;

        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.dialog_share);

            final List<ShareBean> data = new ArrayList<>();
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_wechat), getString(R.string.share_platform_wechat), Platform.WECHAT));
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_moment), getString(R.string.share_platform_moment), Platform.CIRCLE));
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_qq), getString(R.string.share_platform_qq), Platform.QQ));
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_qzone), getString(R.string.share_platform_qzone), Platform.QZONE));
            data.add(new ShareBean(getDrawable(R.drawable.ic_share_link), getString(R.string.share_platform_link), null));

            mRecyclerView = findViewById(R.id.rv_share_list);
            mAdapter = new ShareAdapter(activity);
            mAdapter.setData(data);
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setLayoutManager(new GridLayoutManager(activity, data.size()));
            mRecyclerView.setAdapter(mAdapter);

            mData = new UmengShare.ShareData(getActivity());
        }

        public Builder setShareTitle(String title) {
            mData.setShareTitle(title);
            return this;
        }

        public Builder setShareDescription(String description) {
            mData.setShareDescription(description);
            return this;
        }

        public Builder setShareLogo(String url) {
            mData.setShareLogo(url);
            return this;
        }

        public Builder setShareLogo(@DrawableRes int id) {
            mData.setShareLogo(id);
            return this;
        }

        public Builder setShareUrl(String url) {
            mData.setShareUrl(url);
            return this;
        }

        public Builder setListener(UmengShare.OnShareListener l) {
            mListener = l;
            return this;
        }

        /**
         * {@link BaseRecyclerViewAdapter.OnItemClickListener}
         */
        @Override
        public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
            Platform platform = mAdapter.getItem(position).getSharePlatform();
            if (platform != null) {
                UmengClient.share(getActivity(), platform, mData, mListener);
            } else {
                // 复制到剪贴板
                getSystemService(ClipboardManager.class).setPrimaryClip(ClipData.newPlainText("url", mData.getShareUrl()));
                toast(R.string.share_platform_copy_hint);
            }
            dismiss();
        }
    }

    private static class ShareAdapter extends BaseRecyclerViewAdapter<ShareBean, ShareAdapter.ViewHolder> {

        private ShareAdapter(Context context) {
            super(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ShareBean bean = getItem(position);
            holder.mImageView.setImageDrawable(bean.getShareIcon());
            holder.mTextView.setText(bean.getShareName());
        }

        final class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

            private ImageView mImageView;
            private TextView mTextView;

            private ViewHolder(ViewGroup parent) {
                super(parent, R.layout.item_share);
                mImageView = (ImageView) findViewById(R.id.iv_share_image);
                mTextView = (TextView) findViewById(R.id.tv_share_text);
            }
        }
    }

    private static class ShareBean {

        /** 分享图标 */
        private final Drawable mShareIcon;
        /** 分享名称 */
        private final String mShareName;
        /** 分享平台 */
        private final Platform mSharePlatform;

        private ShareBean(Drawable icon, String name, Platform platform) {
            mShareIcon = icon;
            mShareName = name;
            mSharePlatform = platform;
        }

        private Drawable getShareIcon() {
            return mShareIcon;
        }

        private String getShareName() {
            return mShareName;
        }

        private Platform getSharePlatform() {
            return mSharePlatform;
        }
    }
}
