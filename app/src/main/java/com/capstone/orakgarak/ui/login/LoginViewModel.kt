package com.capstone.orakgarak.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.capstone.orakgarak.data.LoginRepository
import com.capstone.orakgarak.data.Result

import com.capstone.orakgarak.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    // 하드코딩된 로그인 정보
    private val hardcodedUsername = "admin"
    private val hardcodedPassword = "admina"

   /*
   이게 원래 코드
    fun login(username: String, password: String) {

        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }
    */
    //여기부터는 테스트

    fun login(username: String, password: String) {
        // 입력한 아이디와 비밀번호가 하드코딩된 로그인 정보와 일치하는지 확인
        if (username == hardcodedUsername && password == hardcodedPassword) {
            // 로그인 성공 시
            _loginResult.value = LoginResult(
                success = LoggedInUserView(
                    displayName = "Admin" // 로그인한 사용자의 이름
                )
            )
        } else {
            // 로그인 실패 시
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
