package com.example.countryapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.countryapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var provinces: Array<String>

    private val countries = arrayOf(
        "Indonesia", "United States", "United Kingdom",
        "Germany", "France", "Australia",
        "Japan", "China", "Brazil", "Canada"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provinces = resources.getStringArray(R.array.provinces)

        with(binding) {
            // Spinner Country
            val adapterCountry = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item, countries
            )
            adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCountry.adapter = adapterCountry

            // Spinner Provinces
            val adapterProvinces = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item, provinces
            )
            adapterProvinces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerProvinces.adapter = adapterProvinces

            spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>, view: View?, position: Int, id: Long
                ) {
                    Toast.makeText(this@MainActivity, countries[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {}
            }

            // Button show DatePicker dialog
            btnShowCalendar.setOnClickListener {
                val datePicker = DatePickerFragment()
                datePicker.show(supportFragmentManager, "datePicker")
            }

            // Button show TimePicker dialog
            btnShowTimePicker.setOnClickListener {
                val timePicker = TimePickerFragment()
                timePicker.show(supportFragmentManager, "timePicker")
            }

            // AlertDialog
            btnShowAlertDialog.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Keluar")
                builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                builder.setPositiveButton("Ya") { _, _ -> finish() }
                builder.setNegativeButton("Tidak") { dialog, _ -> dialog.dismiss() }
                builder.create().show()
            }

            // CustomDialog
            btnShowCustomDialog.setOnClickListener {
                val dialog = DialogExit()
                dialog.show(supportFragmentManager, "dialogExit")
            }
        }
    }

    // Wajib implementasikan ini
    override fun onDateSet(view: android.widget.DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$dayOfMonth/${month + 1}/$year"
        Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show()
    }

    // Wajib implementasikan ini
    override fun onTimeSet(view: android.widget.TimePicker?, hourOfDay: Int, minute: Int) {
        val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        Toast.makeText(this, selectedTime, Toast.LENGTH_SHORT).show()
    }
}
