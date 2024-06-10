package es.deiividdev.com.to_do_list_espresso_test

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import es.deiividdev.com.to_do_list_espresso_test.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dialogButton.setOnClickListener {
            showDialog()
        }
    }

    //hardcoded text just for testing proposes you can se it's checking the text
    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("Test Dialog")
            .setMessage("This is a test dialog")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .setCancelable(true)
            .show()

    }
}

