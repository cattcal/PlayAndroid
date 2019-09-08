package cn.hujw.widget.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author: hujw
 * @date: 2019/8/18
 * @description: 自动播放帧动画的 ImageView
 * @email: hujw_android@163.com
 */
public final class AnimationImageView extends AppCompatImageView {

    public AnimationImageView(Context context) {
        super(context);
    }

    public AnimationImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        // 默认开启帧动画
        startAnimation();
    }

    /**
     * 开启帧动画
     */
    public void startAnimation() {
        // 判断当前的 Drawable 是否为帧动画
        if (getDrawable() instanceof AnimationDrawable) {
            // 如果是的话自动播放动画
            ((AnimationDrawable) getDrawable()).start();
        }
    }

    /**
     * 停止帧动画
     */
    public void stopAnimation() {
        // 判断当前的 Drawable 是否为帧动画
        if (getDrawable() instanceof AnimationDrawable) {
            // 如果是的话自动播放动画
            ((AnimationDrawable) getDrawable()).stop();
        }
    }
}