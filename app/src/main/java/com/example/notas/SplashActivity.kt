package com.example.notas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    lateinit var iv_spplash_logo : ImageView;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        // creamos la vista y la inflamos
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashAnimation=android.view.animation.AnimationUtils.loadAnimation(this,R.anim.asss_splash)
        // asignamos a la variable tipo animacion  la imagen
        iv_spplash_logo=findViewById(R.id.iv_splash_logo)
        iv_spplash_logo.startAnimation(splashAnimation)

        @Suppress("DEPRECATION")
        // metodo para poner modo pantalla completa y quitar el tools
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.hide(WindowInsets.Type.statusBars())
            }else
            {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        // metodo para decirle a la animacion que sigue despues de terminar su tiempo de funcion
        @Suppress("DEPRECATION")
        Handler().postDelayed({
            startActivity(
                Intent(this@SplashActivity,MainActivity::class.java)
            )
            finish()
        },3000
        )
      }
    }








