package com.example.rolepicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.rolepicker.databinding.ActivityPickRoleBinding

class PickRoleActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPickRoleBinding
    private lateinit var hello: TextView
    private lateinit var hipsterContainer: LinearLayout
    private lateinit var hustlerContainer: LinearLayout
    private lateinit var hackerContainer: LinearLayout
    private var role: String = "Hipster"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPickRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hello = findViewById(R.id.hello)

        val bundle = intent.extras
        val greet = bundle?.getString("username")
        if (greet != null && !greet.isNullOrBlank()) {
            hello.setText(greet)
        }

        binding.hipsterContainer.setOnClickListener { replaceFragment(HipsterFragment()) }
        binding.hustlerContainer.setOnClickListener { replaceFragment(HustlerFragment()) }
        binding.hackerContainer.setOnClickListener { replaceFragment(HackerFragment()) }


        hipsterContainer = findViewById(R.id.hipsterContainer)
        hustlerContainer = findViewById(R.id.hustlerContainer)
        hackerContainer = findViewById(R.id.hackerContainer)

        val continueBtn: Button = findViewById(R.id.continueBtn)
        continueBtn.setOnClickListener(this)

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.hipsterContainer -> this.role = "Hipster"
            R.id.hustlerContainer -> this.role = "Hustler"
            R.id.hackerContainer -> this.role = "Hacker"
        }

        if (v.id == R.id.continueBtn) {
            val intent = Intent()
            intent.putExtra("Role", this.role)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}