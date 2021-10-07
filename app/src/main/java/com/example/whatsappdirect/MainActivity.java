package com.example.whatsappdirect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "WhatsApp_file" ;
    private static final String NAME_KEY = "UserName_key";
    private static final String FIRST_TIME_SHOW_KEY = "FirstTimeShow_key";
    String yourName;

    final Context context = this;
    CountryCodePicker ccp;
    static EditText userPhoneNumber;
    EditText message;
    Button fastReplayOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccp =findViewById(R.id.ccp);
        userPhoneNumber = findViewById(R.id.phone_number_edt);
        message = findViewById(R.id.message);
        fastReplayOne = findViewById(R.id.fast_replay_1);

        checkAlertBox();



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("yst","ss");
        if(resultCode==RESULT_OK){
            Log.i("yst","ss");
            userPhoneNumber.setText(data.getStringExtra("number"));
        }
    }

    public void checkAlertBox(){
        // Show AlertBox :-
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        int check = sharedPreferences.getInt(FIRST_TIME_SHOW_KEY,0);

        if (check == 0){
            makeAlertBox();
        }else {
            setTextInButton();
        }
    }

    public static void setContactNumber(String ph){
        userPhoneNumber.setText(ph);

    }

    public void makeAlertBox(){

        // load the dialog_promt_user.xml layout and inflate to view
        LayoutInflater layoutinflater = LayoutInflater.from(context);
        View promptUserView = layoutinflater.inflate(R.layout.dialog_prompt_user, null);

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(context);
        alertDialogBuilder.setIcon(R.drawable.ic_person_black_24dp);
        alertDialogBuilder.setBackground(getResources().getDrawable(R.drawable.alert_bg,null));
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setView(promptUserView);

        final EditText userAnswer = (EditText) promptUserView.findViewById(R.id.username);

        alertDialogBuilder.setTitle("What's your name?");

        // prompt for username
        alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                // Shared Preferences :
                SharedPreferences.Editor editor = getSharedPreferences(FILE_NAME,MODE_PRIVATE).edit();
                editor.putString(NAME_KEY,userAnswer.getText().toString());
                editor.putInt(FIRST_TIME_SHOW_KEY,1);
                editor.apply();

                setTextInButton();
            }
        });

        // all set and time to build and show up!
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void setTextInButton(){
        //set Values
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        fastReplayOne.setText("Hello! I'm " + sharedPreferences.getString(NAME_KEY," ")+ ".");
        yourName = sharedPreferences.getString(NAME_KEY," ");
    }

    public void sendbtn(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            String messageText = message.getText().toString();

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() + "&text=" + messageText
                    )));
        }
    }

    public void fastReplayOne_btn(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() + "&text=" + "Hello! I'm " + yourName+ "."
                    )));
        }

    }

    public void fastReplayTwo_btn(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() + "&text=Hey"
                    )));
        }
    }

    public void fastReplayThree_btn(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() + "&text=How are you?"
                    )));
        }
    }

    public void fastReplayFour_btn(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() + "&text=What's up?"
                    )));
        }
    }

    public void fastReplayFive_btn(View view) {

        if(TextUtils.isEmpty(userPhoneNumber.getText().toString())){
            Toast.makeText(context, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }else {
            ccp.registerPhoneNumberTextView(userPhoneNumber);
            userPhoneNumber.setHint("Enter Number");

            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(
                            "https://api.whatsapp.com/send?phone=" + ccp.getFullNumberWithPlus() + "&text=What's going on?"
                    )));
        }
    }


    public void contactbtn(View view) {

        Intent intent = new Intent(this,ContactActivity.class);
        startActivity(intent);
    }
}
