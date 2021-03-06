package com.kotlin.estudo.projeto1.view.activities

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.kotlin.estudo.projeto1.R
import android.support.constraint.ConstraintSet
import android.support.transition.ChangeBounds
import android.support.transition.TransitionSet
import com.kotlin.estudo.projeto1.ViewModel
import com.kotlin.estudo.projeto1.databinding.ActivityImageBinding
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    lateinit var detailViewModel: ViewModel.CharacterDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityImageBinding = DataBindingUtil.setContentView(this, R.layout.activity_image)

        detailViewModel = ViewModel.CharacterDetailViewModel(this, intent)

        binding.viewmodel = detailViewModel

        addAnimationOperations()
    }

    override fun onDestroy() {
        detailViewModel.unsubscribe()
        super.onDestroy()
    }

    private fun addAnimationOperations() {
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone(root)
        val constraint2 = ConstraintSet()
        constraint2.clone(this, R.layout.activity_image_alt)

        val mStaggeredTransition = TransitionSet()
        val first = ChangeBounds()
        val second = ChangeBounds()
        val third = ChangeBounds()
//        val fourth = ChangeBounds()

        first.addTarget(characterImage)
        second.setStartDelay(500).addTarget(textView)
        third.setStartDelay(1000).addTarget(textView2)
//        fourth.setStartDelay(150).addTarget(mFourthButton)

        mStaggeredTransition.addTransition(first).addTransition(second).addTransition(third)

        characterImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                TransitionManager.beginDelayedTransition(root, mStaggeredTransition)
                TransitionManager.beginDelayedTransition(root)
                val constraint = if (set) constraint1 else constraint2
                constraint.applyTo(root)
                set = !set
            }
        }
    }
}