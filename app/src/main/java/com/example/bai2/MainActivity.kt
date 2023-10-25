package com.example.bai2

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import java.util.Calendar


data class Response(val message: String, val isError: Boolean)
class MainActivity : AppCompatActivity() {
    lateinit var dateEdt: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dateEdt = findViewById(R.id.idEdtDate)

        // on below line we are adding
        // click listener for our edit text.
        dateEdt.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dateEdt.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }
    }
    fun buttonClicked(view: View) {
        val response: Response = validate()
//        if(response.isError) {
//            Log.d("bg", "color $backgroundColorTint")
//        }
//        Log.d("bg", "${response.message}")
        val title: String = if(!response.isError) "success" else "error"
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
              .setMessage(response.message)
               .setTitle(title)
            .setPositiveButton("Ok") {
                    dialog, which ->  dialog.dismiss()
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun validate(): Response {
        var response: Response;
        val firstName: TextView = findViewById(R.id.editTextText2)
        if(!firstName.getText().toString().isNotEmpty()) {
            response =  Response("first name is invalid", true)
            return response;
        }
        val lastName: TextView = findViewById(R.id.editTextText3)
        if(!lastName.getText().toString().isNotEmpty()) {
            response =  Response("last name is invalid", true)
            return response;
        }
        val gender = findViewById<RadioGroup>(R.id.radioButtonGroup)
        if (gender.getCheckedRadioButtonId() == -1) {
            response =  Response("gender is not chosed", true)
            return response
        }

        val birthDay: TextView = findViewById(R.id.idEdtDate)
        if(!birthDay.getText().toString().isNotEmpty()) {
            response =  Response("birth day is invalid", true)
            return response;
        }
        val address: TextView = findViewById(R.id.editTextText4)
        if(!address.getText().toString().isNotEmpty()) {
            response =  Response("address is invalid", true)
            return response;
        }
        val email: TextView = findViewById(R.id.editTextText5)
        if(!email.getText().toString().isNotEmpty()) {
            response =  Response("email is invalid", true)
            return response;
        }
        val agreeTerm: CheckBox = findViewById(R.id.checkBox2)
        if(!agreeTerm.isChecked) {
            response =  Response("you must agree our term", true)
            return response
        }
        response =  Response("success", false)
        return response;
    }
 }