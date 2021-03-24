package com.example.hunger.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.hunger.R;
import com.example.hunger.adapter.OrdersAdapter;
import com.example.hunger.database.DbHelper;
import com.example.hunger.databinding.ActivityOrderBinding;
import com.example.hunger.models.OrderModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        DbHelper helper=new DbHelper(this);
        ArrayList<OrderModel>list=helper.getOrder();



//        list.add(new OrderModel(R.drawable.slice_of_pizza,"Cheese Pizza","5","1253"));
//        list.add(new OrderModel(R.drawable.slice_of_pizza,"Cheese Pizza","5","1253"));
//        list.add(new OrderModel(R.drawable.slice_of_pizza,"Cheese Pizza","5","1253"));
//        list.add(new OrderModel(R.drawable.slice_of_pizza,"Cheese Pizza","5","1253"));
//        list.add(new OrderModel(R.drawable.slice_of_pizza,"Cheese Pizza","5","1253"));
//        list.add(new OrderModel(R.drawable.slice_of_pizza,"Cheese Pizza","5","1253"));
//        list.add(new OrderModel(R.drawable.slice_of_pizza,"Cheese Pizza","5","1253"));

        OrdersAdapter ordersAdapter=new OrdersAdapter(list,this);
        binding.orderRecyclerView.setAdapter(ordersAdapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(linearLayoutManager);
    }
}