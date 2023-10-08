package com.example.rolepicker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var tvRole: TextView
    private lateinit var tvName: TextView
    private lateinit var hello: TextView

    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val returnString: String? = data?.getStringExtra("Role")
                tvRole.text = returnString
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hello = findViewById(R.id.hello)
        tvRole = findViewById(R.id.tvRole)
        tvName = findViewById(R.id.tvName)

        val bundle = intent.extras
        val name = bundle?.getString("username")
        if (name != null && !name.isNullOrBlank()) {
            hello.setText("Hello $name")
            tvName.setText(name)
        } else {
            hello.setText("Hello Guest")
            tvName.setText(name)
        }

        val pickRoleBtn: Button = findViewById(R.id.pickRoleBtn)
        pickRoleBtn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.pickRoleBtn -> {
                val bundle = Bundle()
                bundle.putString("username", hello.text.toString())
                val intent = Intent(this@MainActivity, PickRoleActivity::class.java)
                intent.putExtras(bundle)
                resultLauncher.launch(intent)
            }
        }
    }
}