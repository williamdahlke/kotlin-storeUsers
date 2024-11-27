package dahlke.br.storeusers

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var login : EditText
    private lateinit var btSave : Button
    private lateinit var usersResult : TextView
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.etLogin)
        btSave = findViewById(R.id.btnSave)
        usersResult = findViewById(R.id.tvUsers)
        dbHelper = DBHelper(this)

        btSave.setOnClickListener {
            val login = login.text.toString()
            dbHelper.addUser(login)
            getAllUsers(usersResult)
        }

        getAllUsers(usersResult)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getAllUsers(tvUsers : TextView){
        val users = dbHelper.getAllUsers()
        tvUsers.text = users.joinToString("\n")
    }

}