//package my.tomer.edu.firedatabaseloggin3.activities;
package azubakov.edu.caloriescalc.activity;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.transition.Explode;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import azubakov.edu.caloriescalc.R;
import azubakov.edu.caloriescalc.models.User;



/*import tomer.edu.firedemo.R;
import tomer.edu.firedemo.models.User;*/

public class LoginActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    ImageView imageView;
    //FloatingActionButton fab;
    RelativeLayout layout;
    Button btnLogin;
    Button btnRegister;
   // FloatingActionButton fabExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        imageView = (ImageView) findViewById(R.id.imageView);
        layout = (RelativeLayout) findViewById(R.id.layout);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);


     //   fabExit = (FloatingActionButton) findViewById(R.id.fabExit);
        /*  fab = (FloatingActionButton) findViewById(R.id.fab);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // animateExplode();
            }
        });*/



        hideKeyboardWhenNeeded();

    }

    /**
     * Hides the keyboard when layout is touched.
     */
    private void hideKeyboardWhenNeeded() {
        findViewById(R.id.layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });
    }
    private void hideKeyboard(){
        View v = getCurrentFocus();
        if (v == null)
            v = new View(LoginActivity.this);
        InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void login(final View view) {
        hideKeyboard();
        showProgressDialog();
        FirebaseAuth.getInstance().
                signInWithEmailAndPassword(getEmail(), getPassword()).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        gotoMain();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showSnackBar(e, view);
            }
        });
    }

    private ProgressDialog dialog;

    private void showProgressDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setTitle("Logging you in...");
            dialog.setMessage("Connecting to server");
        }
        dialog.show();
    }

    private void hideProgress() {
        dialog.dismiss();
    }


    private void showSnackBar(Exception e, View view) {
        hideProgress();
        Snackbar.make(view, e.getLocalizedMessage(),
                Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }).show();
    }

    /**
     * Start an intent without adding the activity to the stack
     */
    private void gotoMain() {
        Intent intent = new Intent(LoginActivity.this, UserListsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void signUp(final View view) {
        hideKeyboard();
        showProgressDialog();
        FirebaseAuth.
                getInstance().
                createUserWithEmailAndPassword(getEmail(), getPassword()).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        saveUserToDataBase();
                        gotoMain();
                    }
                }).
                addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showSnackBar(e, view);
                    }
                });
    }

    private void saveUserToDataBase() {

        //get the current user:
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //init a model of user:
        User user = new User(currentUser.getUid(), currentUser.getEmail());

        //get a reference to the users table
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(user.getUID());
        // save the new User under the node <userID>

        ref.setValue(user);
    }


    public String getEmail() {
        return etEmail.getText().toString();
    }

    public String getPassword() {
        return etPassword.getText().toString();
    }

    /*@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateExplode() {
        Intent intent = new Intent(this, CalorieNavDrawerActivity.class);

        //set the transition
        getWindow().setExitTransition(new Explode());
        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, fab, "fab").toBundle();

        startActivity(intent, bundle);
    }*/


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        moveViewOut(etEmail, etPassword, btnLogin, btnRegister);
        animateLogin();

    }

    private void animateLogin() {
        float xMid = layout.getWidth() / 2;
        float xet = xMid - etEmail.getWidth() / 2;
        float x2 = xet + etEmail.getWidth();
        float xBtn = x2 - btnLogin.getWidth();

        ObjectAnimator etEmailAnimator = ObjectAnimator.ofFloat(etEmail, "X", xet);
        etEmailAnimator.setInterpolator(new BounceInterpolator());
        etEmailAnimator.setDuration(1000);
        etEmailAnimator.start();


        ObjectAnimator etPasswordAnimator = ObjectAnimator.ofFloat(etPassword, "X", xet);
        etPasswordAnimator.setInterpolator(new BounceInterpolator());
        etPasswordAnimator.setDuration(1000);
        etPasswordAnimator.setStartDelay(700);
        etPasswordAnimator.start();


        ObjectAnimator btnAnimator = ObjectAnimator.ofFloat(btnLogin, "X", xBtn);
        btnAnimator.setInterpolator(new BounceInterpolator());
        btnAnimator.setDuration(1000);
        btnAnimator.setStartDelay(1500);
        btnAnimator.start();

        ObjectAnimator btnRAnimator = ObjectAnimator.ofFloat(btnRegister, "X", xBtn);
        btnRAnimator.setInterpolator(new BounceInterpolator());
        btnRAnimator.setDuration(1000);
        btnRAnimator.setStartDelay(1500);
        btnRAnimator.start();


    }


    void moveViewOut(View... views) {
        for (View v : views) {
            v.setX(0 - v.getWidth());
        }
    }


    public void ExitFinish(View view) {
        FirebaseAuth.getInstance().signOut();
         /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }*/
        this.finish();
        System.exit(0);
        //FirebaseAuth.getInstance().signOut();
    }

}
