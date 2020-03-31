package com.example.jobs.data;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.jobs.data.model.LoggedInUser;
import com.example.jobs.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(final String username, String password) {
        try {


            LoggedInUser User =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            username);
            return new Result.Success<>(User);



        } catch (Exception e) {
            Log.w("LOGIN", "signInWithEmail:failure",e);
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
