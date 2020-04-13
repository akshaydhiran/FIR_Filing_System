package MenuActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.fir_filing_system.R;

import java.util.Calendar;

public class ReportCrimeActivity extends AppCompatActivity {

    private static final String TAG = "Date";
    private EditText Cname,FMname,Cadd,Cmno,Cemail,place,details;
    private TextView DisplayDate, DisplayTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_crime);

        Cname = (EditText) findViewById(R.id.et_name);
        FMname = (EditText) findViewById(R.id.et_fmname);
        Cadd = (EditText) findViewById(R.id.et_address);
        Cmno = (EditText) findViewById(R.id.et_mobile);
        Cemail = (EditText) findViewById(R.id.et_email);
        place = (EditText) findViewById(R.id.et_place);
        details = (EditText) findViewById(R.id.et_details);
        DisplayDate = (TextView) findViewById(R.id.tv_date);
        DisplayTime = (TextView) findViewById(R.id.tv_time);

        DisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ReportCrimeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                DisplayDate.setText(date);
            }
        };

        DisplayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });
    }

    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeSet: " + hour + minute);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();
                DisplayTime.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);
        timePickerDialog.show();
    }
}
