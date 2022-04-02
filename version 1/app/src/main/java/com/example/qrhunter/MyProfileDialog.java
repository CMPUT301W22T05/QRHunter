package com.example.qrhunter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class MyProfileDialog extends AppCompatDialogFragment {

    private EditText editUsername;
    private EditText editContact;
    myProfileDialogInterface dialogInterFace;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // initialize the builder and view
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.my_profile_content, null);
        
        // setting the dialog and store the new data
        builder.setView(view)
                .setTitle("Edit your profile")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userName = editUsername.getText().toString();
                        String contact = editContact.getText().toString();

                        dialogInterFace.applyText(userName, contact);
                    }
                });
        
        // set up the textview of the dialog
        editUsername = view.findViewById(R.id.username_editText);
        editContact = view.findViewById(R.id.contact_information_editText);

        return builder.create();
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dialogInterFace = (myProfileDialogInterface) context;
    }

    // interface for storing and displaying text to the screen
    public interface myProfileDialogInterface {
        void applyText(String name, String contact);
    }

}
