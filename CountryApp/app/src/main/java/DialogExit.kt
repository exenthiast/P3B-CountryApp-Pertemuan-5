package com.example.countryapp

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.countryapp.databinding.DialogExitBinding

class DialogExit : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val binding = DialogExitBinding.inflate(inflater)

        binding.btnYes.setOnClickListener {
            requireActivity().finish()
        }
        binding.btnNo.setOnClickListener {
            dismiss()
        }

        builder.setView(binding.root)
        return builder.create()
    }
}
