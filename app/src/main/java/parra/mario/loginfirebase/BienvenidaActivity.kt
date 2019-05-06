package parra.mario.loginfirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bienvenida.*

class BienvenidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        var bundle = intent.extras

        if (bundle != null){
            var correo = bundle.getString("correo")
            tv_usuario.setText(correo)
        }

        btn_cerrarsesion.setOnClickListener{
            finish()
        }
    }
}
