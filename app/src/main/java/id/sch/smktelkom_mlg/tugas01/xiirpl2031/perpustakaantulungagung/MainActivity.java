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
    TextView tvHasil, tv2, tvh2, tvh3;
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
        tv2 = (TextView) findViewById(R.id.textView);
        tvh2 = (TextView) findViewById(R.id.textViewJN);
        tvh3 = (TextView) findViewById(R.id.textViewBK);
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
        if (isValid()) {
            String nama = etNama.getText().toString();
            int tahun = Integer.parseInt(etTahun.getText().toString());
            int usia = 2016 - tahun;
            String hasil = null;
            if (rgJK.getCheckedRadioButtonId() != -1) {
                RadioButton rb = (RadioButton) findViewById(rgJK.getCheckedRadioButtonId());
                hasil = rb.getText().toString();
            }
            if (hasil==null)
            {
                tvh2.setVisibility(View.VISIBLE);
                tvh2.setText("Anda Belum Memilih Jenis Kelamin");
            }
            else {
                tvh2.setVisibility(View.GONE);
                String buku = "\tBuku yang dipinjam \t\n";
                int startlen = buku.length();
                if (cbP.isChecked()) buku += "\t\t -> " +cbP.getText() + "\n";
                if (cbE.isChecked()) buku += "\t\t -> " +cbE.getText() + "\n";
                if (cbH.isChecked()) buku += "\t\t -> " +cbH.getText() + "\n";
                if (cbPD.isChecked()) buku += "\t\t -> " +cbPD.getText() + "\n";
                if (cbA.isChecked()) buku += "\t\t -> " +cbA.getText() + "\n";
                if (buku.length() == startlen) {
                    tvh3.setVisibility(View.VISIBLE);
                    tvh3.setText("Tidak ada buku yang dipilih");
                }
                else {
                    tvh3.setVisibility(View.GONE);
                    StringBuilder builder = new StringBuilder();
                    builder.append("\nFORM PENGEMBALIAN BUKU\n\n");

                    builder.append("\tNama\t\t\t\t\t : \t");
                    builder.append(nama + "\n");

                    builder.append("\tAlamat\n");
                    builder.append("\t\tProvinsi\t\t\t\t : \t");
                    builder.append(spP.getSelectedItem().toString() + "\n");
                    builder.append("\t\tKota\t\t\t\t\t : \t");
                    builder.append(spK.getSelectedItem().toString() + "\n");

                    builder.append("\tJenis kelamin\t\t\t : \t");
                    builder.append(hasil + "\n");

                    builder.append("\tUsia\t\t\t\t\t\t : \t" + usia +" tahun ");
                    builder.append("( lahir tahun " + tahun + " )\n");

                    int lama = Integer.parseInt(etLama.getText().toString());
                    int telat = lama - 3;
                    int pinjam = telat * hobi * 5000;

                    builder.append("\tLama peminjaman\t : \t" + lama +" hari \n");

                    builder.append("\n" + buku + "\n");

                    if (telat <= 0) {
                        builder.append("\nAnda mengembalikan buku tepat waktu!\n");
                    } else {
                        builder.append("\nKeterlambatan\t : \t " + telat + " hari\n");
                        builder.append("Denda\t\t\t\t : \t" + pinjam + " Ribu\n");
                    }

                    tvHasil.setText(builder);
                    tv2.setText("\n*Anda harus mengembalikan buku selambatnya 3 hari dari hari peminjaman, jika tidak anda akan mendapat denda 5000 Ribu per hari dalam 1 buku!.");

                }
            }

        }
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String tahun = etTahun.getText().toString();
        String lama = etLama.getText().toString();

        if(nama.isEmpty())
        {
            etNama.setError("Nama Belum diisi");
            valid = false;
        }
        else if(nama.length()<3)
        {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        }
        else
        {
            etNama.setError(null);
        }
        if(tahun.isEmpty())
        {
            etTahun.setError("Tahun belum diisi");
            valid = false;
        }
        else if (tahun.length()!=4)
        {
            etTahun.setError("format tahun bukan yyyy");
            valid = false;
        }
        else
        {
            etTahun.setError(null);
        }
        if(lama.isEmpty())
        {
            etLama.setError("Lama hari belum diisi");
            valid = false;
        }
        else
        {
            etLama.setError(null);
        }
        return valid;
    }


}
