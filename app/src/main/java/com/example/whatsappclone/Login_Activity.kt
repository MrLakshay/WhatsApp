package com.example.whatsappclone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.hbb20.CountryCodePicker


class Login_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var ccp: CountryCodePicker
        ccp =  findViewById(R.id.ccp)
        val editTextPhone=findViewById<EditText>(R.id.editTextPhone)
        val nxtbtn=findViewById<Button>(R.id.nxtbtn)
        editTextPhone.addTextChangedListener{
            nxtbtn.isEnabled=!(it.isNullOrBlank() || it.length <10)
        }
        nxtbtn.setOnClickListener{
            val phonenumber=ccp.selectedCountryCodeWithPlus+editTextPhone.text.toString()
            val intent = Intent(this,OTPactivity::class.java)
            intent.putExtra("phone number",phonenumber)
            startActivity(intent)
            val phone=findViewById<TextView>(R.id.phone)
            phone.text="Verify $phonenumber"
        }
    }
}