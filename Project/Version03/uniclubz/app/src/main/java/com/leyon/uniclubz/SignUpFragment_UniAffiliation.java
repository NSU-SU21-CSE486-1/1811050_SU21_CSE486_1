package com.leyon.uniclubz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment_UniAffiliation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment_UniAffiliation extends Fragment {

    public static int numberOfUniInfoFormsCreated = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment_UniAffiliation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment_UniAffiliation.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment_UniAffiliation newInstance(String param1, String param2) {
        SignUpFragment_UniAffiliation fragment = new SignUpFragment_UniAffiliation();
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
        return inflater.inflate(R.layout.fragment_sign_up__uni_affiliation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numberOfUniInfoFormsCreated++;
        view.setTag("UniData" + numberOfUniInfoFormsCreated); //tag for identifying fragment in SignUpFragment

        Spinner universitySelectSpinner = view.findViewById(R.id.universitySelectSpinner);
        ArrayAdapter<CharSequence> uniNameAdapter = ArrayAdapter.createFromResource(getContext(), R.array.university_list, android.R.layout.simple_spinner_item);
        uniNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universitySelectSpinner.setAdapter(uniNameAdapter);

        Spinner universityDepartmentSelectSpinner = view.findViewById(R.id.universityDepartmentSelectSpinner);
        ArrayAdapter<CharSequence> uniDepartmentAdapter = ArrayAdapter.createFromResource(getContext(), R.array.departments, android.R.layout.simple_spinner_item);
        uniDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universityDepartmentSelectSpinner.setAdapter(uniDepartmentAdapter);

        Spinner universityStudyLevelSelectSpinner = view.findViewById(R.id.universityStudyLevelSelectSpinner);
        ArrayAdapter<CharSequence> uniStudyLevelAdapter = ArrayAdapter.createFromResource(getContext(), R.array.study_levels, android.R.layout.simple_spinner_item);
        uniStudyLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universityStudyLevelSelectSpinner.setAdapter(uniStudyLevelAdapter);
    }
}