package sg.edu.rp.c346.problemstatement12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Spinner spn1;
    Button btnload;
    Spinner spn2;
    ArrayList<String> alCat;
    ArrayAdapter<String> aaCat;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spn1 = findViewById(R.id.spinner1);
        spn2 = findViewById(R.id.spinner2);
        btnload = findViewById(R.id.button);

        alCat = new ArrayList<>();

        aaCat = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,alCat);


        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        alCat.clear();
                        String[] evenNumbers = getResources().getStringArray(R.array.republic);
                        //Convert Array to List and add to the ArrayList
                        alCat.addAll(Arrays.asList(evenNumbers));
                        spn2.setAdapter(aaCat);


                        break;
                    case 1:
                        alCat.clear();
                        String[] oddNumber = getResources().getStringArray(R.array.soi);
                        //Convert Array to List and add to the ArrayList
                        alCat.addAll(Arrays.asList(oddNumber));
                        spn2.setAdapter(aaCat);

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "";
                int pos1 = spn1.getSelectedItemPosition();
                int pos = spn2.getSelectedItemPosition();
                if(pos1 == 0) {


                    if (pos == 0) {
                        url = "https://www.rp.edu.sg/";
                    }
                    else {
                        url = "https://www.rp.edu.sg/student-life";
                    }
                }
                else{
                    if (pos == 0) {
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r47";
                    }
                    else {
                        url = "https://www.rp.edu.sg/soi/full-time-diplomas/details/r12";
                    }
                }
                Intent intent = new Intent(getBaseContext(), WebPageActivity.class);
                intent.putExtra("link", url);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onPause() {

        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putInt("spnn1",spn1.getSelectedItemPosition());

        prefEdit.putInt("spnn2",spn2.getSelectedItemPosition());


        prefEdit.commit();

    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int spnn1 = prefs.getInt("spnn1",0);
        int spnn2 = prefs.getInt("spnn2", 0);


        spn1.setSelection(spnn1);
        spn2.setSelection(spnn2);
    }
}
