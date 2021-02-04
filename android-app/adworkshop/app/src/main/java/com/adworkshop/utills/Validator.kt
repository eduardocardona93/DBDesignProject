package com.adworkshop.utills

import android.util.Patterns
import androidx.core.text.trimmedLength
import java.util.regex.Pattern

object Validator {

    fun isEmailValid(email: String?): Boolean {
        email ?: return false
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(value: String?): Boolean {
        val PASSWORD_PATTERN : String = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@\$!%*#?&])(?=\\S+$).{4,}$";
        return value?.length in 8..30 && Pattern.compile(PASSWORD_PATTERN).matcher(value).matches()
    }

    fun isUserNameValid(value: String?): Boolean {
        val nameSize = value?.length ?: 0
        return nameSize != 0
    }

    fun isBlank(value: String?): Boolean {
        val nameSize = value!!.trimmedLength()
        return nameSize == 0
    }

}