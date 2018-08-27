package com.harshalbenake.kotlinjetpack.ui.actvity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.harshalbenake.kotlinjetpack.R
import com.harshalbenake.kotlinjetpack.data.Entity.PersonProfile
import com.harshalbenake.kotlinjetpack.data.dao.PersonProfileDAO
import com.harshalbenake.kotlinjetpack.data.PersonProfileDb
import com.harshalbenake.kotlinjetpack.viewmodel.PersonProfileViewModel
import kotlinx.android.synthetic.main.activity_addperson.*

/**
 * This activity is detail activity to Add Person
 */
class AddPersonActivity : AppCompatActivity() {

    private var personProfileDAO: PersonProfileDAO? = null
    private var personProfileViewModel: PersonProfileViewModel? = null
    private var currentPersonProfile: Int? = null
    private var personProfile: PersonProfile? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addperson)
        var personProfileDB: PersonProfileDb = PersonProfileDb.getDataBase(this)
        personProfileDAO = personProfileDB.personProfileDAO()
        personProfileViewModel = ViewModelProviders.of(this).get(PersonProfileViewModel::class.java)
        currentPersonProfile = intent.getIntExtra("idPerson", -1)
        if (currentPersonProfile != -1) {
            setTitle("Edit")
            personProfile = personProfileDAO!!.getPersonById(currentPersonProfile!!)
            et_name.setText(personProfile!!.name)
            et_age.setText(personProfile!!.age)
        } else {
            setTitle("Add")
            invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.done_item -> {
                if (currentPersonProfile == -1) {
                    menuSavePersonProfile()
                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    menuUpdatePersonProfile()
                    Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
            R.id.delete_item -> {
                menuDeletePersonProfile()
                Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        if (currentPersonProfile == -1) {
            menu.findItem(R.id.delete_item).isVisible = false
        }
        return true
    }

    private fun menuSavePersonProfile() {
        var nameContact = et_name.text.toString()
        var numberContact = et_age.text.toString()
        var contact = PersonProfile(0, nameContact, numberContact)
        personProfileViewModel!!.addersonProfile(contact)
    }

    private fun menuDeletePersonProfile() {
        personProfileDAO!!.deletePersonProfiles(personProfile!!)
    }

    private fun menuUpdatePersonProfile() {
        var nameContact = et_name.text.toString()
        var numberContact = et_age.text.toString()
        var contact = PersonProfile(personProfile!!.idPerson, nameContact, numberContact)
        personProfileDAO!!.updatePersonProfiles(contact)
    }
}