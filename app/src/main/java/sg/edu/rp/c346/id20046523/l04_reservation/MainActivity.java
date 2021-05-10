package sg.edu.rp.c346.id20046523.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etPhone;
    EditText etSize;
    RadioGroup rgTable;
    DatePicker dp;
    TimePicker tp;
    Button btnReserve;
    Button btnReset;
    TextView tvConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.textName);
        etPhone=findViewById(R.id.textPhone);
        etSize=findViewById(R.id.textSize);
        rgTable=findViewById(R.id.radioGroupTable);
        btnReserve=findViewById(R.id.buttonReserve);
        btnReset=findViewById(R.id.buttonReset);
        dp=findViewById(R.id.datePicker);
        tp=findViewById(R.id.timePicker);
        tvConfirm=findViewById(R.id.textViewConfirmation);

        dp.updateDate(2020,5,1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        btnReserve.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){

                String output=" ";
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String numOfPeople=etSize.getText().toString();
                int table=rgTable.getCheckedRadioButtonId();

                if(etName != null && etPhone != null && etSize!=null && rgTable.getCheckedRadioButtonId() != -1)
                {
                    String date=String.valueOf(dp.getDayOfMonth()) + "/" + String.valueOf(dp.getMonth() +1 ) + "/" + String.valueOf(dp.getYear());
                    String timing=String.valueOf(tp.getCurrentHour()) + ":"+ String.valueOf(tp.getCurrentMinute());

                    if(table == R.id.radioButtonNonSmoking)
                    {
                        output=name + "\n" + phone + "\n" + "Total number of people: " + numOfPeople + "\n"+ "Table in Non-Smoking area " + "\n" + "Reservation on " + date + " at " +timing;
                    }
                    else
                    {
                        output=name + "\n" + phone + "\n" + "Total number of people: " + numOfPeople + "\n"+ "Table in Smoking area " + "\n" + "Reservation on " + date + " at " + timing;
                    }

                    tvConfirm.setText(output);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please fill in all blanks", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                etName.setText("");
                etPhone.setText("");
                etSize.setText("");
                rgTable.clearCheck();
                dp.updateDate(2020,5,1);
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay>8)
                {
                    Toast.makeText(MainActivity.this, "We are only open at 8am.",Toast.LENGTH_SHORT);
                }
                else if(hourOfDay<21 && minute<=59)
                {
                    Toast.makeText(MainActivity.this, "We are closed at 9pm.",Toast.LENGTH_SHORT);
                }

            }
        });

    }
}