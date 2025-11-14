package com.example.productand.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productand.DAO.ProductDAO;
import com.example.productand.DTO.ProductDTO;
import com.example.productand.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductDTO> list;

    ProductDAO productDAO;

    public ProductAdapter(Context context, ArrayList<ProductDTO> list) {
        this.context = context;
        this.list = list;
        productDAO = new ProductDAO(context);
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
        int index = position;
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
                updateRow(productDTO, index);
            }
        });

        // nút xóa
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRow(productDTO.getId(), index);
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


    void deleteRow(int id, int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xac nhan xoa");
        builder.setMessage("Ban co muon xoa san pham nay khong");
        builder.setCancelable(false);
        builder.setPositiveButton("Co", (dialog, which) ->{
            productDAO.deleteRow(id);
            list.remove(index);
            notifyDataSetChanged();
            dialog.dismiss();
        });
        builder.setNegativeButton("Khong", (dialog, which) ->{
           dialog.dismiss();
        });
        builder.show();
    }

    void updateRow(ProductDTO objProduct, int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_edit, null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        EditText ed_name = v.findViewById(R.id.ed_name);
        EditText ed_price = v.findViewById(R.id.ed_price);
        ed_name.setText(objProduct.getName());
        ed_price.setText(String.valueOf(objProduct.getPrice()));

        Button btn_save = v.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objProduct.setName(ed_name.getText().toString());
                String priceStr = ed_price.getText().toString();
                double price = Double.parseDouble(priceStr);
                objProduct.setPrice(price);
                productDAO.updateRow(objProduct);
                list.set(index, objProduct);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    };


}
