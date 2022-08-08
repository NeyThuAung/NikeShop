package com.exam.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.exam.login.card.ListActivity
import com.exam.login.databinding.ActivityRegisterBinding
import com.exam.login.login.LoginActivity
import com.exam.login.util.AppSharedPreference
import com.exam.login.util.PatternUtils
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private  val sharedPref : AppSharedPreference by lazy {
        AppSharedPreference(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_register)
        setContentView(binding.root)

        checkValidate()
        onClick()
    }

    private fun checkValidate(){
        binding.etPhNo.doAfterTextChanged { s->
            if (!s.isNullOrEmpty()){
                if(PhoneNumberUtils.isGlobalPhoneNumber(s.toString())){
                    binding.regPhone.isErrorEnabled = false
                } else{
                    binding.regPhone.error = "Enter Correct Phone Number"
                    return@doAfterTextChanged
                }
            }
        }

        binding.etCreatePass.doAfterTextChanged { s->
            if (!s.isNullOrEmpty()){
                val password = PatternUtils.PASSWORD_PATTERN.matcher(s)
                if (!password.matches()){
                    val error = checkError(s.toString())
                    binding.tilCreatePass.error =error
                    return@doAfterTextChanged
                } else {
                    binding.tilCreatePass.isErrorEnabled = false
                }
            }
        }

        binding.etComfirmPass.doAfterTextChanged { s->
            if (binding.etCreatePass.text.toString().isNullOrEmpty()){
                binding.tilCreatePass.error = "Enter Password First"
                return@doAfterTextChanged
            }else{
                if (binding.etCreatePass.text.toString() == s.toString()){
                    binding.tilComfirmPass.isErrorEnabled = false

                }else {
                    binding.tilComfirmPass.error = "Password is not Match"
                    return@doAfterTextChanged
                }
            }
        }
    }

    private fun onClick(){
        binding.btnRegister.setOnClickListener{
            if(binding.regPhone.isErrorEnabled || binding.tilCreatePass.isErrorEnabled || binding.tilComfirmPass.isErrorEnabled ||
            binding.etFirstName.text.isNullOrBlank() || binding.etLastName.text.isNullOrBlank() || binding.etEmail.text.isNullOrBlank() || !binding.tvTerm.isChecked
                || binding.etPhNo.text.isNullOrBlank() || binding.etCreatePass.text.isNullOrBlank() || binding.etComfirmPass.text.isNullOrBlank() ){

                Snackbar.make(it,"Fix Correct Data",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                sharedPref.save("IsLogin",true)
                val intent = Intent(this@RegisterActivity,ListActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun checkError(password: String):String {
        return when {
            /* Rule 1 */
            !password.contains(Regex("[A-Z]")) -> "Password must contain one capital letter"
            /* Rule 2 */
            !password.contains(Regex("[0-9]")) -> "Password must contain one digit"
            /* Rule 3, not counting space as special character */
            !password.contains(Regex("[^a-zA-Z0-9 ]")) -> "Password must contain one special character"

            !password.contains(Regex("\\S+\$"))-> "Password not allowed to add space"
            else -> "Password is too short."
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}