package com.example.usbtry;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usbtry.utils.Product;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder>{
    private final List<Product> mProductList2;
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView md5_name;
        public ViewHolder(View view) {
            super(view);
            md5_name=(TextView) view.findViewById(R.id.d5_id);
        }
    }
    public MyAdapter2(List<Product> productList){
        mProductList2=productList;
    }
    @NonNull
    public MyAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell2,parent,false);
        return new ViewHolder(view);
    }
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mProductList2.get(position);
        holder.md5_name.setText(product.getName());
        holder.md5_name.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
    }
    @Override
    public int getItemCount() {
        // TODO: Add for test
        return mProductList2.size()/*mProductList.size()*/;
    }

    /*https://github.com/suming77/RecyclerView_Gallery*/

}

