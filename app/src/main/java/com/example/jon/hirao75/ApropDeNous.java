package com.example.jon.hirao75;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



public class ApropDeNous extends Fragment {
    private TextView textKely;
    private ListView listZokOl;
    String zkOlona[] = {"Tonia", "ZkJaona"};

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.apropos_denous, container, false);
       // TextView textApropos = (TextView)

        /*listZokOl = (ListView) v.findViewById(R.id.listZokOl);

        ArrayAdapter<String> listAdapter = new CustomListAdapter(getContext(), R.layout.list_item);
        for (int i=0; i<2;i++)
            listAdapter.add(zkOlona[i]);
        listZokOl.setAdapter(listAdapter);*/


        final String fisaorana = "******Teny fisaorana avy amin'ny MENAFIFY 2e IVORY AVARATRA FIANARANTSOA*******" +
                "Isaorana indrindra :" +
                "-Andriamanitra Ray sy Zanaka ary Fanahy Masina: nanome Hery sy fivondronana," +
                "-Foibe Tily: nanome alalana tamin'ny fanatanterahana ity tetik'asa ity" +
                "-Isaorana ireo MP Ivory Avaratra: Nanampy arak'hevitra";

        String tenyManokana = "";

       /* listZokOl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View toolbar = view.findViewById(R.id.toolbar);
                textKely = (TextView) view.findViewById(R.id.textKely);

                ExpandAnimation expandAnimation = new ExpandAnimation(toolbar, 500);
                toolbar.startAnimation(expandAnimation);
                textKely.setText(fisaorana);
            }
        });*/

       // textDetail.setText(fisaorana);

        return  v;
    }

    class CustomListAdapter extends ArrayAdapter<String> {

        public CustomListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, null);
            }

            ((TextView)convertView.findViewById(R.id.title)).setText(getItem(position));

            // Resets the toolbar to be closed
            View toolbar = convertView.findViewById(R.id.toolbar);
            ((LinearLayout.LayoutParams) toolbar.getLayoutParams()).bottomMargin = -50;
            toolbar.setVisibility(View.GONE);

            return convertView;
        }
    }
}
