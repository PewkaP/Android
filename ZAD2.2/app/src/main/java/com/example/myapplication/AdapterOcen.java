package com.example.myapplication;

import android.app.Activity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterOcen extends RecyclerView.Adapter<AdapterOcen.OcenyViewHolder> {
    private List<ModelOceny> mListaOcen;
    private LayoutInflater mPompka;
    public AdapterOcen(Activity kontekst, List<ModelOceny> listaOcen){
        mPompka = kontekst.getLayoutInflater();
        this.mListaOcen = listaOcen;
    }
    @NotNull
    @Override
    public OcenyViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int viewType){
        View widokElementu = mPompka.inflate(R.layout.wiersz_ocen, viewGroup, false);
        return new OcenyViewHolder(widokElementu);
    }
    @Override
    public void onBindViewHolder(@NotNull OcenyViewHolder ocenyViewHolder, int numerWiersza){
        ModelOceny ocena = mListaOcen.get(numerWiersza);
        ocenyViewHolder.radioGroup.setTag(numerWiersza); // Przypisanie tagu do przechowywania numeru wiersza
        ocenyViewHolder.textViewPrzedmiot.setText(ocena.getNazwa()); // Ustawienie nazwy przedmiotu
        ocenyViewHolder.radioButton2.setChecked(ocena.getOcena() == 2);
        ocenyViewHolder.radioButton3.setChecked(ocena.getOcena() == 3);
        ocenyViewHolder.radioButton4.setChecked(ocena.getOcena() == 4);
        ocenyViewHolder.radioButton5.setChecked(ocena.getOcena() == 5);
    }
    @Override
    public int getItemCount(){
        return mListaOcen.size();
    }
    public Pair<Boolean, Double> getAverage(){
        double sum=0;
        boolean flag=true;
        for(ModelOceny ocena : mListaOcen){
            if(ocena.getOcena()>=2 && ocena.getOcena()<=5)
                sum+=ocena.getOcena();
            else
                flag=false;
        }
        return new Pair<Boolean,Double>(flag,sum/mListaOcen.size());
    }
    public class OcenyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {
        private RadioGroup radioGroup;
        private TextView textViewPrzedmiot;
        private RadioButton radioButton2, radioButton3, radioButton4, radioButton5;

        public OcenyViewHolder(@NotNull View glownyElementWiersza){
            super(glownyElementWiersza);
            radioGroup = glownyElementWiersza.findViewById(R.id.radioGroup);
            textViewPrzedmiot = glownyElementWiersza.findViewById(R.id.textViewPrzedmiot);
            radioButton2 = glownyElementWiersza.findViewById(R.id.radioButton2);
            radioButton3 = glownyElementWiersza.findViewById(R.id.radioButton3);
            radioButton4 = glownyElementWiersza.findViewById(R.id.radioButton4);
            radioButton5 = glownyElementWiersza.findViewById(R.id.radioButton5);

            radioGroup.setOnCheckedChangeListener(this);
        }
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int numerWiersza = (int) group.getTag(); // Pobranie numeru wiersza z tagu
            ModelOceny ocena = mListaOcen.get(numerWiersza);
            if (checkedId == R.id.radioButton2) {
                ocena.setOcena(2);
            } else if (checkedId == R.id.radioButton3) {
                ocena.setOcena(3);
            } else if (checkedId == R.id.radioButton4) {
                ocena.setOcena(4);
            } else if (checkedId == R.id.radioButton5) {
                ocena.setOcena(5);
            }
        }
    }
}
