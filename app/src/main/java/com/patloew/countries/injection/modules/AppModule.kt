package com.patloew.countries.injection.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.patloew.countries.injection.qualifier.AppContext
import com.patloew.countries.injection.scopes.PerApplication
import com.patloew.countries.ui.base.feedback.ApplicationToaster
import com.patloew.countries.ui.base.feedback.Toaster
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import dagger.Module
import dagger.Provides
import io.realm.Realm

/* Copyright 2016 Patrick Löwenstein
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ------
 *
 * FILE MODIFIED 2017 Tailored Media GmbH */
@Module
class AppModule(private val app: Application) {

    @Provides
    @PerApplication
    @AppContext
    internal fun provideAppContext(): Context = app

    @Provides
    @PerApplication
    internal fun provideResources(): Resources = app.resources

    @Provides
    @PerApplication
    internal fun provideRefWatcher(): RefWatcher = LeakCanary.install(app)

    @Provides
    internal fun provideRealm(): Realm = Realm.getDefaultInstance()

    @Provides
    @PerApplication
    internal fun provideToaster(): Toaster = ApplicationToaster(app)
}