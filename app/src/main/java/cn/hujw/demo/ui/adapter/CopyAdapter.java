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
public class CopyAdapter extends MyRecyclerViewAdapter<String, CopyAdapter.ViewHolder> {


    public CopyAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    final class ViewHolder extends MyRecyclerViewAdapter.ViewHolder {

        ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_copy);
        }
    }
}
