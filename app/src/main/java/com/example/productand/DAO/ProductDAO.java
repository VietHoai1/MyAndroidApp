package com.example.productand.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.productand.DTO.ProductDTO;
import com.example.productand.DbHelper.MyDbHelper;

public class ProductDAO {
        MyDbHelper dbHelper;
        SQLiteDatabase db;


        public ProductDAO(Context context) {
            dbHelper = new MyDbHelper(context);
            db = dbHelper.getWritableDatabase();
        }

        // viết hàm thêm dữ liệu
        public int addProduct(ProductDTO productDTO) {
            ContentValues values = new ContentValues();
            values.put("name", productDTO.getName());
            values.put("price", productDTO.getPrice());
            return (int) db.insert("tb_product", null, values);

        }
    }