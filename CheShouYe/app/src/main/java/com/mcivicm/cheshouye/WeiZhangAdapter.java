package com.mcivicm.cheshouye;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by zlb on 17-7-18.
 */

public class WeiZhangAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public WeiZhangAdapter(@Nullable List<String> data) {
        super(R.layout.item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.result, item);
    }
}
