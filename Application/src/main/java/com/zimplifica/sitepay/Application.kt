package com.zimplifica.sitepay


import android.content.Context
import com.zimplifica.awsplatform.useCases.UseCaseProvider
import com.zimplifica.sitepay.utils.SingletonHolder

class Application(context: Context) {
    val awsProvider : UseCaseProvider
    init {
        awsProvider = UseCaseProvider(context)
    }
    companion object : SingletonHolder<Application, Context>(::Application)
}