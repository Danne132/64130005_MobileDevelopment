package bt.an.firebaseapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    TextInputEditText txtEmail, txtPass;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        txtEmail = findViewById(R.id.email);
        txtPass = findViewById(R.id.pass);
        btnRegister = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass;
                email = String.valueOf(txtEmail.getText());
                pass = String.valueOf(txtPass.getText());
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Register.this, "createUserWithEmail:success", Toast.LENGTH_LONG).show();
//                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed", Toast.LENGTH_LONG).show();
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    updateUI(null);
                                }
                            }
                        });
            }
        });
    }
}