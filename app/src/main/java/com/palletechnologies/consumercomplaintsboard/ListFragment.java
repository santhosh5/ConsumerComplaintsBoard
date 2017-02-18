package com.palletechnologies.consumercomplaintsboard;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    ListView listView;
    ConsumerDataBase consumerDataBase;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        listView = (ListView) v.findViewById(R.id.list_view1);

        consumerDataBase = new ConsumerDataBase(getActivity());
        consumerDataBase.open();
        cursor = consumerDataBase.getComplaints();
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.row, cursor,
                new String[]{"company_name","complaint_subject","category","city"},
                new int[]{R.id.row_company_name,R.id.row_complaint_details,R.id.row_category,R.id.row_location});
        listView.setAdapter(simpleCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.showComments(position);
            }
        });

        return v;
    }

}
