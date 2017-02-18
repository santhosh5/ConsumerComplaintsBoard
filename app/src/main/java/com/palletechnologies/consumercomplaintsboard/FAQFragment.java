package com.palletechnologies.consumercomplaintsboard;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FAQFragment extends Fragment {

    ListView listView;
    String[] Id,Questions, Answers;
    ArrayList<FAQRow> arrayList;
    MainActivity mainActivity;
    FAQRow faqRow;
    MyAdapter myAdapter;

    public FAQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faq, container, false);

        mainActivity = (MainActivity) getActivity();
        listView = (ListView) view.findViewById(R.id.lvfaq);
        Id = getResources().getStringArray(R.array.id);
        Questions = getResources().getStringArray(R.array.questions);
        Answers = getResources().getStringArray(R.array.answers);

        arrayList = new ArrayList<FAQRow>();
        myAdapter = new MyAdapter();

        for (int i=0;i<Id.length;i++)
        {
            faqRow = new FAQRow();
            faqRow.setId(Id[i]);
            faqRow.setQuestions(Questions[i]);
            faqRow.setAnswers(Answers[i]);
            arrayList.add(faqRow);
        }

        listView.setAdapter(myAdapter);
        return view;
    }

    public class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            faqRow = arrayList.get(position);
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_faq, parent, false);
            TextView tx_id = (TextView) convertView.findViewById(R.id.txid);
            TextView tx_questions = (TextView) convertView.findViewById(R.id.txquestions);
            TextView tx_answers = (TextView) convertView.findViewById(R.id.txanswers);

            tx_id.setText(faqRow.getId()+".");
            tx_questions.setText(faqRow.getQuestions());
            tx_answers.setText(faqRow.getAnswers());

            return convertView;
        }
    }

}
