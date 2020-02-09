package com.example.fileexample.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.fileexample.R
import com.example.fileexample.StorageTypes
import kotlinx.android.synthetic.main.dialog_select_storage.view.*

class DialogStorage : DialogFragment() {
    companion object{
        val SAVE_MODE=1
        val LOAD_MODE=2
    }
    interface Listener {
        fun dialogOkCLick(storage: StorageTypes, mode:Int?)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_select_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.dialog_ok_button.setOnClickListener {
            lateinit var storage: StorageTypes
            when (view.dialog_storage_radio_group.checkedRadioButtonId) {
                R.id.internal_radio_button -> storage = StorageTypes.INTERNAL
                R.id.external_radio_button -> storage = StorageTypes.EXTERNAL
                R.id.internal_cache_radio_button -> storage = StorageTypes.INTERNAL_CACHE
                R.id.external_cahe_radio_button -> storage = StorageTypes.EXTERNAL_CACHE
            }
            (targetFragment as Listener).dialogOkCLick(storage,arguments?.getInt("mode"))
            dismiss()
        }

        view.dialog_cancel_button.setOnClickListener {
            dialog?.dismiss()
        }
    }
}