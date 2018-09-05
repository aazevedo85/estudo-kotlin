package com.kotlin.estudo.projeto1.view.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import com.kotlin.estudo.projeto1.MainActivity
import com.kotlin.estudo.projeto1.R
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email_sign_in_button.setOnClickListener { }

        mHandler.postDelayed(mUpdateTimeTask, 1000)

        email_sign_in_button.setOnClickListener {
            openMainActivity()
        }

    }

    fun openMainActivity() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private val mUpdateTimeTask = Runnable {
        // do what you need to do here after the delay
        addAnimationOperations()
    }

    private fun addAnimationOperations() {
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone(login_form)
        val constraint2 = ConstraintSet()
        constraint2.clone(this, R.layout.activity_login_alt)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                TransitionManager.beginDelayedTransition(root, mStaggeredTransition)
            TransitionManager.beginDelayedTransition(login_form)
            val constraint = if (set) constraint1 else constraint2
            constraint.applyTo(login_form)
            set = !set
        }

    }

}
