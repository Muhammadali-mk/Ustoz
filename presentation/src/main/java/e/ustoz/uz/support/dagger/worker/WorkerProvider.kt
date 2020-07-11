package uz.anotomica.app.presentation.support.dagger.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface WorkerProvider {

    fun provideWorker(context: Context, workerParams: WorkerParameters): ListenableWorker
}