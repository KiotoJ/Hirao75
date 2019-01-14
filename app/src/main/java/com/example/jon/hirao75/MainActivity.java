package com.example.jon.hirao75;

import android.content.res.AssetManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SearchView searchView;
    private ImageButton btn_revert;

    private int[] tabIcons={R.drawable.page_sons,
            R.drawable.tanana};
    static final int READ_BLOCK_SIZE = 100;
    private ListView listHira;
    private TextView affichHira;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.hirao);
        actionBar.setDisplayHomeAsUpEnabled(true);

        listHira = (ListView) findViewById(R.id.listHira);
        affichHira = (TextView) findViewById(R.id.affichHira);

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

        final ArrayAdapter<String> hiraAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lohatenyHira);
        listHira.setAdapter(hiraAdapter);
        listHira.setTextFilterEnabled(true);
        /*viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);*/

        btn_revert = (ImageButton) findViewById(R.id.btn_revert);
        btn_revert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Revenir", Toast.LENGTH_SHORT).show();
                affichHira.setText("");
                affichHira.setVisibility(View.INVISIBLE);
                listHira.setVisibility(View.VISIBLE);
            }
        });
        /*tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);*/
        //setupTabIcons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.act_cherch);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        //searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // Toast.makeText(getApplicationContext(), "submission", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast.makeText(getApplicationContext(), "Recherche", Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(newText)) {
                    listHira.clearTextFilter();
                } else {
                    //
                    listHira.setFilterText(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            /*case R.id.act_settings:
                return true;*/
            case R.id.act_version:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Version");
                builder.setCancelable(true);
                builder.setMessage(getResources().getString(R.string.a_proposText));
                builder.setPositiveButton("OK", null);
               // builder.setNegativeButton("Cancel", null);
                builder.show();
                return true;
            case R.id.quit:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void setupViewPager(ViewPager viewPager){
        //VueAdapterPage adapter = new VueAdapterPage(getSupportFragmentManager());
        //adapter.addFragment(new PageFragment(), "Hira");
        //adapter.addFragment(new ApropDeNous(), "À propos");
        //viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        //tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        //tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        //tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    public String[] makaLohateninkira(){
        String titrHira[] = new String[100];
        AssetManager am = this.getAssets();
        try{
            titrHira = am.list("hirao");

            if(titrHira.length > 0) {
                titrHira = titrHira;
            }
            else {
                String tost = "dossier vide";
                Toast.makeText(this, tost+","+titrHira.length, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            String tost = "erreur d'ouverture";
            Toast.makeText(this,tost,Toast.LENGTH_SHORT).show();
            titrHira = null;
        }
        return  titrHira;
    }
    public String  mamakyTononkira(String name){
        String s="";
        try {

            AssetManager am = this.getAssets();

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
