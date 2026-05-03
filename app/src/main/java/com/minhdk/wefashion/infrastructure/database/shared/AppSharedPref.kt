package com.minhdk.wefashion.infrastructure.database.shared

import com.minhdk.wefashion.infrastructure.database.shared.base.SharedPref

interface AppSharedPref: SharedPref {

    var firstOpenApp: Boolean

}