package br.com.raphael.projetoplanetas;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class ProjetoPlanetasApplication extends Application {

    private List<Planeta> planetas;
    private int cont = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        planetas = new ArrayList<>();
        criarPlanetas();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public List<Planeta> getPlanetas() {
        return planetas;
    }

    private void criarPlanetas(){
        planetas.add(new Planeta("Mercurio", R.drawable.mercurio));
        planetas.add(new Planeta("Venus", R.drawable.venus));
        planetas.add(new Planeta("Terra", R.drawable.terra));
        planetas.add(new Planeta("Marte", R.drawable.marte));
        planetas.add(new Planeta("Saturno", R.drawable.saturno));
        planetas.add(new Planeta("Jupter", R.drawable.jupter));
        planetas.add(new Planeta("Urano", R.drawable.urano));
        planetas.add(new Planeta("Netuno", R.drawable.netuno));
    }

    public void addPlaneta() {
        planetas.add(planetas.get(cont));
        cont++;
    }
}
