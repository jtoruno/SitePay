package com.zimplifica.domain.entities

import java.lang.Exception

class GenericResponse<R, E : Exception>(val result : R?, val status : Boolean, val error : E?){

    override fun equals(other: Any?): Boolean {
        (other as? GenericResponse<R,E>)?.let {

            return it.status == this.status

        } ?: run{
            return false
        }
    }
}