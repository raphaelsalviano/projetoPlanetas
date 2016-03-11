package br.com.raphael.projetoplanetas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlanetaAdapter extends BaseAdapter {

    private Context context;
    private List<Planeta> planetas;

    private ProjetoPlanetasApplication application;
    private boolean isGrid = false;

    public PlanetaAdapter(Context context) {
        this.context = context;
        application = (ProjetoPlanetasApplication) context.getApplicationContext();
        planetas = application.getPlanetas();
    }

    @Override
    public int getCount() {
        return planetas.size();
    }

    @Override
    public Object getItem(int position) {
        return planetas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if(isGrid){
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        Planeta planeta = planetas.get(position);

        ((ImageView) view.findViewById(R.id.iconPlaneta)).setImageDrawable(context.getResources().getDrawable(planeta.getImg()));
        ((TextView) view.findViewById(R.id.textView)).setText(planeta.getNome());

        return view;
    }

    public void setIsGrid(boolean isGrid) {
        this.isGrid = isGrid;
    }

    public boolean isGrid() {
        return isGrid;
    }
}
