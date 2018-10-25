package apps.shay.barak.mobilecommerceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edName, edEmail, edPhone, edPassword, edBirth;
    private RadioGroup rgGender;


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
                Toast.makeText(getApplicationContext(), "Submit", Toast.LENGTH_SHORT).show();
                validateSubmittedData();
                break;


            case R.id.btn_select_image:
                Toast.makeText(getApplicationContext(), "select image", Toast.LENGTH_SHORT).show();


                break;
        }

    }



    private void validateSubmittedData(){

        if(!validateEmail(edEmail.getText().toString())){
            //Tell the user about the relevant error
            Toast.makeText(getApplicationContext(), "Email is not valid", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getApplicationContext(), "Ata gever", Toast.LENGTH_SHORT).show();

    }


    private boolean validateEmail(String email){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
