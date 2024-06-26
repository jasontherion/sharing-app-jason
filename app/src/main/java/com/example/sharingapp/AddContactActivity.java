package com.example.sharingapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private ContactList contact_list = new ContactList();
    private ContactListController contact_list_controller = new ContactListController(contact_list);

    private Context context;

    private EditText username;
    private EditText email;

    private String username_str;
    private String email_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        context = getApplicationContext();
        contact_list_controller.loadContacts(context);
        contact_list.loadContacts(context);
    }

    public void saveContact(View view) {
        if (!validateInput()) {
            return;
        }

        Contact contact = new Contact(username_str, email_str, null);

        // Add Contact
        boolean success = contact_list_controller.addContact(contact, context);
        if (!success) {
            return;
        }

        // End AddContactActivity
        finish();
    }

    public boolean validateInput() {

        boolean validData = true;

        username_str = username.getText().toString();
        email_str = email.getText().toString();

        if (username_str.equals("")) {
            username.setError("Empty field!");
            validData = false;
        }

        if (email_str.equals("")) {
            email.setError("Empty field!");
            validData = false;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            validData = false;
        }

        if (!contact_list_controller.isUsernameAvailable(username_str)){
            username.setError("Username already taken!");
            validData = false;
        }

        return validData;
    }
}