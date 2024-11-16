package project.an.readnewsapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextInputEditText inputEmailSignIn, inputPassSignIn;
    TextView forgetPass;
    Button btnSignIn;
    LinearLayout btnSignInGoogle, btnSignInFacebook;

    private void getControl(){
        inputEmailSignIn = findViewById(R.id.inputEmailSignIn);
        inputPassSignIn = findViewById(R.id.inputPassSignIn);
        forgetPass = findViewById(R.id.forgetPass);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignInFacebook = findViewById(R.id.btnSignInFacebook);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener signInMailAndPass = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email, pass;
            email = String.valueOf(inputEmailSignIn.getText());
            pass = String.valueOf(inputPassSignIn.getText());
            if(TextUtils.isEmpty(email)){

            }
        }
    };
}