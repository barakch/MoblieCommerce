package apps.shay.barak.mobilecommerceapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import apps.shay.barak.mobilecommerceapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int PICK_IMAGE = 1;
    private EditText edName, edEmail, edPhone, edPassword, edBirth;
    private RadioGroup rgGender;
    private String imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.ed_name);
        edEmail = findViewById(R.id.ed_email);
        edPhone = findViewById(R.id.ed_phone);
        edPassword = findViewById(R.id.ed_password);
        edBirth = findViewById(R.id.ed_birth_date);
        rgGender = findViewById(R.id.rg_parent);

        findViewById(R.id.btn_submit).setOnClickListener(this);
        findViewById(R.id.btn_select_image).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                validateSubmittedData();
                break;

            case R.id.btn_select_image:
                selectImage();
                break;
        }
    }

    private void validateSubmittedData() {

        if (!validateName(edName.getText().toString())) {
            if (edName.getText().toString().length() == 0)
                Toast.makeText(getApplicationContext(), "Please enter a full name", Toast.LENGTH_SHORT).show();
            else if (edName.getText().toString().length() < 2)
                Toast.makeText(getApplicationContext(), "Full Name is too short", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Full Name is not valid", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!validateEmail(edEmail.getText().toString())) {
            if (edEmail.getText().toString().length() <= 0)
                Toast.makeText(getApplicationContext(), "Please enter an Email Address", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Email is not valid", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!validatePhone(edPhone.getText().toString())) {
            if (edPhone.getText().toString().length() <= 0)
                Toast.makeText(getApplicationContext(), "Please enter a phone number", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Phone is not valid -  Should be at least 6 numbers", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!validatePassword(edPassword.getText().toString())) {
            if (edPassword.getText().toString().length() < 6)
                Toast.makeText(getApplicationContext(), "Password is not valid, you need at least 6 characters", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Password is not valid, you must use A-Z and numbers only", Toast.LENGTH_SHORT).show();
            return;
        }
        String gender = validateGender();
        if (gender == null) {
            Toast.makeText(getApplicationContext(), "Please choose your gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!validateBirth(edBirth.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Date of birth is not valid - Example 01.01.1999", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imagePath == null) {
            Toast.makeText(getApplicationContext(), "Please select your avatar image", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent(getBaseContext(), SecondActivity.class);
        intent.putExtra("name", edName.getText().toString());
        intent.putExtra("email", edEmail.getText().toString());
        intent.putExtra("phone", edPhone.getText().toString());
        intent.putExtra("password", edPassword.getText().toString());
        intent.putExtra("gender", gender);
        intent.putExtra("birthDate", edBirth.getText().toString());
        intent.putExtra("imagePath", imagePath);
        startActivity(intent);
    }

    private boolean validateName(String name) {
        String expression = "^[A-Za-z]+[A-Za-z]$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return name.length() > 2;
    }

    private boolean validateEmail(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePhone(String phone) {
        String expression = "[0-9*#+() -]*";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches() && phone.length() > 6;
    }

    private boolean validatePassword(String password) {
        String expression = "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private String validateGender() {
        switch (rgGender.getCheckedRadioButtonId()) {
            case R.id.rd_male:
                return "Male";

            case R.id.rd_female:
                return "Female";

            default:
                return null;
        }
    }

    private boolean validateBirth(String birth) {

        String expression = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(birth);
        return matcher.matches();
    }


    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            if (data != null) {
                System.out.println(data.toString() + "");
                System.out.println("" + data.getDataString());
                imagePath = data.getDataString();
            }
        }
    }
}
