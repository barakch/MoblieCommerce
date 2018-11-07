package apps.shay.barak.mobilecommerceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle data = getIntent().getExtras();
        if(data == null)
            finish();

        ImageView imgUser = findViewById(R.id.img_user);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvEmail = findViewById(R.id.tv_email);
        TextView tvPhone = findViewById(R.id.tv_phone);
        TextView tvPassword = findViewById(R.id.tv_password);
        TextView tvGender = findViewById(R.id.tv_gender_second);
        TextView tvBirthDate = findViewById(R.id.tv_birthDate);


        String name = data.getString("name");
        String email = data.getString("email");
        String phone = data.getString("phone");
        String password = data.getString("password");
        String gender = data.getString("gender");
        String birthDate = data.getString("birthDate");
        String imagePath = data.getString("imagePath");


        Picasso.get().load(imagePath).fit().into(imgUser);
        tvName.setText("Nickname: " + name);
        tvEmail.setText("Email: " + email);
        tvPhone.setText("Phone: " + phone);
        tvPassword.setText("Password: " + password);
        tvGender.setText("Gender: " + gender);
        tvBirthDate.setText("Date of birth: " +birthDate);


        findViewById(R.id.btn_dial).setOnClickListener(this);
        findViewById(R.id.btn_send_mail).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btn_send_mail:


                break;


            case R.id.btn_dial:


                break;
        }
    }
}
