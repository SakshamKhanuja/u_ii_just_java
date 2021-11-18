package com.basic.just_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * The app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    /*
     * Represents the number of cups of coffee.
     */
    private int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the "Order" button is clicked.
     *
     * @param view is the clicked view.
     */
    public void submitOrder(View view) {
        display(quantity);
        displayPrice(quantity * 30);
    }

    /**
     * This method increases the given quantity by one.
     *
     * @param view is the clicked view.
     */
    public void increment(View view) {
        display(++quantity);
    }

    /**
     * This method decreases the given quantity by one.
     *
     * @param view is the clicked view.
     */
    public void decrement(View view) {
        display(--quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param number is the total number of cups.
     */
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    /**
     * This method displays the calculate price value on the screen.
     *
     * @param number is total order amount.
     */
    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(new Locale("eng",
                "IN")).format(number));
    }
}