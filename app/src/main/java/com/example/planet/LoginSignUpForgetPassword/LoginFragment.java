package com.example.planet.LoginSignUpForgetPassword;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planet.AddPlanetData.AddPlanetFragment;
import com.example.planet.ShowData.AllPlanetsFragment;
import com.example.planet.FirebaseServices;
import com.example.planet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private EditText etEmail , etPassword;
    private Button btnLogin;
    private FirebaseServices fbs;
    private TextView tvForgetPassword , tvSignUp;
    private ImageButton btnAddPlanet;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etEmail = getView().findViewById(R.id.etEmailLoginFragment);
        etPassword = getView().findViewById(R.id.etPasswordLoginFragment);
        btnLogin = getView().findViewById(R.id.btnLoginLoginFragment);
        tvForgetPassword = getView().findViewById(R.id.tvForgetPasswordLoginFragment);
        tvSignUp = getView().findViewById(R.id.tvSignUpLoginFragment);
        btnAddPlanet = getView().findViewById(R.id.btnAddPlanetLoginFragment);
        btnAddPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddPlanetFragment();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.trim().isEmpty() && password.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_LONG).show();
                    return;
                }
                fbs.getAuth().signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //TODO: decide what to do
                            goToAllPlanetsFragment();
                        }
                        else
                        {
                            //TODO: decide what to do
                        }
                    }
                });
            }
        });


        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToForgetPasswordFragment();
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUpFragment();
            }
        });
        btnAddPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddPlanetFragment();
            }
        });
    }
    private void goToForgetPasswordFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new ForgetPasswordFragment());
        ft.commit();
    }
    private void goToSignUpFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new SignUpFragment());
        ft.commit();
    }
    private void goToAddPlanetFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new AddPlanetFragment());
        ft.commit();
    }
    private void goToAllPlanetsFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new AllPlanetsFragment());
        ft.commit();
    }
}