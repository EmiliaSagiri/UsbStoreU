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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final List<Product> mProductList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;



        public ViewHolder(View view) {
            super(view);
            name=(TextView) view.findViewById(R.id.nam_id);
        }
    }
    public MyAdapter(List<Product> productList){
        mProductList=productList;
    }
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell1,parent,false);
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
    public void onBindViewHolder( ViewHolder holder, int position) {
        Product product = mProductList.get(position);
        holder.name.setText(product.getName());
        holder.name.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
    }

    @Override
    public int getItemCount() {
        // TODO: Add for test
        return mProductList.size()/*mProductList.size()*/;
    }

    /*https://github.com/suming77/RecyclerView_Gallery*/

}
