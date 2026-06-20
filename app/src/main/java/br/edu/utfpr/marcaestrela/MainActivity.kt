package br.edu.utfpr.marcaestrela

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var imagem: ImageView
    private lateinit var btOnOff: Button
    private lateinit var btConfiguracoes: Button

    private var isChecked = false

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imagem = findViewById(R.id.imagem)
        btOnOff = findViewById(R.id.btOnOff)
        btConfiguracoes = findViewById(R.id.btConfiguracoes)


        sharedPreference = getSharedPreferences( "PREFERENCE_FILE", MODE_PRIVATE)
        isChecked = sharedPreference.getBoolean("isChecked", false)

        when ( isChecked ) {
            false -> {
                imagem.setImageResource(android.R.drawable.btn_star_big_off)
            }
            true -> {
                imagem.setImageResource(android.R.drawable.btn_star_big_on)
            }
        }


        btOnOff.setOnClickListener {
            btOnOffOnClick()
        }

        btConfiguracoes.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity( intent )
        }
    }

    private fun btOnOffOnClick() {

        when ( isChecked ) {
            false -> {
                imagem.setImageResource(android.R.drawable.btn_star_big_on)
                isChecked = true
            }
            true -> {
                imagem.setImageResource(android.R.drawable.btn_star_big_off)
                isChecked = false
            }
        }

        var editor = sharedPreference.edit()
        editor.putBoolean("isChecked", isChecked )
        editor.apply()
    }

} //fim da MainActivity

