package project.an.readnewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextInputEditText inputEmailSignIn, inputPassSignIn;
    TextView forgetPass, needPass, needMail;
    Button btnSignIn;
    LinearLayout btnSignInGoogle, btnSignInFacebook;

    private void getControl(){
        inputEmailSignIn = findViewById(R.id.inputEmailSignIn);
        inputPassSignIn = findViewById(R.id.inputPassSignIn);
        forgetPass = findViewById(R.id.forgetPass);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignInFacebook = findViewById(R.id.btnSignInFacebook);
        btnSignInGoogle = findViewById(R.id.btnSignInGoogle);
        needMail = findViewById(R.id.needMail);
        needPass = findViewById(R.id.needPass);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        getControl();
        btnSignIn.setOnClickListener(signInMailAndPass);
        inputEmailSignIn.addTextChangedListener(putEmail);
    }

    TextWatcher putEmail = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            needMail.setText("");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    TextWatcher putPass = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            needPass.setText("");
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    View.OnClickListener signInMailAndPass = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email, pass;
            email = String.valueOf(inputEmailSignIn.getText());
            pass = String.valueOf(inputPassSignIn.getText());
            if(TextUtils.isEmpty(email)){
                needMail.setText("Hãy nhập email!");
                return;
            }
            if(TextUtils.isEmpty(pass)){
                needMail.setText("");
                needPass.setText("Hãy nhập mật khẩu!");
                return;
            }
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(SignInActivity.this, "Login successful",Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignInActivity.this, "Authentication failed.",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    };
}