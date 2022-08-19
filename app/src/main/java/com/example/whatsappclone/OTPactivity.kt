package com.example.whatsappclone

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import org.w3c.dom.Text

const val phone="phone number"
lateinit var phone_number:String
class OTPactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)
            initview()
        showTimer(60000)
    }

    private fun showTimer(milliSecInFuture: Long) {
        val resendbtn=findViewById<Button>(R.id.resend)
        resendbtn.isEnabled=false
        object:CountDownTimer(milliSecInFuture,1000){
            val countdown=findViewById<TextView>(R.id.countdown)
            override fun onTick(p0: Long) {
                countdown.isVisible=true
                countdown.text=getString(R.string.scnd,p0/1000)
            }

            override fun onFinish() {
                resendbtn.isEnabled=true
                countdown.isVisible=false
            }

        }.start()
    }

    private fun initview() {
        phone_number= intent.getStringExtra(phone).toString()
        setSpannableString()
        val phone=findViewById<TextView>(R.id.phone)

        phone.text=getString(R.string.verify, phone_number)
    }


    private fun setSpannableString() {
        val span = SpannableString(getString(R.string.waiting, phone_number))
        val clickableSpan=object : ClickableSpan(){
            override fun onClick(p0: View) {
                showLoginActivity()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText=true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ds.underlineColor=ds.linkColor
                }
            }
        }
        span.setSpan(clickableSpan,span.length-13,span.length,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        val btn=findViewById<TextView>(R.id.w8)
        btn.movementMethod=LinkMovementMethod.getInstance()
        btn.text=span

    }
    private fun showLoginActivity(){
        startActivity(Intent(this,Login_Activity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
    }
    override fun onBackPressed() {

    }
}