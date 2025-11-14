package com.example.productand.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.productand.DTO.ProductDTO;
import com.example.productand.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class ProductDAO {
        MyDbHelper dbHelper;
        SQLiteDatabase db;
        static String TAG = "ZZZZZZZZZZZZ_PRODUCT";


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

        public ArrayList<ProductDTO> getAllProduct(){
            ArrayList<ProductDTO> list = new ArrayList<>();
            String spl = "SELECT * FROM tb_product";
            Cursor cursor = db.rawQuery(spl, null);
            if (cursor != null){
                cursor.moveToFirst();
                do{
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    double price = cursor.getDouble(2);

                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(id);
                    productDTO.setName(name);
                    productDTO.setPrice(price);

                    list.add(productDTO);

                }while (cursor.moveToNext());
                cursor.close();
            }else {
                Log.d(TAG, "getAllProduct: Không lấy được danh sách");
            }
            return list;
    }

    public ProductDTO getProductById(int id) {
        ProductDTO productDTO = null;
        String[] params = { String.valueOf(id) };
        String sql = "SELECT * FROM tb_product WHERE id = ?";

        Cursor cursor = db.rawQuery(sql, params);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            productDTO = new ProductDTO();
            productDTO.setId(cursor.getInt(0));
            productDTO.setName(cursor.getString(1));
            productDTO.setPrice(cursor.getDouble(2));
        }

        if (cursor != null) {
            cursor.close();
        }

        return productDTO;
    }

    public boolean updateRow(ProductDTO productDTO) {
        ContentValues values = new ContentValues();
        values.put("name", productDTO.getName());
        values.put("price", productDTO.getPrice());
        // nếu có id_cat:
        // values.put("id_cat", productDTO.getId_cat());

        String[] params = { String.valueOf(productDTO.getId()) };

        int rows = db.update("tb_product", values, "id = ?", params);
        return rows > 0;
    }

    public boolean deleteRow(int id) {
        String[] params = { String.valueOf(id) };
        int rows = db.delete("tb_product", "id = ?", params);
        return rows > 0;
    }

    public void clearAll() {
        db.execSQL("DELETE FROM tb_product");
    }
    }