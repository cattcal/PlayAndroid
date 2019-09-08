package cn.hujw.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hujw.base.BaseDialog;
import cn.hujw.base.BaseDialogFragment;
import cn.hujw.base.BaseRecyclerViewAdapter;

/**
 * @author: hujw
 * @date: 2019/8/18
 * @description: 菜单选择框
 * @email: hujw_android@163.com
 */
public final class MenuDialog {

    public static final class Builder
            extends BaseDialogFragment.Builder<Builder>
            implements View.OnClickListener,
            BaseRecyclerViewAdapter.OnItemClickListener {

        private OnListener mListener;
        private boolean mAutoDismiss = true;

        private final RecyclerView mRecyclerView;
        private final MenuAdapter mAdapter;
        private final TextView mCancelView;

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_menu);

            mRecyclerView = findViewById(R.id.rv_menu_list);
            mCancelView  = findViewById(R.id.tv_menu_cancel);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mAdapter = new MenuAdapter(getContext());
            mAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mAdapter);

            mCancelView.setOnClickListener(this);
        }

        public Builder setList(int... ids) {
            List<String> data = new ArrayList<>(ids.length);
            for (int id : ids) {
                data.add(getString(id));
            }
            return setList(data);
        }

        public Builder setList(String... data) {
            return setList(Arrays.asList(data));
        }

        @SuppressWarnings("all")
        public Builder setList(List data) {
            mAdapter.setData(data);
            return this;
        }

        public Builder setCancel(@StringRes int id) {
            return setCancel(getString(id));
        }

        public Builder setCancel(CharSequence text) {
            mCancelView.setText(text);
            mCancelView.setVisibility((text == null || "".equals(text.toString())) ? View.GONE : View.VISIBLE);
            return this;
        }

        public Builder setAutoDismiss(boolean dismiss) {
            mAutoDismiss = dismiss;
            return this;
        }

        public Builder setListener(OnListener l) {
            mListener = l;
            return this;
        }

        /**
         * {@link View.OnClickListener}
         */
        @Override
        public void onClick(View v) {
            if (mAutoDismiss) {
                dismiss();
            }

            if (v == mCancelView) {
                if (mListener != null) {
                    mListener.onCancel(getDialog());
                }
            }
        }

        /**
         * {@link BaseRecyclerViewAdapter.OnItemClickListener}
         */
        @SuppressWarnings("all")
        @Override
        public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
            if (mAutoDismiss) {
                dismiss();
            }

            if (mListener != null) {
                mListener.onSelected(getDialog(), position, mAdapter.getItem(position));
            }
        }
    }

    private static final class MenuAdapter extends BaseRecyclerViewAdapter<Object, MenuAdapter.ViewHolder> {

        private MenuAdapter(Context context) {
            super(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        final class ViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

            private final TextView mTextView;
            private final View mView;

            private ViewHolder(ViewGroup parent) {
                super(parent, R.layout.item_menu);
                mTextView = (TextView) findViewById(R.id.tv_menu_name);
                mView = findViewById(R.id.v_menu_line);
            }

            @Override
            public void onBindView(int position) {
                mTextView.setText(getItem(position).toString());

                if (position == 0) {
                    // 当前是否只有一个条目
                    if (getItemCount() == 1) {
                       itemView.setBackgroundResource(R.drawable.dialog_menu_item);
                       mView.setVisibility(View.GONE);
                    } else {
                        itemView.setBackgroundResource(R.drawable.dialog_menu_item_top);
                        mView.setVisibility(View.VISIBLE);
                    }
                } else if (position == getItemCount() - 1) {
                    itemView.setBackgroundResource(R.drawable.dialog_menu_item_bottom);
                   mView.setVisibility(View.GONE);
                } else {
                    itemView.setBackgroundResource(R.drawable.dialog_menu_item_middle);
                    mView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public interface OnListener<T> {

        /**
         * 选择条目时回调
         */
        void onSelected(BaseDialog dialog, int position, T t);

        /**
         * 点击取消时回调
         */
        void onCancel(BaseDialog dialog);
    }
}
