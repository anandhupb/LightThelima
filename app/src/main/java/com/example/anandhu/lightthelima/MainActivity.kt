package com.example.anandhu.lightthelima

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    lateinit var editText : EditText
    lateinit var editText1 : EditText
    lateinit var calendar: Calendar
    lateinit var simpleDateFormat: SimpleDateFormat
    lateinit var date: String
   // lateinit var dateTimeView: TextView

    lateinit var btn : Button
    lateinit var btn1 : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  dateTimeView = findViewById(R.id.dateTimeView)

      //  Date = simpleDateFormat.format(calendar.getTime())




        editText    = findViewById(R.id.firstname)
        editText1   = findViewById(R.id.lastname)

        btn   = findViewById(R.id.savebtn)
        btn1   = findViewById(R.id.databaseBtn)
      //  btn3   = findViewById(R.id.timebtn)

     /*   btn3.setOnClickListener {
          //  Toast.makeText(this,Date,Toast.LENGTH_LONG).show()

        }*/

        btn.setOnClickListener {

            saveData()


        }

        btn1.setOnClickListener {
            val intent = Intent(this@MainActivity,EmployeesData::class.java)
            startActivity(intent)
        }

    }
    private fun saveData(){
        val firstname    = editText.text.toString().trim()
        val lastname     = editText1.text.toString().trim()
       val calendar = Calendar.getInstance();
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
       val date = simpleDateFormat.format(calendar.getTime())
      //  val datevar = Date
        if (firstname.isEmpty()){
            editText.error = "Please enter your firstname"
            return
        }
        if (lastname.isEmpty()){
            editText.error = "Please enter your lastname"
            return
        }

        val myDataBase = FirebaseDatabase.getInstance().getReference("Employees")
        val employeeId = myDataBase.push().key
        val employee = Employees(employeeId,firstname,lastname,date)
        myDataBase.child(employeeId).setValue(employee).addOnCompleteListener{
            Toast.makeText(this,"Saved :) ", Toast.LENGTH_LONG).show()

        }


    }
}

