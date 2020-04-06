package Initial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fir_filing_system.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText victim_name,victim_email,victim_password,victim_confirmPassword;
    Button registerButton;

    private FirebaseAuth mAuth;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        victim_name = findViewById(R.id.victim_name);
        victim_email = findViewById(R.id.victim_email);
        victim_password = findViewById(R.id.victim_password);
        victim_confirmPassword = findViewById(R.id.victim_confirmPassword);
        registerButton = findViewById(R.id.victim_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,email,password,confirmPassword;
                name = victim_name.getText().toString();
                email = victim_email.getText().toString();
                password = victim_password.getText().toString();
                confirmPassword = victim_confirmPassword.getText().toString();

                if (TextUtils.isEmpty(name))
                {
                    victim_name.setError("Enter Your Name");
                    return;
                }
                if (TextUtils.isEmpty(email))
                {
                    victim_email.setError("Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    victim_password.setError("Enter Your Password");
                }
                if (TextUtils.isEmpty(confirmPassword))
                {
                    victim_confirmPassword.setError("Enter Your Password Again");
                    return;
                }

                if (!password.equals(confirmPassword))
                {
                    victim_password.setError("Password Mismatch");
                    victim_confirmPassword.setError("Password Mismatch");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });


    }
    private void updateUI(FirebaseUser user){
        if(user==null)
        {

        }
        else
        {
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


}
