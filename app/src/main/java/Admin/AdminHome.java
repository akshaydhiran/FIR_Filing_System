package Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fir_filing_system.R;

public class AdminHome extends AppCompatActivity {

    Button verifyFIR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        verifyFIR = findViewById(R.id.admin_signature);

        verifyFIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, AdminDigitalSignature.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
