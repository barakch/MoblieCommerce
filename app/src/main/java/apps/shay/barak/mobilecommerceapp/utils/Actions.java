package apps.shay.barak.mobilecommerceapp.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class Actions {

    public static void sendEmail(Activity context, String emailAddress, String subject, String body){
        String[] email = {emailAddress} ;
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(intent, "Send Email"));
    }

    public static void dailPhoneNumber(Activity context, String phoneNum){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNum, null));
        context.startActivity(intent);
    }
}
