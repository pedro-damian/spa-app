package com.example.vitaspa

object EndPoints {
    private val URL_ROOT = "http://192.168.133.1/webapi2/v1/?op="
    val ADD_SERVICIO = URL_ROOT + "add_servicio"
    val LIST_SERVICIOS = URL_ROOT + "list_servicios"
    val GET_SERVICIO = URL_ROOT + "get_servicio"
    val UPDATE_SERVICIO = URL_ROOT + "update_servicio"
    val DELETE_SERVICIO = URL_ROOT + "delete_servicio"
    val LOGIN = URL_ROOT + "login"
}