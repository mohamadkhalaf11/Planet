package com.example.planet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.planet.LoginSignUpForgetPassword.LoginFragment;
import com.example.planet.ShowData.AllPlanetsFragment;

public class MainActivity extends AppCompatActivity {

    private FirebaseServices fbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbs = FirebaseServices.getInstance();
        if(fbs.getAuth().getCurrentUser()==null) {
            goToLoginFragment();
        }
        else {
            goToAllPlanetsFragment();
        }
    }

    private void goToLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new LoginFragment());
        ft.commit();
    }
    private void goToAllPlanetsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new AllPlanetsFragment());
        ft.commit();
    }
}