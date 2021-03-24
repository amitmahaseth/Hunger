package com.example.hunger.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        final int image = getIntent().getIntExtra("image", 0);
        final int price = Integer.parseInt(getIntent().getStringExtra("price"));
        final String name = getIntent().getStringExtra("name");
        final String description = getIntent().getStringExtra("description");


        binding.detailImage.setImageResource(image);
        binding.priceLbl.setText(String.format("%d", price));
        binding.nameLbl.setText(name);
        binding.detailDescriptions.setText(description);

        DbHelper helper = new DbHelper(this);

        binding.btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.etNameBox.getText().toString().isEmpty()) {
                    binding.etNameBox.setError("Required");
                    binding.etNameBox.requestFocus();
                } else if (binding.etPhoneBox.getText().toString().isEmpty()) {
                    binding.etPhoneBox.setError("Required");
                    binding.etPhoneBox.requestFocus();
                }  else {
                    startActivity(new Intent(DetailsActivity.this,OrderActivity.class));
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
    }
}