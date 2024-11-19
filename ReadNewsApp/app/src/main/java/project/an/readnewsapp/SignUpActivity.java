package project.an.readnewsapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextInputEditText inputEmailSignUp, inputPassSignUp, inputUserSignUp, inputConfirmPassSignUp;
    TextView ivalidPass, ivalidMail, ivalidUser, textClickSignIn;
    Button btnSignUp;
    String email, password, userName;

    private void getControl(){
        inputEmailSignUp = findViewById(R.id.inputEmailSignUp);
        inputPassSignUp = findViewById(R.id.inputPassSignUp);
        inputUserSignUp = findViewById(R.id.inputUserSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        ivalidMail = findViewById(R.id.ivalidMail);
        ivalidPass = findViewById(R.id.ivalidPass);
        ivalidUser = findViewById(R.id.ivalidUser);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        getControl();
    }

    //Check mật khẩu: ít nhất 8 ký tự, có chữ hoa, chữ thường, số và ký tự đặc biệt
    private boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(passwordPattern);
    }

    //check mail
    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailPattern);
    }

    //check tên tài khoản
    private boolean isValidUserName(String userName) {
        // Biểu thức chính quy để kiểm tra userName
        String userNamePattern = "^[a-zA-Z][a-zA-Z0-9_]*$";
        return userName.matches(userNamePattern);
    }

    TextWatcher checkEmail = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            email = inputEmailSignUp.getText().toString();
            if(!isValidEmail(email)){
                ivalidMail.setText("Email chưa hợp lệ!");
            }
            else{
                ivalidMail.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable s){
        }
    };
}