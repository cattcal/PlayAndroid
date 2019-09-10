package cn.hujw.demo.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import cn.hujw.demo.R;
import cn.hujw.demo.common.MyRecyclerViewAdapter;

/**
 * @author: hujw
 * @date: 2019/8/28
 * @description: 可进行拷贝的副本
 * @email: hujw_android@163.com
 */
public final class CopyAdapter extends MyRecyclerViewAdapter<String> {

    public CopyAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }

    final class ViewHolder extends MyRecyclerViewAdapter.ViewHolder {

        ViewHolder() {
            super(R.layout.item_copy);
        }

        @Override
        public void onBindView(int position) {

        }
    }
}