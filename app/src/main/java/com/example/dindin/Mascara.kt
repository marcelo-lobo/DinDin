package com.example.dindin

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class Mascara {

    object Mask {
        fun insert(mask: String, et: EditText): TextWatcher {
            return object : TextWatcher {
                var isUpdating = false
                var oldTxt = ""
                override fun onTextChanged(
                    s: CharSequence, start: Int, before: Int, count: Int
                ) {
                    val str = unmask(s.toString())
                    var maskCurrent = ""
                    if (isUpdating) {
                        oldTxt = str
                        isUpdating = false
                        return
                    }
                    var i = 0
                    for (m in mask.toCharArray()) {
                        if (m != '#' && str.length > oldTxt.length) {
                            maskCurrent += m
                            continue
                        }
                        maskCurrent += try {
                            str[i]
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }
                    isUpdating = true
                    et.setText(maskCurrent)
                    et.setSelection(maskCurrent.length)
                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {}
            }
        }

        private fun unmask(s: String): String {
            return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
                .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
                .replace("[)]".toRegex(), "")
        }
    }

}