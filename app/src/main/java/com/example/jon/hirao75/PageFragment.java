package com.example.jon.hirao75;

import android.content.res.AssetManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment {

    static final int READ_BLOCK_SIZE = 100;
    private ListView listHira;
    private TextView affichHira;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.page_fragment, container, false);

        listHira = (ListView) v.findViewById(R.id.listHira);
        affichHira = (TextView) v.findViewById(R.id.affichHira);

        affichHira.setVisibility(View.INVISIBLE);

        listHira.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) listHira.getItemAtPosition(i);
                //btn_revert.setVisibility(vMain.VISIBLE);
                listHira.setVisibility(View.INVISIBLE);
                affichHira.setVisibility(View.VISIBLE);
                affichHira.setText(mamakyTononkira(selected));
            }
        });

        List<String> lohatenyHira = new ArrayList<>();
        String titre[] = this.makaLohateninkira();
        int i=0;
        while (i<titre.length){
            String titrH = titre[i].substring(0, titre[i].length()-4);
            lohatenyHira.add(titrH);
            i++;
        }

        ArrayAdapter<String> hiraAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, lohatenyHira);
        listHira.setAdapter(hiraAdapter);

        return v;
    }

    public String[] makaLohateninkira(){
        String titrHira[] = new String[100];
        AssetManager am = getActivity().getAssets();
        try{
            titrHira = am.list("hirao");

            if(titrHira.length > 0) {
                titrHira = titrHira;
            }
            else {
                String tost = "dossier vide";
                Toast.makeText(getActivity(), tost+","+titrHira.length, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            String tost = "erreur d'ouverture";
            Toast.makeText(getActivity(),tost,Toast.LENGTH_SHORT).show();
            titrHira = null;
        }
        return  titrHira;
    }
    public String  mamakyTononkira(String name){

        String s="";
        try {

            AssetManager am = getActivity().getAssets();

            InputStream is = am.open("hirao/"+name+".txt");


            InputStreamReader InputRead= new InputStreamReader(is);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();

        } catch (Exception e) {
            e.printStackTrace();
            s=" Erreur: le fichier n'a pas trouvé";
        }
        return s;
    }
}
