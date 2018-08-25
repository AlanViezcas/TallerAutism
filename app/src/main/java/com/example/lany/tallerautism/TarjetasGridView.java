package com.example.lany.tallerautism;


import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.transform.Result;

public class TarjetasGridView extends AppCompatActivity implements View.OnClickListener  {

    ///////////////////////////////////////////////
    // Componentes para el Grid (Zona donde se presentan las imagenes de tres en tres)
     ViewStub stubGrid;
     GridView gridView;
     GridViewAdapter gridViewAdapter;
     List<Tarjetas> tarjetaList;
     LinearLayout borrar;
     ////////////////////////////////////////////
     //Componentes para sintetizar la voz
    TextToSpeech toSpeech;
    int result;
    String texto;
    String cadena;

    ////////////////////////////////////////////////////
    //Imagenes dentro del scrollView vertical
    ImageButton img1, img2, img3, img4, img5, img6, img7, img8, img9;

    /////////////////////////////////////////////////////
    //Imagenes transparentes de la parte superior de la pantalla, horizontal
    ImageView imgScreen1, imgScreen2, imgScreen3, imgScreen4;
    int imagen;

    /////////////////////////////////////////////////////
    public String palabra1 = " ", palabra2 = " ", palabra3 = " ", palabra4 = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjetasgridview);

///////////////////////// seccion de sintesis de voz
        toSpeech = new TextToSpeech(TarjetasGridView.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    //Se define el idioma en que hablara en este caso con el que este configurado el dispositivo
                    result = toSpeech.setLanguage(Locale.getDefault());
                }else {
                    Toast.makeText(TarjetasGridView.this, "Tu celular no soporta esta caracteristica", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ////////////////Layout donde se desvanecen las tarjetas
        borrar =(LinearLayout)findViewById(R.id.lotBorrar);



        //Se conetan las variables con su imagen correspondiente en el layout
        img1 = (ImageButton)findViewById(R.id.img1);
        img2 = (ImageButton)findViewById(R.id.img2);
        img3 = (ImageButton)findViewById(R.id.img3);
        img4 = (ImageButton)findViewById(R.id.img4);
        img5 = (ImageButton)findViewById(R.id.img5);
        img6 = (ImageButton)findViewById(R.id.img6);
        img7 = (ImageButton)findViewById(R.id.img7);
        img8 = (ImageButton)findViewById(R.id.img8);
        img9 = (ImageButton)findViewById(R.id.img9);

        imgScreen1 = (ImageView)findViewById(R.id.imgScreen1);
        imgScreen2 = (ImageView)findViewById(R.id.imgScreen2);
        imgScreen3 = (ImageView)findViewById(R.id.imgScreen3);
        imgScreen4 = (ImageView)findViewById(R.id.imgScreen4);


        ///////////////////Hace que la imagen se pueda mover llamando a la clase ChoiceTeamListener
        imgScreen1.setOnTouchListener(new ChoiceTeamListener());
        imgScreen2.setOnTouchListener(new ChoiceTeamListener());
        imgScreen3.setOnTouchListener(new ChoiceTeamListener());
        imgScreen4.setOnTouchListener(new ChoiceTeamListener());
        //////////////////////////////////////////////////////////////////////////////////////////
        /////Hace que la imagen se vuelva null al entratar al layout, llamando a ChoiceDragListrener
        borrar.setOnDragListener(new ChoiceDragListener());


        //////////////////////////////////////////////////////////////////////////////////////////


        stubGrid = (ViewStub)findViewById(R.id.stub_grid);
        stubGrid.inflate();
        gridView = (GridView)findViewById(R.id.mygridview);

       //Primera listr
        getListaDeseos();

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);
        img7.setOnClickListener(this);
        img8.setOnClickListener(this);
        img9.setOnClickListener(this);

        gridView.setOnItemClickListener(onItemClick);
        stubGrid.setVisibility(View.VISIBLE);
        setAdapters();

    }
///////////////////////// tres puntos para reproducir
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mi_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        ///////Filtros de la tarjeta///////////

        if (item.getItemId() == R.id.item1){
            if (imgScreen1.getDrawable() == null
                    && imgScreen2.getDrawable() == null
                    && imgScreen3.getDrawable() == null
                    && imgScreen4.getDrawable() == null ){
                cadena = " ";
            }else
            if (imgScreen1.getDrawable() == null
                    && imgScreen2.getDrawable() == null && !palabra2.equals(" ")
                    && imgScreen3.getDrawable() == null)
            {
                cadena = palabra4;
            } else

            if (imgScreen1.getDrawable() == null
                    && imgScreen2.getDrawable() == null && !palabra2.equals(" ")
                    && imgScreen4.getDrawable() == null && !palabra4.equals(" "))
            {
                cadena = palabra3;
            }else

            if (imgScreen1.getDrawable() == null
                    && imgScreen2.getDrawable() == null)
            {
                cadena = palabra3+ " " + palabra4;
            }else

            if (imgScreen1.getDrawable() == null
                    && imgScreen3.getDrawable() == null
                    && imgScreen4.getDrawable() == null)
            {
                cadena = palabra2;
            }

            if (imgScreen1.getDrawable() == null
                    && imgScreen3.getDrawable() == null
                    )
            {
                cadena = palabra2 + " " + palabra4;
            }else

            if (imgScreen1.getDrawable() == null
                    && imgScreen4.getDrawable() == null)
            {
                cadena = palabra2 + " " + palabra3;
            }else

            if (imgScreen1.getDrawable() == null)
            {
                cadena = palabra2 + " " + palabra3 + " " + palabra4;
            }else

            if (imgScreen2.getDrawable() == null && imgScreen3.getDrawable() == null && imgScreen4.getDrawable() == null)
            {
                cadena = palabra1;
            }else

            if (imgScreen3.getDrawable() == null && imgScreen4.getDrawable() == null)
            {
                cadena = palabra1+ " "+ palabra2;
            }else

            if (imgScreen2.getDrawable() == null && imgScreen4.getDrawable() == null)
            {
                cadena = palabra1+ " "+ palabra3;
            }else

            if (imgScreen2.getDrawable() == null)
            {
                cadena = palabra1+ " "+ palabra3 + " " +palabra4;
            }else

            if (imgScreen3.getDrawable() == null && imgScreen4.getDrawable() == null)
            {
                cadena = palabra1+ " "+ palabra2;
            }else

            if (imgScreen3.getDrawable() == null)
            {
                cadena = palabra1+ " "+ palabra2 + " " +palabra4;
            }else

            if (imgScreen4.getDrawable() == null)
            {
                cadena = palabra1+ " "+ palabra2 + " " +palabra3;
            }

           TTS2();

            return true;
        }
        return super.onOptionsItemSelected(item);


    }

    ///////////////////////////////////////////////
    private void setAdapters() {
        gridViewAdapter = new GridViewAdapter(this, R.layout.item_tarjetas, tarjetaList);
        gridView.setAdapter(gridViewAdapter);

    }

    public List<Tarjetas> getListaDeseos() {
       tarjetaList = new ArrayList<>();
       tarjetaList.add(new Tarjetas(R.drawable.si, "Sí", 10));
       tarjetaList.add(new Tarjetas(R.drawable.no, "No",10));
        tarjetaList.add(new Tarjetas(R.drawable.quiero, "Quiero",10));
        tarjetaList.add(new Tarjetas(R.drawable.noquiero, "No quiero",10));
        tarjetaList.add(new Tarjetas(R.drawable.salir, "Salir",10));
        tarjetaList.add(new Tarjetas(R.drawable.abrazo, "Abrazar",10));
        tarjetaList.add(new Tarjetas(R.drawable.ir, "Ir",10));
        tarjetaList.add(new Tarjetas(R.drawable.parar, "Parar",10));

        return tarjetaList;
    }

    public List<Tarjetas> getListaLugares() {
        tarjetaList = new ArrayList<>();
        tarjetaList.add(new Tarjetas(R.drawable.casa, "Casa", 10));
        tarjetaList.add(new Tarjetas(R.drawable.bano, "Baño",10));
        tarjetaList.add(new Tarjetas(R.drawable.banera, "Bañera",10));
        tarjetaList.add(new Tarjetas(R.drawable.sala, "Sala",10));
        tarjetaList.add(new Tarjetas(R.drawable.cocina, "Cocina",10));
        tarjetaList.add(new Tarjetas(R.drawable.cama, "Cama",10));
        tarjetaList.add(new Tarjetas(R.drawable.hospital, "Hospital",10));
        tarjetaList.add(new Tarjetas(R.drawable.parque, "Parque",10));
        tarjetaList.add(new Tarjetas(R.drawable.escaleras, "Escaleras",10));

        return tarjetaList;
    }

    public List<Tarjetas> getListaAcciones() {
        tarjetaList = new ArrayList<>();
        tarjetaList.add(new Tarjetas(R.drawable.comer, "Comer", 10));
        tarjetaList.add(new Tarjetas(R.drawable.beber, "Beber",10));
        tarjetaList.add(new Tarjetas(R.drawable.hablar, "Hablar",10));
        tarjetaList.add(new Tarjetas(R.drawable.quitar, "Quitarme",10));
        tarjetaList.add(new Tarjetas(R.drawable.poner, "Ponerme",10));
        tarjetaList.add(new Tarjetas(R.drawable.abrir, "Abrir",10));
        tarjetaList.add(new Tarjetas(R.drawable.cerrar, "Cerrar",10));
        tarjetaList.add(new Tarjetas(R.drawable.sentar, "Sentar",10));
        tarjetaList.add(new Tarjetas(R.drawable.levantar, "Levantarme",10));
        tarjetaList.add(new Tarjetas(R.drawable.correr, "Correr",10));
        tarjetaList.add(new Tarjetas(R.drawable.dormir, "Dormir",10));

        return tarjetaList;
    }

    public List<Tarjetas> getListaSentimientos() {
        tarjetaList = new ArrayList<>();
        tarjetaList.add(new Tarjetas(R.drawable.triste, "Triste", 10));
        tarjetaList.add(new Tarjetas(R.drawable.feliz, "Feliz",10));
        tarjetaList.add(new Tarjetas(R.drawable.aburrido, "Aburrido",10));
        tarjetaList.add(new Tarjetas(R.drawable.asco, "Asco",10));
        tarjetaList.add(new Tarjetas(R.drawable.dolor, "Dolor",10));
        tarjetaList.add(new Tarjetas(R.drawable.miedo, "Miedo",10));
        tarjetaList.add(new Tarjetas(R.drawable.verguenza, "Verguenza",10));
        tarjetaList.add(new Tarjetas(R.drawable.amor, "Amor",10));
        tarjetaList.add(new Tarjetas(R.drawable.ira, "Ira",10));

        return tarjetaList;
    }

    public List<Tarjetas> getListaComida() {
        tarjetaList = new ArrayList<>();
        tarjetaList.add(new Tarjetas(R.drawable.agua, "Agua", 10));
        tarjetaList.add(new Tarjetas(R.drawable.leche, "Leche",10));
        tarjetaList.add(new Tarjetas(R.drawable.jugo, "Jugo",10));
        tarjetaList.add(new Tarjetas(R.drawable.soda, "Soda",10));
        tarjetaList.add(new Tarjetas(R.drawable.te, "Té",10));
        tarjetaList.add(new Tarjetas(R.drawable.pan, "Pan",10));
        tarjetaList.add(new Tarjetas(R.drawable.platano, "Platano",10));
        tarjetaList.add(new Tarjetas(R.drawable.dulces, "Dulces",10));
        tarjetaList.add(new Tarjetas(R.drawable.sandwich, "Sándwich",10));
        tarjetaList.add(new Tarjetas(R.drawable.hamburguesa, "Hamburguesa",10));
        tarjetaList.add(new Tarjetas(R.drawable.pizza, "Pizza",10));
        tarjetaList.add(new Tarjetas(R.drawable.espaguetis, "Espagueti",10));
        tarjetaList.add(new Tarjetas(R.drawable.helado, "Helado",10));
        tarjetaList.add(new Tarjetas(R.drawable.pastel, "Pastel",10));
        tarjetaList.add(new Tarjetas(R.drawable.sopa, "Sopa",10));
        tarjetaList.add(new Tarjetas(R.drawable.palomitas, "Palomitas",10));
        tarjetaList.add(new Tarjetas(R.drawable.taco, "Taco",10));
        tarjetaList.add(new Tarjetas(R.drawable.burrito, "Burrito",10));



        return tarjetaList;
    }

    public List<Tarjetas> getListaRopa() {
        tarjetaList = new ArrayList<>();
        tarjetaList.add(new Tarjetas(R.drawable.camiseta, "Camiseta",10));
        tarjetaList.add(new Tarjetas(R.drawable.camisa, "Camisa",10));
        tarjetaList.add(new Tarjetas(R.drawable.pantalon, "Pantalon",10));
        tarjetaList.add(new Tarjetas(R.drawable.falda, "Falda",10));
        tarjetaList.add(new Tarjetas(R.drawable.zapatos, "Zapatos",10));
        tarjetaList.add(new Tarjetas(R.drawable.tenis, "Tenis",10));
        tarjetaList.add(new Tarjetas(R.drawable.mochila, "Mochila",10));
        tarjetaList.add(new Tarjetas(R.drawable.gorra, "Gorra",10));
        tarjetaList.add(new Tarjetas(R.drawable.calzon, "Calzon",10));
        tarjetaList.add(new Tarjetas(R.drawable.calcetines, "Calcetines",10));

        return tarjetaList;
    }

    public List<Tarjetas> getListaCuerpo() {
        tarjetaList = new ArrayList<>();
        tarjetaList.add(new Tarjetas(R.drawable.cara, "Cara",10));
        tarjetaList.add(new Tarjetas(R.drawable.ojos, "Ojos",10));
            tarjetaList.add(new Tarjetas(R.drawable.nariz, "Nariz",10));
        tarjetaList.add(new Tarjetas(R.drawable.boca, "Boca",10));
        tarjetaList.add(new Tarjetas(R.drawable.oreja, "Oreja",10));
        tarjetaList.add(new Tarjetas(R.drawable.mano, "Mano",10));
        tarjetaList.add(new Tarjetas(R.drawable.dedos, "Dedos",10));
        tarjetaList.add(new Tarjetas(R.drawable.pie, "Pie",10));
        tarjetaList.add(new Tarjetas(R.drawable.brazo, "Brazo",10));
        tarjetaList.add(new Tarjetas(R.drawable.rodilla, "Rodilla",10));
        tarjetaList.add(new Tarjetas(R.drawable.dientes, "Dientes",10));

        return tarjetaList;
    }

    public List<Tarjetas> getListaAnimales() {
        tarjetaList = new ArrayList<>();
        tarjetaList.add(new Tarjetas(R.drawable.perro, "Perro",10));
        tarjetaList.add(new Tarjetas(R.drawable.gato, "Gato",10));
        tarjetaList.add(new Tarjetas(R.drawable.paloma, "Paloma",10));
        tarjetaList.add(new Tarjetas(R.drawable.cucaracha, "Cucaracha",10));
        tarjetaList.add(new Tarjetas(R.drawable.pez, "Pez",10));
        tarjetaList.add(new Tarjetas(R.drawable.mariposa, "Mariposa",10));
        tarjetaList.add(new Tarjetas(R.drawable.mosquito, "Mosquito",10));
        tarjetaList.add(new Tarjetas(R.drawable.mosca, "Mosca",10));


        return tarjetaList;
    }

    public List<Tarjetas> getProductList2() {
        tarjetaList = new ArrayList<>();
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 1",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 2",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 3",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 4",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 5",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 6",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 7",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 8",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 9",10));
        tarjetaList.add(new Tarjetas(R.drawable.pika, "Title 10",10));

        return tarjetaList;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img1:
                getProductList2();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;
            case R.id.img2:
               getListaDeseos();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;

            case R.id.img3:
                getListaLugares();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;

            case R.id.img4:
                getListaAcciones();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;

            case R.id.img5:
                getListaSentimientos();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;

            case R.id.img6:
                getListaComida();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;

            case R.id.img7:
                getListaRopa();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;

            case R.id.img8:
                getListaCuerpo();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;

            case R.id.img9:
                getListaAnimales();
                gridView.setOnItemClickListener(onItemClick);
                stubGrid.setVisibility(View.VISIBLE);
                setAdapters();
                break;


        }
    }
    // Seleccion de cada elemento del Grid
            AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           // Toast.makeText(getApplicationContext(), tarjetaList.get(position).getTitulo() + " - " , Toast.LENGTH_SHORT).show();

           imagen = tarjetaList.get(position).getImageId();
           texto = tarjetaList.get(position).getTitulo();

           TTS();
            if (imgScreen1.getDrawable() == null){
                imgScreen1.setImageResource(imagen);
                palabra1 = texto;
            } else if(imgScreen2.getDrawable() == null){
                imgScreen2.setImageResource(imagen);
                palabra2 = texto;
            }else if (imgScreen3.getDrawable()== null){
                imgScreen3.setImageResource(imagen);
                palabra3 = texto;
            }else if(imgScreen4.getDrawable()== null){
                imgScreen4.setImageResource(imagen);
                palabra4 = texto;
            }

            cadena = palabra1 + " " + palabra2 + " " + palabra3 + " " +palabra4;



        }



    };

        public void TTS(){

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED ){
                Toast.makeText(this, "Tu celular no soporta esta caracteristica", Toast.LENGTH_SHORT).show();
            }else

                toSpeech.speak(texto,TextToSpeech.QUEUE_FLUSH,null);
        }

    public void TTS2(){

        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED ){
            Toast.makeText(this, "Tu celular no soporta esta caracteristica",
                    Toast.LENGTH_SHORT).show();
        }else

            toSpeech.speak(cadena,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toSpeech!=null) {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }
}


//ionic1-70a94
/*

<script src="https://www.gstatic.com/firebasejs/5.0.2/firebase.js"></script>
<script>
// Initialize Firebase
  var config = {
          apiKey: "AIzaSyBu40Ij7jMQNO--Jo8OCXKKwu8SdJNiIj8",
          authDomain: "ionic1-70a94.firebaseapp.com",
          databaseURL: "https://ionic1-70a94.firebaseio.com",
          projectId: "ionic1-70a94",
          storageBucket: "ionic1-70a94.appspot.com",
          messagingSenderId: "1070631711499"
          };
          firebase.initializeApp(config);

</script>*/

