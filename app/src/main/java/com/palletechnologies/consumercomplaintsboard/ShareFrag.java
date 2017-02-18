package com.palletechnologies.consumercomplaintsboard;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFrag extends DialogFragment {

    ImageView ib1,ib2,ib3,ib4;
    public static final String url = "http://www.facebook.com/palletech";

    public ShareFrag() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog d = null;
        AlertDialog.Builder ab = new AlertDialog.Builder(getActivity());

        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_share, null);

        ib1 = (ImageView) v.findViewById(R.id.facebook);
        ib2 = (ImageView) v.findViewById(R.id.twitter);
        ib3 = (ImageView) v.findViewById(R.id.googleplus);
        ib4 = (ImageView) v.findViewById(R.id.website);


        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),WebFrag.class);
                i.setAction(Intent.ACTION_VIEW);
                //i.addCategory(Intent.CATEGORY_BROWSABLE);
                i.setData(Uri.parse(url));
                Bundle bundle = new Bundle();
                bundle.putString("url",url);

                //i.putExtra("url",url);
                //  startActivity(i);

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.Web();
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.twitter.com/palletechbnglr"));
                startActivity(i);

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.Web();
            }
        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),WebFrag.class);
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.palletechnologiesbangalore.blogspot.in"));
                startActivity(i);

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.Web();
            }
        });

        ab.setView(v);
        d = ab.create();

        return d;
    }

}
