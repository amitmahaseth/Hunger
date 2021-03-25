package com.example.hunger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hunger.R;
import com.example.hunger.database.DbHelper;
import com.example.hunger.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final DbHelper helper = new DbHelper(this);


        if(getIntent().getIntExtra("type",0)==1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("description");


            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.nameLbl.setText(name);
            binding.detailDescriptions.setText(description);


            binding.btnOrderNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.etNameBox.getText().toString().isEmpty()) {
                        binding.etNameBox.setError("Required");
                        binding.etNameBox.requestFocus();
                    } else if (binding.etPhoneBox.getText().toString().isEmpty()) {
                        binding.etPhoneBox.setError("Required");
                        binding.etPhoneBox.requestFocus();
                    } else {
                        startActivity(new Intent(DetailsActivity.this, OrderActivity.class));
                        finish();
                        boolean isInserted = helper.insertOrder(binding.etNameBox.getText().toString(),
                                binding.etPhoneBox.getText().toString(),
                                price,
                                image,
                                name,
                                description,
                                Integer.parseInt(binding.tvCounter.getText().toString())
                        );
                        if (isInserted)
                            Toast.makeText(DetailsActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DetailsActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            int id=getIntent().getIntExtra("id",0);
            Cursor cursor=helper.getOrderById(id);
            int image=cursor.getInt(4);

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", 3));
            binding.nameLbl.setText(cursor.getString(6));
            binding.detailDescriptions.setText(cursor.getString(5));

            binding.etNameBox.setText(cursor.getString(1));
            binding.etPhoneBox.setText(cursor.getString(2));

            binding.btnOrderNow.setText("Update Now");
            binding.btnOrderNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (binding.etNameBox.getText().toString().isEmpty()) {
                        binding.etNameBox.setError("Required");
                        binding.etNameBox.requestFocus();
                    } else if (binding.etPhoneBox.getText().toString().isEmpty()) {
                        binding.etPhoneBox.setError("Required");
                        binding.etPhoneBox.requestFocus();
                    } else {
                        startActivity(new Intent(DetailsActivity.this, OrderActivity.class));
                        finish();
                        boolean isUpdated = helper.updateOrder(
                                binding.etNameBox.getText().toString(),
                                binding.etPhoneBox.getText().toString(),
                                Integer.parseInt(binding.priceLbl.getText().toString()),
                                image,
                                binding.detailDescriptions.getText().toString(),
                                binding.nameLbl.getText().toString(),
                                1,
                                id

                        );
                        if (isUpdated)
                            Toast.makeText(DetailsActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}