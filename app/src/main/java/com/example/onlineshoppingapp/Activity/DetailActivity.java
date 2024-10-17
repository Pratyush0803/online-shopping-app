package com.example.onlineshoppingapp.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.onlineshoppingapp.Adapter.SimilarAdapter;
import com.example.onlineshoppingapp.Domain.ItemDomain;
import com.example.onlineshoppingapp.R;
import com.example.onlineshoppingapp.databinding.ActivityDetailBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity {
ActivityDetailBinding binding;
private ItemDomain object;
private int weight = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getBundles();
        setVariable();
        initSimilarList();

    }

    private void initSimilarList() {
        DatabaseReference myRef = database.getReference("Items");
        binding.progressBarSimilar.setVisibility(View.VISIBLE);
        ArrayList<ItemDomain> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue: snapshot.getChildren()){
                        list.add(issue.getValue(ItemDomain.class));
                    }
                    if (!list.isEmpty()){
                        binding.recyclerViewSimilar.setLayoutManager(new LinearLayoutManager(DetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        binding.recyclerViewSimilar.setAdapter(new SimilarAdapter(list));
                    }
                    binding.recyclerViewSimilar.setVisibility(View.VISIBLE);
                }
                binding.progressBarSimilar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(view -> finish());

        Glide.with(DetailActivity.this)
                .load(object.getImagePath())
                .into(binding.img);

        binding.priceKgTxt.setText(object.getPrice() + "₹/Kg");
        binding.titleTxt.setText(object.getTitle());
        binding.descriptionTxt.setText(object.getDescription());
        binding.ratingBar.setRating((float) object.getStar());
        binding.ratingTxt.setText("("+object.getStar()+")");
        binding.totalTxt.setText((weight*object.getPrice())+"₹");

        binding.plusBtn.setOnClickListener(view -> {
            weight = weight + 1;
            binding.weightTxt.setText(weight+"kg");
            binding.totalTxt.setText((weight*object.getPrice())+"₹");

        });

        binding.minusBtn.setOnClickListener(view -> {
            if (weight>1){
                weight = weight - 1;
                binding.weightTxt.setText(weight+" kg");
                binding.totalTxt.setText((weight*object.getPrice())+"₹");
            }
        });
    }

    private void getBundles() {
        object = (ItemDomain) getIntent().getSerializableExtra("object");
    }
}