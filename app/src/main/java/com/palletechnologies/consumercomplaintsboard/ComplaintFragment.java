package com.palletechnologies.consumercomplaintsboard;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComplaintFragment extends Fragment {

    EditText et1,et2,et3,et4,et5,et6;
    Spinner sp1,sp2;
    Button b1;
    ConsumerDataBase consumerDataBase;
    ArrayAdapter<String> arrayAdapter1, arrayAdapter2;
    Cursor cursor;
    String[] Country_Array;     //sp2
    String[] Category_Array;    //sp1
    String ccountry,ccategory;

    public ComplaintFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_complaint, container, false);

        et1 = (EditText) v.findViewById(R.id.company_name);
        et2 = (EditText) v.findViewById(R.id.complaint_subject);
        et3 = (EditText) v.findViewById(R.id.complaint_details);
        et4 = (EditText) v.findViewById(R.id.zipcode);
        et5 = (EditText) v.findViewById(R.id.city);
        et6 = (EditText) v.findViewById(R.id.website);
        sp1 = (Spinner) v.findViewById(R.id.categoty);
        sp2 = (Spinner) v.findViewById(R.id.country);
        b1 = (Button) v.findViewById(R.id.submit);

        Country_Array = getResources().getStringArray(R.array.country);
        Category_Array = getResources().getStringArray(R.array.category);

        arrayAdapter1 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_row, Category_Array); //sp1
        arrayAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_row, Country_Array); //sp2

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ccategory = sp1.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp1.setAdapter(arrayAdapter1);

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ccountry = sp2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp2.setAdapter(arrayAdapter2);

        consumerDataBase = new ConsumerDataBase(getActivity());
        consumerDataBase.open();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cname = et1.getText().toString();
                String csub = et2.getText().toString();
                String cdetails = et3.getText().toString();
                int czip = Integer.parseInt(et4.getText().toString());
                String ccity = et5.getText().toString();
                String cweb = et6.getText().toString();


                consumerDataBase.insertComplaints(cname,csub,cdetails,czip,ccity,cweb,ccategory,ccountry);

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.home();
            }
        });
        return v;
    }

}
