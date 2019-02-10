package com.example.a.foodcam;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class NutritionAdapter extends
        RecyclerView.Adapter<NutritionAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView data;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.nutrition_name);
            data = (TextView) itemView.findViewById(R.id.nutrition_data);
        }


    }
    private List<Food> mFoods;

    // Pass in the contact array into the constructor
    public NutritionAdapter(List<Food> foods) {
        mFoods = foods;
    }

    @Override
    public NutritionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View foodView = inflater.inflate(R.layout.item_nutrition, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(foodView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(NutritionAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Food food = mFoods.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(food.getName());
        TextView dataView = viewHolder.data;
        dataView.setText(String.format(Locale.US, " Cal: %.1f, Fat: %.1f, Sodium: %.1f, " +
                        "Carbs: %.2f, Sugar: %.2f, Protein: %.2f. \n",food.getCalories(), food.getFat(),
                food.getSodium(), food.getCarbohydrate(), food.getSugars(), food.getProtein()));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mFoods.size();
    }
}