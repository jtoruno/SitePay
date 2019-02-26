package com.zimplifica.domain.entities

import java.lang.Exception

class GenericResponse<R, E : Exception>(val result : R?, val status : Boolean, val error : E?){

}