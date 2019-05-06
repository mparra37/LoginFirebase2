package parra.mario.loginfirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        btn_registrar.setOnClickListener{
            registrar()
        }

        btn_ingresar.setOnClickListener{
            ingresar()
        }
    }

    private fun registrar(){
        var correo = et_correo.text.toString()
        var contra = et_contra.text.toString()

        mAuth?.createUserWithEmailAndPassword(correo, contra)
            ?.addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Se agregó el usuario",
                        Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "No se agregó el usuario",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        bienvenida()
    }
    private fun ingresar(){
        var correo = et_correo.text.toString()
        var contra = et_contra.text.toString()

        mAuth?.signInWithEmailAndPassword(correo, contra)
            ?.addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    bienvenida()
                }else{
                    Toast.makeText(this,"Datos incorrectos",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onRestart() {
        super.onRestart()

        var usuario = mAuth?.currentUser
        if(usuario != null){
            mAuth?.signOut()
        }
    }

    private fun bienvenida(){
        var usuario = mAuth?.currentUser

        if(usuario!= null){
            var intent = Intent(this, BienvenidaActivity::class.java)
            var correo = usuario.email
            intent.putExtra("correo", correo)
            startActivity(intent)
        }

    }
}
