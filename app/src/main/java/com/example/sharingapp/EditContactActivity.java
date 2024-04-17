package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity implements  Observer  {
    private ContactList contact_list = new ContactList();
    private ContactListController contact_list_controller = new ContactListController(contact_list);

    private Contact contact;
    private ContactController contact_controller = new ContactController(contact);

    private EditText email;
    private EditText username;
    private Context context;
    private boolean on_create_update = false;
    private int pos;

    private String email_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);

        context = getApplicationContext();

        on_create_update = true;

        contact_list_controller.addObserver(this);
        contact_list_controller.loadContacts(context);

        on_create_update = false;
    }

    public void saveContact(View view) {

        if (!validateInput()) {
            return;
        }

        String username_str = username.getText().toString();
        String id = contact.getId(); // Reuse the contact id

        if (!contact_list_controller.isUsernameAvailable(username_str) && !(contact.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return;
        }

        Contact updated_contact = new Contact(username_str, email_str, id);

        // Edit Contact
        boolean success = contact_list_controller.editContact(contact, updated_contact, context);
        if (!success){
            return;
        }

        // End EditContactActivity
        contact_list_controller.removeObserver(this);

        // End EditContactActivity
        finish();
    }

    public void deleteContact(View view) {

        // Delete Contact
        boolean success = contact_list_controller.deleteContact(contact, context);
        if (!success) {
            return;
        }

        // End EditContactActivity
        contact_list_controller.removeObserver(this);

        // End EditContactActivity
        finish();
    }

    @Override
    public void update() {
        if (on_create_update) {
            contact = contact_list_controller.getContact(pos);
            contact_controller = new ContactController(contact);

            username.setText(contact_controller.getUsername());
            email.setText(contact_controller.getEmail());
        }
    }

    public boolean validateInput() {

        boolean validData = true;

        email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            validData = false;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            validData = false;
        }

        return validData;
    }
}