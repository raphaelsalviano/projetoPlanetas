package br.com.raphael.projetoplanetas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private GridView gridView;

    private PlanetaAdapter adapter;

    private ProjetoPlanetasApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        application = (ProjetoPlanetasApplication) getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new PlanetaAdapter(this);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                application.addPlaneta();
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.actionGrid){
            if (adapter.isGrid()){
                item.setTitle("LISTA");
                adapter.setIsGrid(true);
                listView.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);
            }else{
                item.setTitle("GRADE");
                adapter.setIsGrid(false);
                listView.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
            }

            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}
