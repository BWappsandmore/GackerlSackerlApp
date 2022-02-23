package at.bwappsandmore.gackerlsackerlapp.hilt

import at.bwappsandmore.gackerlsackerlapp.MapEventsReceiverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import org.osmdroid.events.MapEventsReceiver

@Module
@InstallIn(FragmentComponent::class)

abstract class MapReceiverModule {
    @Binds
    abstract fun provideMapReceiver(mapEventsReceiverImpl: MapEventsReceiverImpl): MapEventsReceiver
}