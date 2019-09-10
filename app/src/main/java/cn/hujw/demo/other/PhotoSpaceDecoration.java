package cn.hujw.demo.other;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: hujw
 * @date: 2019/8/19
 * @description: 图片选择列表分割线
 * @email: hujw_android@163.com
 */
public final class PhotoSpaceDecoration extends RecyclerView.ItemDecoration {

    private final int mSpace;

    public PhotoSpaceDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {}

    @SuppressWarnings("all")
    @Override
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int position = recyclerView.getChildAdapterPosition(view);
        int spanCount = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();

        // 每一行的最后一个才留出右边间隙
        if ((position + 1) % spanCount == 0) {
            rect.right = mSpace;
        }

        // 只有第一行才留出顶部间隙
        if (position < spanCount) {
            rect.top = mSpace;
        }

        rect.bottom = mSpace;
        rect.left = mSpace;
    }

    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {}
}