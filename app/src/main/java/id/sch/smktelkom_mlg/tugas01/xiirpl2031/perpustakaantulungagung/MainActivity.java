package id.sch.smktelkom_mlg.tugas01.xiirpl2031.perpustakaantulungagung;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText etNama, etTahun, etLama;
    RadioGroup rgJK;
    CheckBox cbP, cbE, cbH, cbPD, cbA;
    TextView tvHasil;

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
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        findViewById(R.id.buttonOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });
    }

    private void doClick() {
        String nama = etNama.getText().toString();
        int tahun = Integer.parseInt(etTahun.getText().toString());
        int usia = 2016 - tahun;
        String hasil = null;

        StringBuilder builder = new StringBuilder();
        builder.append("Nama Anda : ");
        builder.append(nama + "\n");
        builder.append("Anda lahir tahun " + tahun);
        builder.append(" usia anda sekarang : ");
        builder.append(usia + "tahun\n");

        if (rgJK.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton) findViewById(rgJK.getCheckedRadioButtonId());
            hasil = rb.getText().toString();
        }
        builder.append("Jenis kelamin anda : ");
        builder.append(hasil + "\n");

        String buku = "Buku yang anda pinjam : \n";
        int startlen = buku.length();
        if (cbP.isChecked()) buku += cbP.getText() + "\n";
        if (cbE.isChecked()) buku += cbE.getText() + "\n";
        if (cbH.isChecked()) buku += cbH.getText() + "\n";
        if (cbPD.isChecked()) buku += cbPD.getText() + "\n";
        if (cbA.isChecked()) buku += cbA.getText() + "\n";
        if (buku.length() == startlen) buku += "Tidak ada buku yang dipilih";
        builder.append(buku);

        int lama = Integer.parseInt(etLama.getText().toString());
        int telat = lama - 3;
        int pinjam = telat * 5000;
        if (pinjam <= 0) {
            builder.append("Anda mengembalikan buku tepat waktu!");
        } else {
            builder.append("Anda Terlambat mengembalikan buku selama " + telat + " hari\n");
            builder.append("Anda mendapat denda : " + pinjam + "\n");
        }

        tvHasil.setText(builder);
    }
}
