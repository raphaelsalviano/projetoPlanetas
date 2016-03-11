package br.com.raphael.projetoplanetas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class RecyclerViewActivity extends AppCompatActivity implements PlanetaOnClickListener{

    private RecyclerView recyclerView;
    private PlanetasRecyclerAdapter adapter;

    private ProjetoPlanetasApplication application;
    private boolean mListView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        application = (ProjetoPlanetasApplication) getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new PlanetasRecyclerAdapter(this, mListView); // Adapter do RecyclerView
        adapter.setClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler); // Captura o recyclerView do xml
        // Envio um tipo de Layout para o recyclerView
        // Uma vez que ele pode ser adaptavel para o formato
        // de lista(LinearLayoutManager) ou grade(GridLayoutManager)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // Envio o item de animação padrão
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                application.addPlaneta();
                adapter.notifyDataSetChanged();
                Snackbar.make(view, "Adicionou um Planeta", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClickPlaneta(View view, int position) {
        application.removerPlaneta(position);
        adapter.notifyDataSetChanged();
        Snackbar.make(view, "Removeu o platena: " + position, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.actionGrid){
            gridLayout(mListView, item);
        }

        return super.onOptionsItemSelected(item);
    }

    private void gridLayout(boolean sucess, MenuItem item) {
        if (sucess) {
            item.setTitle("LISTA");
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter.setGridLayout(true);
            adapter.notifyDataSetChanged();
            mListView = false;
        } else {
            item.setTitle("GRADE");
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setGridLayout(false);
            adapter.notifyDataSetChanged();
            mListView = true;
        }
    }

}
