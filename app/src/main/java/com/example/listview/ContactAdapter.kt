package com.example.listview

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ContactAdapter(
    private val context: Context,
    private val datos: ArrayList<ContactoModel>
) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private val telefonoCount: Map<String, Int> = datos.groupingBy { it.telefono }.eachCount()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.list_item_contact, parent, false)

        val imgContacto = rowView.findViewById<ImageView>(R.id.imgContacto)
        val txtNombre = rowView.findViewById<TextView>(R.id.txtNombre)
        val txtTelefono = rowView.findViewById<TextView>(R.id.txtTelefono)
        val txtEmail = rowView.findViewById<TextView>(R.id.txtEmail)
        val btnEnviarCorreo = rowView.findViewById<Button>(R.id.btnEmail)

        val contacto = getItem(position) as ContactoModel

        txtNombre.text = contacto.nombre
        txtTelefono.text = contacto.telefono
        txtEmail.text = contacto.email
        imgContacto.setImageResource(contacto.imagenId)

        if (telefonoCount[contacto.telefono] ?: 0 > 1) {
            rowView.setBackgroundColor(Color.RED)
        } else {
            rowView.setBackgroundColor(Color.TRANSPARENT)
        }

        btnEnviarCorreo.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${contacto.email}")
                putExtra(Intent.EXTRA_SUBJECT, "Asunto")
                putExtra(Intent.EXTRA_TEXT, "Cuerpo del correo")
            }
            context.startActivity(Intent.createChooser(emailIntent, "Enviar correo desde:"))
        }

        return rowView
    }

    override fun getItem(position: Int): Any {
        return datos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datos.size
    }
}
