package id.sch.smktelkom_mlg.tugas01.xiirpl2031.perpustakaantulungagung;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import id.sch.smktelkom_mlg.tugas01.xiirpl2031.perpustakaantulungagung.adapter.KotaAdapter;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etNama, etTahun, etLama;
    RadioGroup rgJK;
    CheckBox cbP, cbE, cbH, cbPD, cbA;
    Spinner spP, spK;
    TextView tvHasil;
    int hobi;
    String[][]arKota= {{"Bandung", "Cirebon", "Bekasi"},
            {"Jakarta Pusat", "Jakarta Timur", "Jakarta Barat", "Jakarta Utara", "Jakarta Selatan"},
            {"Banten"},
            {"Semarang", "Magelang", "Surakarta"},
            {"Yogjakarta"},
            {"Surabaya", "Malang", "Blitar"}, {"Denpasar"}};
    ArrayList<String> listKota = new ArrayList<>();
    KotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTahun = (EditText) findViewById(R.id.editTextTahun);
        etLama = (EditText) findViewById(R.id.editTextLama);
        rgJK = (RadioGroup) findViewById(R.id.radiogroupJK);
        cbP = (CheckBox) findViewById(R.id.checkBoxP);
        cbE = (CheckBox) findViewById(R.id.checkBoxE);
        cbH = (CheckBox) findViewById(R.id.checkBoxH);
        cbPD = (CheckBox) findViewById(R.id.checkBoxPD);
        cbA = (CheckBox) findViewById(R.id.checkBoxA);
        cbP.setOnCheckedChangeListener(this);
        cbE.setOnCheckedChangeListener(this);
        cbH.setOnCheckedChangeListener(this);
        cbPD.setOnCheckedChangeListener(this);
        cbA.setOnCheckedChangeListener(this);
        spP = (Spinner) findViewById(R.id.spinnerProv);
        spK = (Spinner) findViewById(R.id.spinnerKota);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        adapter = new KotaAdapter(this, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spK.setAdapter(adapter);

        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });
        spP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[pos]));
                adapter.setProvinsi((String)spP.getItemAtPosition(pos));
                adapter.notifyDataSetChanged();
                spK.setSelection(0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) hobi+=1;
        else hobi-=1;
    }

    private void doClick() {
        String nama = etNama.getText().toString();
        int tahun = Integer.parseInt(etTahun.getText().toString());
        int usia = 2016 - tahun;
        String hasil = null;

        StringBuilder builder = new StringBuilder();
        builder.append("Nama Anda : ");
        builder.append(nama + "\n");

        builder.append("Wilayah Provinsi ");
        builder.append(spP.getSelectedItem().toString());
        builder.append(" Kota ");
        builder.append(spK.getSelectedItem().toString() + "\n");

        if (rgJK.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton) findViewById(rgJK.getCheckedRadioButtonId());
            hasil = rb.getText().toString();
        }
        builder.append("Jenis kelamin anda : ");
        builder.append(hasil + "\n");

        builder.append("Anda lahir tahun " + tahun);
        builder.append(" usia anda sekarang : ");
        builder.append(usia + " tahun\n");

        String buku = "Buku yang anda pinjam : \n";
        int startlen = buku.length();
        if (cbP.isChecked()) buku += cbP.getText() + "\n";
        if (cbE.isChecked()) buku += cbE.getText() + "\n";
        if (cbH.isChecked()) buku += cbH.getText() + "\n";
        if (cbPD.isChecked()) buku += cbPD.getText() + "\n";
        if (cbA.isChecked()) buku += cbA.getText() + "\n";
        if (buku.length() == startlen) buku += "Tidak ada buku yang dipilih";
        builder.append(buku);

        builder.append("Anda meminjam buku sebanyak " + hobi + " buku\n");

        int lama = Integer.parseInt(etLama.getText().toString());
        int telat = lama - 3;
        int pinjam = telat * hobi * 5000;
        if (telat <= 0) {
            builder.append("Anda mengembalikan buku tepat waktu!");
        } else {
            builder.append("Anda Terlambat mengembalikan buku selama " + telat + " hari\n");
            builder.append("Anda mendapat denda : " + pinjam + "\n");
        }


        tvHasil.setText(builder);
    }


}
