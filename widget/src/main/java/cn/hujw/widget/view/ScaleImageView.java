package cn.hujw.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import cn.hujw.widget.R;

/**
 * @author: hujw
 * @date: 2019/8/18
 * @description: 长按缩放松手恢复的 ImageView
 * @email: hujw_android@163.com
 */
public final class ScaleImageView extends AppCompatImageView {

    private float mScaleSize = 1.2f;

    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScaleImageView);
        setScaleSize(array.getFloat(R.styleable.ScaleImageView_scaleRatio, mScaleSize));
        array.recycle();
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        // 判断当前手指是否按下了
        if (pressed) {
            setScaleX(mScaleSize);
            setScaleY(mScaleSize);
        } else {
            setScaleX(1);
            setScaleY(1);
        }
    }

    public void setScaleSize(float size) {
        mScaleSize = size;
    }
}
