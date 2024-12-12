package th.hoangduyan.quizappgui2fullcode_laydiemcong;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    TextView resultTxt, percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        boolean[] check = getIntent().getBooleanArrayExtra("checkTF");
        int score = getIntent().getIntExtra("score", 0);
        double correctPercent = score;
        if(check.length!=0)
            correctPercent = (double)(score/check.length)*100;
        String percentR = String.format("%.2f", correctPercent) + "%" ;
        resultTxt.setText(String.valueOf(score));
        percent.setText(String.valueOf(percentR));
    }
}