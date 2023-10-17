package com.robert.travelexpenses

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import com.robert.data.di.analyticsModule
import com.robert.data.di.daosModule
import com.robert.data.di.dataSourceModule
import com.robert.data.di.preferencesModule
import com.robert.data.di.repositoryModule
import com.robert.travelexpenses.ui.addplace.AddTripNameViewModel
import com.robert.travelexpenses.ui.dashboard.DashboardViewModel
import com.robert.travelexpenses.ui.tripdate.AddTripDatesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class TravelExpensesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                analyticsModule,
                viewModelModule,
                daosModule,
                repositoryModule,
                preferencesModule,
                dataSourceModule
            )
        }
        initializeFlipper()
    }

    private val viewModelModule = module {
        viewModel { DashboardViewModel(get(), get(), get()) }
        viewModel { AddTripNameViewModel(get()) }
        viewModel { AddTripDatesViewModel(get()) }
    }

    private fun initializeFlipper() {
        if (BuildConfig.DEBUG) {
            SoLoader.init(this, false)
        }

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)
            client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
            client.start()
        }
    }
}