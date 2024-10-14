package com.example.listview

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)

        val contactList = getContacts()

        val contactAdapter = ContactAdapter(this, contactList)

        listView.adapter = contactAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val contactName = contactList[position].nombre
            val contactPhone = contactList[position].telefono

            Toast.makeText(
                applicationContext,
                "Contacto: $contactName\nTeléfono: $contactPhone",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getContacts(): ArrayList<ContactoModel> {
        return arrayListOf(
            ContactoModel("Gabriela Escalante", "+52 5590 3260 30", "gescalante@gmail.com", R.drawable.contacto_1),
            ContactoModel("Juan Díaz", "+52 9036 6999 31", "jdiaz@gmail.com", R.drawable.contacto_2),
            ContactoModel("María López", "+52 5555 1234 56", "mlopez@gmail.com", R.drawable.contacto_3),
            ContactoModel("Rodrigo Hernández", "+52 9988 1122 44", "rhernandez@gmail.com", R.drawable.contacto_4),
            ContactoModel("Andrea Gutiérrez", "+52 4433 2211 99", "agutierrez@gmail.com", R.drawable.contacto_5),
            ContactoModel("Carlos Sánchez", "+52 6644 7788 55", "csanchez@gmail.com", R.drawable.contacto_6)
        )
    }
}
