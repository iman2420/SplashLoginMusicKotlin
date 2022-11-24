package ir.splash.login.music

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import ir.splash.login.music.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading




        username.doAfterTextChanged {
            login.isEnabled = binding.validateLogin()
        }

        password.apply {
            doAfterTextChanged {
                login.isEnabled = binding.validateLogin()
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE -> {
                        login.isEnabled = binding.validateLogin()
                    }

                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loading.postDelayed({
                    if ((username.text.toString() == getString(R.string.fake_email))
                            .and(password.text.toString() == getString(R.string.fake_password))
                    ) {
                        updateUiWithUser(username.text.toString())
                        DashboardActivity.start(this@LoginActivity)
                    }
                    else
                        showLoginFailed(R.string.login_failed)
                    loading.visibility = View.GONE
                } , 2000)

            }
        }
    }

    private fun updateUiWithUser(displayName: String) {
        val welcome = getString(R.string.welcome)
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun ActivityLoginBinding.validateLogin(): Boolean {
        username.error = null
        password.error = null
        return if (isValidString(username.text.toString()).not()) {
            username.error = getString(R.string.invalid_username)
            false
        } else if (password.text.length < 6) {
            password.error = getString(R.string.invalid_password)
            false
        } else
            true

    }
}

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    /* regex = */ "[a-zA-Z0-9+._%\\-]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun isValidString(str: String): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
}
