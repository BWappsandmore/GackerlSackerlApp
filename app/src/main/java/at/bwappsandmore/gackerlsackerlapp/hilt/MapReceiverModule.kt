package at.bwappsandmore.gackerlsackerlapp.hilt

import at.bwappsandmore.gackerlsackerlapp.MapEventsReceiverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)

class MapReceiverModule {
    @Provides
    fun provideMapReceiver() = MapEventsReceiverImpl()
}