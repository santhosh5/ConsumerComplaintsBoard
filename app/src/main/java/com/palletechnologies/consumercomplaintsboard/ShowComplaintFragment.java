package com.palletechnologies.consumercomplaintsboard;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowComplaintFragment extends Fragment {

    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    Button button;
    ConsumerDataBase consumerDataBase;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;
    String Description;

    public ShowComplaintFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_complaint, container, false);


        textView1 = (TextView) view.findViewById(R.id.show_company_name);
        textView2 = (TextView) view.findViewById(R.id.show_complaint_subject);
        textView3 = (TextView) view.findViewById(R.id.show_category);
        textView4 = (TextView) view.findViewById(R.id.show_country);
        textView5 = (TextView) view.findViewById(R.id.show_city);
        textView6 = (TextView) view.findViewById(R.id.show_description);
        button = (Button) view.findViewById(R.id.show_comments);

        Bundle bundle = getArguments();
        int position = bundle.getInt("position");

        consumerDataBase = new ConsumerDataBase(getActivity());
        consumerDataBase.open();
        cursor = consumerDataBase.getComplaints();

        while(cursor.moveToNext() == true)
        {
            int id = cursor.getInt(0);
            id--;
            for (int i = id;i <= position; i++)
            {
                String CompanyName = cursor.getString(1);
                textView1.setText(CompanyName);
                String ComplaintSubject = cursor.getString(2);
                textView2.setText(ComplaintSubject);
                String Category = cursor.getString(4);
                textView3.setText(Category);
                String Country = cursor.getString(5);
                textView4.setText(Country);
                String City = cursor.getString(7);
                textView5.setText(City);
                Description = cursor.getString(3);
                textView6.setText(Description);
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.comments(Description);
            }
        });
        
        return view;
    }

}
