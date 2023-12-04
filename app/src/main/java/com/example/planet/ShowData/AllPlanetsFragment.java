package com.example.planet.ShowData;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planet.AddPlanetData.Planet;
import com.example.planet.FirebaseServices;
import com.example.planet.LoginSignUpForgetPassword.LoginFragment;
import com.example.planet.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllPlanetsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllPlanetsFragment extends Fragment {
    private FirebaseServices fbs;
    private ArrayList<Planet> planetsList;
    private RecyclerView rvPlanets;
    private FloatingActionButton btnBack;
    private PlanetAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllPlanetsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllPlanetsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllPlanetsFragment newInstance(String param1, String param2) {
        AllPlanetsFragment fragment = new AllPlanetsFragment();
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
        return inflater.inflate(R.layout.fragment_all_planets, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FirebaseServices.getInstance();
        planetsList = new ArrayList<>();
        rvPlanets = getView().findViewById(R.id.rvPlanetsAllPlanetsFragment);
        adapter = new PlanetAdapter(planetsList , getActivity());
        rvPlanets.setAdapter(adapter);
        rvPlanets.setHasFixedSize(true);
        rvPlanets.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnBack = getView().findViewById(R.id.btnBackAllPlanetsFragment);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbs.getAuth().signOut();
                goToLoginFragment();
            }
        });
        fbs.getFire().collection("planets").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot dataSnapshot : queryDocumentSnapshots.getDocuments()) {
                    Planet p = dataSnapshot.toObject(Planet.class);

                    planetsList.add(p);
                }

                adapter.notifyDataSetChanged();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "No data available", Toast.LENGTH_SHORT).show();
                Log.e("AllTilesFragment", e.getMessage());
            }
        });
    }
    private void goToLoginFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutMain, new LoginFragment());
        ft.commit();
    }
}