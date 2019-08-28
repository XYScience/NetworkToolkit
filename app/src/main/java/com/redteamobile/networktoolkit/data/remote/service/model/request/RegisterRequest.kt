package com.redteamobile.networktoolkit.data.remote.service.model.request

import com.redteamobile.networktoolkit.data.remote.service.util.md5


class RegisterRequest(var email: String, password: String) : BasicRequest() {

    var password = password.md5()

}
