package com.harshalbenake.kotlinjetpack.ui.actvity


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.harshalbenake.kotlinjetpack.R
import com.harshalbenake.kotlinjetpack.adapter.PersonProfileAdapter
import com.harshalbenake.kotlinjetpack.data.PersonProfileDb
import com.harshalbenake.kotlinjetpack.data.Entity.PersonProfile
import com.harshalbenake.kotlinjetpack.utils.Helper
import com.harshalbenake.kotlinjetpack.viewmodel.PersonProfileViewModel

/**
 * This activity is main activity Person Profiles
 */
class MainActivity : AppCompatActivity(), PersonProfileAdapter.OnItemClickListener {

    private var personProfileRecyclerView: RecyclerView? = null
    private var personProfileAdapter: PersonProfileAdapter? = null
    private var personProfileViewModel: PersonProfileViewModel? = null
    private var personProfileDB: PersonProfileDb? = null
    private var fab:FloatingActionButton?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initLayout()

        personProfileRecyclerView!!.layoutManager = LinearLayoutManager(this)
        personProfileRecyclerView!!.adapter = personProfileAdapter

        personProfileViewModel!!.getListPersonProfiles().observe(this, Observer { personprofile ->
            personProfileAdapter!!.addContacts(personprofile!!)
        })
        fab!!.setOnClickListener {
            var intent = Intent(applicationContext, AddPersonActivity::class.java)
            startActivity(intent)
        }

        var strReturn= Helper(this).notificationWork()
        Toast.makeText(this,strReturn,Toast.LENGTH_SHORT).show()
    }

    /**
     * init Layout
     */
    fun initLayout(){
        personProfileRecyclerView = findViewById(R.id.recycler_view)
        fab = findViewById(R.id.fab)
        personProfileDB = PersonProfileDb.getDataBase(this)
        personProfileAdapter = PersonProfileAdapter(arrayListOf(), this)
        personProfileViewModel = ViewModelProviders.of(this).get(PersonProfileViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_items -> {
                menuDeleteAllPersonProfiles()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * menu deletes All Person Profiles
     */
    private fun menuDeleteAllPersonProfiles() {
        personProfileDB!!.personProfileDAO().deleteAllPersonProfiles()
    }

    override fun onItemClick(personProfile: PersonProfile) {
        var intent = Intent(applicationContext, AddPersonActivity::class.java)
        intent.putExtra("idPerson", personProfile.idPerson)
        startActivity(intent)
    }
}
