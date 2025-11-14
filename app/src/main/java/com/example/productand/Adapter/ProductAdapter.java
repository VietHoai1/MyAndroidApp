package com.example.productand.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productand.DTO.ProductDTO;
import com.example.productand.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductDTO> list;

    public ProductAdapter(Context context, ArrayList<ProductDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_product, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // lấy 1 dòng dữ liệu trong list
        ProductDTO productDTO = list.get(position);

        // gán vào view
        holder.tv_id.setText("ID: " + productDTO.getId());
        holder.tv_name.setText("Tên: " + productDTO.getName());
        holder.tv_price.setText("Giá: " + productDTO.getPrice());

        // nút sửa
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: xử lý nút sửa
                // có thể mở dialog sửa sản phẩm
            }
        });

        // nút xóa
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: xử lý nút xóa
                // gọi DAO để delete nếu cần
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    // ================= VIEW HOLDER =================
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_id, tv_name, tv_price;
        ImageView img_edit, img_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id    = itemView.findViewById(R.id.tv_id);
            tv_name  = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);

            img_edit   = itemView.findViewById(R.id.img_edit);
            img_delete = itemView.findViewById(R.id.img_delete);
        }
    }
}
