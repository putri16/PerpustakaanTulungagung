package id.sch.smktelkom_mlg.tugas01.xiirpl2031.perpustakaantulungagung;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText etNama, etTahun, etLama;
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etTahun = (EditText) findViewById(R.id.editTextTahun);
        etLama = (EditText) findViewById(R.id.editTextLama);
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

        StringBuilder builder = new StringBuilder();
        builder.append("Nama Anda : ");
        builder.append(nama + "\n");
        builder.append("Anda lahir tahun " + tahun);
        builder.append(" usia anda sekarang : ");
        builder.append(usia + "\n");
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
