package com.palletechnologies.consumercomplaintsboard;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends Fragment {

    ListView listView;
    EditText editText;
    ImageView imageView;
    ConsumerDataBase consumerDataBase;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;

    public CommentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_comments, container, false);

        listView = (ListView) view.findViewById(R.id.lvcomments);
        editText = (EditText) view.findViewById(R.id.etcomments);
        imageView = (ImageView) view.findViewById(R.id.sendcomments);

        Bundle bundle = getArguments();
        final String description = bundle.getString("description");

        consumerDataBase = new ConsumerDataBase(getActivity());
        consumerDataBase.open();
        cursor = consumerDataBase.getComments(description);
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.comments_row, cursor,
                new String[]{"comment"}, new int[]{R.id.forcomments});
        listView.setAdapter(simpleCursorAdapter);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = editText.getText().toString();
                consumerDataBase.insertComments(comment, description);
                cursor.requery();
                editText.setText(" ");
            }
        });

        return view;
    }

}
