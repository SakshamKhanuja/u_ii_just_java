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
        int price = calculatePrice(quantity);
        String orderSummary = createOrderSummary(price);
        displayMessage(orderSummary);
    }

    /**
     * Calculates the total price of the order.
     *
     * @param quantity is the number of cups ordered.
     * @return the total order amount.
     */
    private int calculatePrice(int quantity) {
        return quantity * 30;
    }

    /**
     * Creates the order summary for this order.
     *
     * @param price is the total price amount.
     * @return a description of the user order.
     */
    private String createOrderSummary(int price) {
        String orderSummary = "Name: Kaptain Kunal";
        orderSummary += "\nQuantity: " + quantity;
        orderSummary += "\nTotal: " + getPriceWithCurrencySymbol(price);
        orderSummary += "\nThank you!";
        return orderSummary;
    }

    /**
     * This method increases the given quantity by one.
     *
     * @param view is the clicked view.
     */
    public void increment(View view) {
        displayQuantity(++quantity);
    }

    /**
     * This method decreases the given quantity by one.
     *
     * @param view is the clicked view.
     */
    public void decrement(View view) {
        displayQuantity(--quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     *
     * @param numberOfCoffee is the total number of cups.
     */
    private void displayQuantity(int numberOfCoffee) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(numberOfCoffee));
    }

    /**
     * This method displays the given message on the screen.
     *
     * @param message is the argument.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Formats the total order amount with currency INR.
     *
     * @param number is total order amount.
     */
    private String getPriceWithCurrencySymbol(int number) {
        return NumberFormat.getCurrencyInstance(new Locale("eng", "IN"))
                .format(number);
    }
}