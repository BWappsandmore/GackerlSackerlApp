package at.bwappsandmore.gackerlsackerlapp.hilt

import android.content.Context
import androidx.preference.PreferenceManager
import at.bwappsandmore.gackerlsackerlapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.osmdroid.config.Configuration
import org.osmdroid.config.IConfigurationProvider

@Module
@InstallIn(FragmentComponent::class)
class ConfigurationModule {

    @Provides
    fun provideConfiguration(@ApplicationContext appContext: Context): IConfigurationProvider =
        Configuration.getInstance().apply {
            load(appContext, appContext.let { PreferenceManager.getDefaultSharedPreferences(it) })
            userAgentValue = BuildConfig.APPLICATION_ID
        }
}