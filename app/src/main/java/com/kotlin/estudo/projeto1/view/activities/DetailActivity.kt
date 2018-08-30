package com.kotlin.estudo.projeto1

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kotlin.estudo.projeto1.databinding.ActivityDetailBinding
import com.kotlin.estudo.projeto1.view.activities.ImageActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), ViewModel.CharacterDetailViewModel.DetailViewModel {

    companion object { val MODEL_EXTRA = "model" }

    lateinit var detailViewModel: ViewModel.CharacterDetailViewModel
    lateinit var model : Model.Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        var intent = Intent(this, DetailActivity::class.java)
        val characterType = object : TypeToken<Model.Character>() {}.type
        model = Gson().fromJson<Model.Character>(intent.getStringExtra(MODEL_EXTRA), characterType)
        detailViewModel = ViewModel.CharacterDetailViewModel(this, intent)
        detailViewModel.loadCharacter(this)

        binding.viewmodel = detailViewModel
        detailViewModel.loadCollectionViews(binding.dynamicItems, binding.dynamicItemsSeries, supportFragmentManager)

        characterImage.setOnClickListener({ openImageActivity() })

    }

    fun openImageActivity(){
        var intent = Intent(this, ImageActivity::class.java)
        val json = Gson().toJson(model)
        intent.putExtra(MODEL_EXTRA, json)
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, characterImage ,"myImage" )
        startActivity(intent, optionsCompat.toBundle())
    }

    override fun endCallProgress(any: Any?) {
        if (any is Throwable) {
            println("Error: " + any.message)
        } else if (any is Model.CharacterResponse) {
            println("response "+ any.data.results[0].name)
        }
    }

    override fun onDestroy() {
        detailViewModel.unsubscribe()
        super.onDestroy()
    }

}
