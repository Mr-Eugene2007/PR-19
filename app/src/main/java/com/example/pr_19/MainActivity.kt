package com.example.pr_19

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var timePick: TextView? = null
    var btnTime: Button? = null
    var btnDate: Button? = null
    var dateAndTime: Calendar = Calendar.getInstance()

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePick = findViewById(R.id.timePick)
        btnDate = findViewById(R.id.btnDate)
        btnTime = findViewById(R.id.btnTime)

        btnDate!!.setOnClickListener(this)
        btnTime!!.setOnClickListener(this)

        setInitialDateTime()
    }

    // Метод для установки начальных даты и времени в текстовое поле
    private fun setInitialDateTime() {
        timePick!!.setText(
            DateUtils.formatDateTime(
                this,
                dateAndTime.getTimeInMillis(),
                (DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_SHOW_YEAR
                        or DateUtils.FORMAT_SHOW_TIME)
            )
        )
    }

    override fun onClick(view: View) {
        if (view.getId() == R.id.btnDate) {
            DatePickerDialog(
                this@MainActivity, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
            )
                .show()
        } else if (view.getId() == R.id.btnTime) {
            TimePickerDialog(
                this@MainActivity, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true
            )
                .show()
        }
    }

    // Обработчик выбора времени, обновляет объект Calendar и экран
    var t: OnTimeSetListener = object : OnTimeSetListener {
        override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            dateAndTime.set(Calendar.MINUTE, minute)
            setInitialDateTime()
        }
    }

    // Обработчик выбора даты, обновляет объект Calendar и экран
    var d: OnDateSetListener = object : OnDateSetListener {
        override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
            dateAndTime.set(Calendar.YEAR, year)
            dateAndTime.set(Calendar.MONTH, monthOfYear)
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            setInitialDateTime()
        }
    }
}
