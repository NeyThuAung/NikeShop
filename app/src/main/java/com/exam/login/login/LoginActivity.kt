package com.exam.login.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.exam.login.R
import com.exam.login.RegisterActivity
import com.exam.login.card.ListActivity
import com.exam.login.databinding.ActivityLoginBinding
import com.exam.login.util.AppSharedPreference
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPref : AppSharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_login)

        sharedPref = AppSharedPreference(this)
        var isLogin = sharedPref.getValueBoolean("IsLogin",false)
        if (isLogin){
            var intent = Intent(this@LoginActivity,ListActivity::class.java)
            startActivity(intent)
            finish()
        }

        checkValidate()
        binding.btnLogin.setOnClickListener{
            val phoneNumber = binding.etPhNo.text.toString()
            val password = binding.etPassword.text.toString()
            if (phoneNumber.isNullOrEmpty()){
                binding.tilPhNo.error = "Enter Phone Number"
                binding.tilPhNo.isErrorEnabled = true
                return@setOnClickListener
            }

            if (password.isNullOrEmpty()){
                binding.tilPassword.error = "Enter Password"
                binding.tilPassword.isErrorEnabled = true
                return@setOnClickListener
            }
            if (phoneNumber.length<8){
                binding.tilPhNo.error = "Enter correct phone number"
                binding.tilPhNo.isErrorEnabled = true
                return@setOnClickListener
            }

            if (phoneNumber == "09963287263" && password == "Ouwae123"){
                sharedPref.save("IsLogin",true)
                Toast.makeText(this@LoginActivity,"Welcome",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity,ListActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Snackbar.make(binding.root,"Enter Correct Phone Number and Correct Password",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.tvCreate1.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.tvCreate2.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun checkValidate(){
        binding.etPhNo.doAfterTextChanged { s->
            if (!s.isNullOrEmpty()){
                if (s.length>8) {
                    binding.tilPhNo.isErrorEnabled = false
                }
            }
        }
        binding.etPassword.doAfterTextChanged { s->
            if (!s.isNullOrEmpty()){
                binding.tilPassword.isErrorEnabled = false
            }
        }
    }


}