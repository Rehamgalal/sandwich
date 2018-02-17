package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView alsoknown, Originplace, desc, ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        Originplace = findViewById(R.id.origin_tv);
        desc = findViewById(R.id.description_tv);
        ingredients = findViewById(R.id.ingredients_tv);
        alsoknown = findViewById(R.id.also_known_tv);
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich S) {
        List<String> altername = S.getAlsoKnownAs();
        if (altername.isEmpty()) {

            alsoknown.setText("No Names");
        } else {

                alsoknown.setText(altername.toString().replace("[", "").replace("]", ""));
        }
        List<String> in = S.getIngredients();
        ingredients.setText(in.toString().replace("[", "").replace("]", ""));
        String description = S.getDescription();
        desc.setText(description);
        String Origin = S.getPlaceOfOrigin();
        if (Origin.equals("")) {

            Originplace.setText("Not specified");
        } else {
            Originplace.setText(Origin);
        }
    }
}