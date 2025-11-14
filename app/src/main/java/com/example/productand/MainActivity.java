package com.example.productand;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.productand.Adapter.ProductAdapter;
import com.example.productand.DAO.ProductDAO;
import com.example.productand.DTO.ProductDTO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProductDAO productDAO;
    static String TAG = "ZZZZZZZZZZZZ";
    RecyclerView rc_product;
    ArrayList<ProductDTO> list;
    ProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        productDAO = new ProductDAO(this);

        productDAO.clearAll();
        ProductDTO p1 = new ProductDTO();
        p1.setName("Điện thoại");
        p1.setPrice(5000);
        productDAO.addProduct(p1);

        ProductDTO p2 = new ProductDTO();
        p2.setName("Tivi");
        p2.setPrice(7000);
        productDAO.addProduct(p2);

        ProductDTO p3 = new ProductDTO();
        p3.setName("Tủ lạnh");
        p3.setPrice(12000);
        productDAO.addProduct(p3);



        list = productDAO.getAllProduct();
        adapter = new ProductAdapter(this, list);
        rc_product = findViewById(R.id.rc_product);
        rc_product.setAdapter(adapter);
    }
}