package com.example.lany.tallerautism;

/**
 * Created by Lany on 19/03/2018.
 */

public class Tarjetas  {
    private int imageId;
    private String titulo;
    public int audio;

     public Tarjetas (int imageId, String titulo, int audio){
         this.imageId = imageId;
         this.titulo = titulo;
         this.audio = audio;
     }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }
}
