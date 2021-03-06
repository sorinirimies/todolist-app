package ro.sorin.blanknote

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.phraseapp.android.sdk.PhraseApp
import kotlinx.android.synthetic.main.activity_main.*
import ro.sorin.blanknote.ui.notes.NotesFragment
import ro.sorin.blanknote.ui.settings.SettingsFragment
import ro.sorin.blanknote.ui.user.UserFragment
import com.phraseapp.android.sdk.TranslationsSyncCallback
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(R.id.frag_container,
                        NotesFragment.newInstance(),
                        NotesFragment.TAG_NOTES)
            }
        }
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_notes -> supportFragmentManager.transaction {
                    replace(R.id.frag_container,
                            NotesFragment.newInstance(),
                            NotesFragment.TAG_NOTES)
                }
//                R.id.action_user -> supportFragmentManager.transaction {
//                    replace(R.id.frag_container,
//                            UserFragment.newInstance(),
//                            UserFragment.TAG_USER)
//                }
                R.id.action_settings -> supportFragmentManager.transaction {
                    replace(R.id.frag_container, SettingsFragment(), SettingsFragment.TAG_SETTINGS)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        PhraseApp.updateTranslations(object : TranslationsSyncCallback {
            override fun onSuccess(translationsChanged: Boolean) {
                Timber.w("Translations updated!")
                Timber.w("Did translations change? $translationsChanged")

            }

            override fun onFailure() {
                Timber.e("Translations failed to update!")
            }
        })
    }
   override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(PhraseApp.wrap(newBase))
    }
}
