package edu.newhaven.krikorherlopian.android.fragmentexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var isFragmentDisplayed = false

    // Saved instance state key.
    val STATE_FRAGMENT = "state_of_fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                open_button.setText(R.string.close)
            }
        }
        // Set the click listener for the button.
        open_button.setOnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()
            }
        }
    }

    /**
     * This method is called when the user clicks the button
     * to open the fragment.
     */
    private fun displayFragment(): Unit {
        // Instantiate the fragment.
        val simpleFragment = SimpleFragment.newInstance()
        // Get the FragmentManager and start a transaction.
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()

        // Add the SimpleFragment.
        fragmentTransaction.add(
            R.id.fragment_container,
            simpleFragment
        ).addToBackStack(null).commit()

        // Update the Button text.
        open_button.setText(R.string.close)
        // Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true
    }

    /**
     * This method is called when the user clicks the button to
     * close the fragment.
     */
    fun closeFragment() {
        // Get the FragmentManager.
        val fragmentManager = supportFragmentManager
        // Check to see if the fragment is already showing.
        val simpleFragment = fragmentManager
            .findFragmentById(R.id.fragment_container) as SimpleFragment
        if (simpleFragment != null) {
            // Create and commit the transaction to remove the fragment.
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }
        // Update the Button text.
        open_button.setText(R.string.open)
        // Set boolean flag to indicate fragment is closed.
        isFragmentDisplayed = false
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        // Save the state of the fragment (true=open, false=closed).
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)
        super.onSaveInstanceState(savedInstanceState)
    }
}
