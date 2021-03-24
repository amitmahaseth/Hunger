package com.example.hunger.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.hunger.R;
import com.example.hunger.adapter.MainAdapter;
import com.example.hunger.databinding.ActivityMainBinding;
import com.example.hunger.models.MainModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel>list=new ArrayList<>();

        list.add(new MainModel(R.drawable.new_veggie_paradise,"Pizza","5","Extra veggie"));
        list.add(new MainModel(R.drawable.new_veggie_paradise,"Pizza","5","Extra veggie"));
        list.add(new MainModel(R.drawable.hamburguesa,"Burger","0","Extra veggie"));
        list.add(new MainModel(R.drawable.hamburguesa,"Burger","0","Extra veggie"));
        list.add(new MainModel(R.drawable.hamburguesa,"Burger","0","Extra veggie"));
//        list.add(new MainModel(R.drawable.slice_of_pizza,"Pizza","5","Slice Of Pizza with extra veggie"));
        list.add(new MainModel(R.drawable.classic_american_cheeseburger,"Classic American Cheeseburger"
                ,"5","Extra Cheese"));
        MainAdapter adapter =new MainAdapter(list,this);
       binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}