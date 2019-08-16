package com.diamond.diamond.thuchi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.diamond.diamond.thuchi.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class    LoginActivity extends AppCompatActivity {
private GoogleSignInClient mGoogleSignInClient;
private SignInButton signInButton;
private Button btnlog;
private CheckBox ckeck;
private EditText nameuser;
private EditText pass;
int RC_SIGN_IN=0;

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         signInButton = findViewById(R.id.sign_in_button);
         btnlog= findViewById(R.id.ogin);
         nameuser= findViewById(R.id.edtnameuser);
         pass= findViewById(R.id.edtpass);
         ckeck= findViewById(R.id.ckeck);
         ckeck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (ckeck.isChecked()){
                     Toast.makeText(LoginActivity.this, "Đã nhớ", Toast.LENGTH_SHORT).show();
                 } else {
                     Toast.makeText(LoginActivity.this, "Bỏ nhớ", Toast.LENGTH_SHORT).show();
                 }

             }
         });


         btnlog.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (nameuser.getText().toString().equals("admin") && pass.getText().toString().equals("123456")){
                     startActivity(new Intent(LoginActivity.this,MainActivity.class));
                 }else {
                     Toast.makeText(LoginActivity.this, "Thông tin tài khoản hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                 }

             }
         });
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent= new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());

        }
    }

}
