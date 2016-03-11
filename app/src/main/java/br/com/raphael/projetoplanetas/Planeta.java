package br.com.raphael.projetoplanetas;

/**
 * Created by raphaelsalviano on 10/03/16.
 */
public class Planeta {

    private String nome;
    private int img;

    public Planeta(String nome, int img) {
        this.nome = nome;
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
