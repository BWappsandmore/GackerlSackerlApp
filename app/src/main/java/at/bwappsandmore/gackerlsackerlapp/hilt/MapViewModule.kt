package at.bwappsandmore.gackerlsackerlapp.hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import org.osmdroid.views.MapView

@Module
@InstallIn(FragmentComponent::class)
class MapViewModule {

    @Provides
    fun provideMapView(@ApplicationContext appContext: Context): MapView = MapView(appContext)
}