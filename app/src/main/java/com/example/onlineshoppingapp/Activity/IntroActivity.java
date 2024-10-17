package com.example.onlineshoppingapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.onlineshoppingapp.R;
import com.example.onlineshoppingapp.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startBtn.setOnClickListener(view -> startActivity(new Intent(IntroActivity.this,MainActivity.class)));

        TextView textView = findViewById(R.id.textView);
        String text = getString(R.string.intro_title);
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#F3C024")), 10, 29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);

//        TextView textView3 = findViewById(R.id.textView3);
//        String text3 = getString(R.string.intro_login);
//        SpannableString spannable3 = new SpannableString(text3);
//        spannable3.setSpan(new ForegroundColorSpan(Color.parseColor("#F3C024")), 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        textView3.setText(spannable3);
    }
}