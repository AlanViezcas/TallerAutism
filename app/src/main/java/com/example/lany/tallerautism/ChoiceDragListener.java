package com.example.lany.tallerautism;

import android.animation.ObjectAnimator;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;

/**
 * Created by Lany on 24/03/2018.
 */

public class ChoiceDragListener implements View.OnDragListener {

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
               ImageView view = (ImageView) event.getLocalState();
               // ViewGroup palabras = (ViewGroup) event.getLocalState();
               ((ImageView)view).setImageDrawable(null);
               //((ViewGroup)palabras).setLayoutMode(0);



                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
        }
        return true;
    }

}
