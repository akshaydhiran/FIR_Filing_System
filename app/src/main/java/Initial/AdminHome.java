package Initial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fir_filing_system.R;

import Admin.AdminDigitalSignature;

public class AdminHome extends AppCompatActivity {

    Button verifyFIR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

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
