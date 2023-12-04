package com.example.planet.AddPlanetData;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.planet.FirebaseServices;
import com.example.planet.LoginSignUpForgetPassword.LoginFragment;
import com.example.planet.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPlanetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPlanetFragment extends Fragment {
    private EditText etName, etSize, etOrbit, etPopulation;
    private Button btnAddPlanet;
    private ImageButton btnBack;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPlanetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPlanetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPlanetFragment newInstance(String param1, String param2) {
        AddPlanetFragment fragment = new AddPlanetFragment();
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
        return inflater.inflate(R.layout.fragment_add_planet, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        etName = getView().findViewById(R.id.etNameAddPlanetFragment);
        etSize = getView().findViewById(R.id.etSizeAddPlanetFragment);
        etOrbit = getView().findViewById(R.id.etOrbitAddPlanetFragment);
        etPopulation = getView().findViewById(R.id.etPopulationAddPlanetFragment);
        btnBack = getView().findViewById(R.id.btnBackAddPlanetFragment);
        btnAddPlanet = getView().findViewById(R.id.btnAddPlanetAddPlanetFragment);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginFragment();
            }
        });
        btnAddPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String size = etSize.getText().toString();
                String orbit = etOrbit.getText().toString();
                String population = etPopulation.getText().toString();
                if (name.trim().isEmpty() || size.trim().isEmpty() ||
                        orbit.trim().isEmpty() || population.trim().isEmpty())
                {
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!TextUtils.isDigitsOnly(size) || !TextUtils.isDigitsOnly(orbit) || !TextUtils.isDigitsOnly(population))
                {

                    Toast.makeText(getActivity(), "something went wrong!", Toast.LENGTH_LONG).show();
                    return;
                }
                Planet planet1 = new Planet( name , size , orbit , population);
                fbs.getFire().collection("planets").add(planet1).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Successfully added!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Failure AddData : ", e.getMessage());
                    }
                });

            }
        });
    }
    private void goToLoginFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new LoginFragment());
        ft.commit();
    }

}