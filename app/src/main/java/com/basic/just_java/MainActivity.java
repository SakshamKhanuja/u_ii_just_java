package com.basic.just_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    /*
     * Shows on the go messages to the user.
     */
    private Toast toast;

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
        EditText customerNameEditText = findViewById(R.id.edit_text_name);
        String name = customerNameEditText.getText().toString();

        CheckBox whippedCreamCheckBox = findViewById(R.id.checkbox_whipped_cream);
        boolean addWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.checkbox_chocolate);
        boolean addChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(quantity, addWhippedCream, addChocolate);

        String orderSummary = createOrderSummary(price, name, addWhippedCream, addChocolate);

        // Used for Logging.
        String TAG = "MainActivity";

        Log.v(TAG, "Is Whipped Cream CheckBox Checked? " + addWhippedCream);
        Log.v(TAG, "Is Chocolate CheckBox Checked? " + addChocolate);

        displayMessage(orderSummary);
    }

    /**
     * Calculates the total price of the order.
     *
     * @param quantity        is the number of cups ordered.
     * @param addWhippedCream is 'true' if the user has checked the "Whipped Cream" CheckBox,
     *                        otherwise 'false'.
     * @param addChocolate    is 'true' if the user has checked the "Chocolate" CheckBox, otherwise
     *                        'false'.
     * @return the total order amount.
     */
    private int calculatePrice(int quantity, boolean addWhippedCream, boolean addChocolate) {
        // Represents the base price of a single cup of coffee.
        int basePrice = 30;
        // Represents the price to add Whipped Cream topping.
        int toppingWhippedCream = 10;
        // Represents the price to add Chocolate topping.
        int toppingChocolate = 20;

        if (addWhippedCream && addChocolate) {
            return (basePrice + toppingChocolate + toppingWhippedCream) * quantity;
        } else if (addWhippedCream) {
            return (basePrice + toppingWhippedCream) * quantity;
        } else if (addChocolate) {
            return (basePrice + toppingChocolate) * quantity;
        } else {
            return basePrice * quantity;
        }
    }

    /**
     * Creates the order summary for this order.
     *
     * @param price           is the total price amount.
     * @param name            is the customer name.
     * @param addWhippedCream is 'true' if the user has checked the "Whipped Cream" CheckBox,
     *                        otherwise false.
     * @param addChocolate    is 'true' if the user has checked the "Chocolate" CheckBox, otherwise
     *                        false.
     * @return a description of the user order.
     */
    private String createOrderSummary(int price, String name, boolean addWhippedCream,
                                      boolean addChocolate) {
        String orderSummary = "Name: " + name;
        orderSummary += "\nAdd whipped cream? " + addWhippedCream;
        orderSummary += "\nAdd Chocolate? " + addChocolate;
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
        if (quantity < 100) {
            displayQuantity(++quantity);
        } else {
            showToast(R.string.toastMoreThan100);
        }
    }

    /**
     * Cancels the ongoing Toast and shows a new Toast.
     *
     * @param messageID is a string Resource ID containing the message you want to show.
     */
    private void showToast(int messageID) {
        // If any other toast is showing - remove it.
        if (toast != null) {
            toast.cancel();
        }

        // Send out a new Toast telling customer cannot have less than 1 coffee.
        toast = Toast.makeText(this, messageID, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * This method decreases the given quantity by one.
     *
     * @param view is the clicked view.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            displayQuantity(--quantity);
        } else {
            showToast(R.string.toastLessThanOne);
        }
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