package br.com.raphael.projetoplanetas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlanetasRecyclerAdapter extends RecyclerView.Adapter<PlanetasRecyclerAdapter.PlanetasViewHolder> {

    private final Context context;
    private PlanetaOnClickListener clickListener;
    private final List<Planeta> planetas;

    private ProjetoPlanetasApplication application;
    private boolean gridLayout;

    public PlanetasRecyclerAdapter(Context context, boolean gridLayout) {
        this.context = context;
        this.application = (ProjetoPlanetasApplication) context.getApplicationContext();
        this.planetas = application.getPlanetas();
        this.gridLayout = gridLayout;
    }

    @Override
    public PlanetasRecyclerAdapter.PlanetasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        if(gridLayout){
            view = inflater.inflate(R.layout.grid_item, parent, false);
        }else{
            view = inflater.inflate(R.layout.list_item, parent, false);
        }
        return new PlanetasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlanetasRecyclerAdapter.PlanetasViewHolder holder, int position) {

        Planeta planeta = planetas.get(position);

        holder.imageView.setImageDrawable(context.getResources().getDrawable(planeta.getImg()));
        holder.textView.setText(planeta.getNome());

    }

    @Override
    public int getItemCount() {
        return planetas.size();
    }

    public void setClickListener(PlanetaOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setGridLayout(boolean gridLayout) {
        this.gridLayout = gridLayout;
    }

    public class PlanetasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private View viewList;
        private View viewGrid;

        private ImageView imageView;
        private TextView textView;

        public PlanetasViewHolder(View itemView) {
            super(itemView);
            viewList = itemView.findViewById(R.id.listView);
            viewList.setOnClickListener(this);

            viewGrid = itemView.findViewById(R.id.gridView);
            viewGrid.setOnClickListener(this);

            imageView = (ImageView) itemView.findViewById(R.id.iconPlaneta);
            textView = (TextView) itemView.findViewById(R.id.textView);

        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onClickPlaneta(v, getPosition());
            }
        }

        /* O padrão ViewHolder tamém é utilizado no ListView, porém é um pouco complicado de fazê-lo.
         * Já que daqui pra frente SEMPRE devem utilizar o RecyclerView devido sua forma mais dinamica de
         * personalização, irei tentar explicar o padrão ViewHolder, ok?!
         *
         * Quando o Android faz a rolagem em uma lista com uma grande quantidade de elementos,
         * é preciso reutilizar as views para evitar criar uma nova a cada imagem. Imagine que
         * existe uma lista com 1000 linhas. Como o Android mostra apenas no máximo cerca de 10
         * views por vez, podemos criar apenas 10 views e reutiliza-las ao fazer a rolagem, em vez
         * de criar 1000 objetos do tipo view. Isso otimiza a memoria e deixa a rolagem da lista
         * mais fluída. Utilizando o RecyclerView este padrão é mais transparente ao desenvolvedor.
         */
    }
}
