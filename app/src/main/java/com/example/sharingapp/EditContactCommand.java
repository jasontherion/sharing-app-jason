package com.example.sharingapp;

import android.content.Context;

public class EditContactCommand extends Command {

    private ContactList contact_list;
    private Contact old_contact;
    private Contact contact;

    private Context context;


    public EditContactCommand(ContactList contact_list, Contact contact, Contact old_contact,  Context context) {
        this.contact_list = contact_list;
        this.old_contact = old_contact;
        this.contact = contact;
        this.context = context;
    }

    @Override
    public void execute() {
        contact_list.deleteContact(contact);
        contact_list.addContact(contact);
        setIsExecuted(contact_list.saveContacts(context));


    }
}
