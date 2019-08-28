package cn.hujw.demo.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;

import com.hjq.toast.ToastUtils;

import butterknife.ButterKnife;
import cn.hujw.base.BaseListViewAdapter;
import cn.hujw.image.ImageLoader;

/**
 * @author: hujw
 * @date: 2019/8/19
 * @description: 项目中 ListView 适配器基类
 * @email: hujw_android@163.com
 */
public abstract class MyListViewAdapter<T, VH extends MyListViewAdapter.ViewHolder>
        extends BaseListViewAdapter<T, VH> {

    /** 当前列表的页码，默认为第一页，用于分页加载功能 */
    private int mPageNumber = 1;
    /** 是否是最后一页，默认为false，用于分页加载功能 */
    private boolean mLastPage;
    /** 标记对象 */
    private Object mTag;

    public MyListViewAdapter(Context context) {
        super(context);
    }

    /**
     * 获取当前的页码
     */
    public int getPageNumber() {
        return mPageNumber;
    }

    /**
     * 设置当前的页码
     */
    public void setPageNumber(int pageNumber) {
        mPageNumber = pageNumber;
    }

    /**
     * 当前是否为最后一页
     */
    public boolean isLastPage() {
        return mLastPage;
    }

    /**
     * 设置是否为最后一页
     */
    public void setLastPage(boolean lastPage) {
        mLastPage = lastPage;
    }

    /**
     * 获取标记
     */
    public Object getTag() {
        return mTag;
    }

    /**
     * 设置标记
     */
    public void setTag(Object tag) {
        mTag = tag;
    }

    /**
     * 显示吐司
     */
    public void toast(CharSequence text) {
        ToastUtils.show(text);
    }

    public void toast(@StringRes int id) {
        ToastUtils.show(id);
    }

    public void toast(Object object) {
        ToastUtils.show(object);
    }

    public class ViewHolder extends BaseListViewAdapter.ViewHolder {

        public ViewHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        public final ViewHolder setText(@IdRes int viewId, @StringRes int stringId) {
            return setText(viewId, getString(stringId));
        }

        public final ViewHolder setText(@IdRes int id, String text) {
            if (text == null) {
                text = "";
            }
            View view = findViewById(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }

        public final ViewHolder setVisibility(@IdRes int viewId, int visibility) {
            View view = findViewById(viewId);
            if (view != null) {
                view.setVisibility(visibility);
            }
            return this;
        }

        public final ViewHolder setColor(@IdRes int viewId, @ColorInt int color) {
            View view = findViewById(viewId);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(color);
            }
            return this;
        }

        public final ViewHolder setImage(@IdRes int viewId, @DrawableRes int drawableId) {
            View view = findViewById(viewId);
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableId);
            }
            return this;
        }

        public final ViewHolder setImage(@IdRes int id, String url) {
            View view = findViewById(id);
            if (view instanceof ImageView) {
                ImageLoader.with(getContext())
                        .load(url)
                        .into((ImageView) view);
            }
            return this;
        }

        public final ViewHolder setChecked(@IdRes int id, boolean checked) {
            View view = findViewById(id);
            if (view instanceof CompoundButton) {
                ((CompoundButton) view).setChecked(checked);
            }
            return this;
        }
    }
}
