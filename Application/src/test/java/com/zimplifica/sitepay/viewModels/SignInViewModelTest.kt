package com.zimplifica.sitepay.viewModels

import com.zimplifica.domain.entities.GenericResponse
import com.zimplifica.domain.entities.SignInError
import com.zimplifica.domain.entities.SignInResult
import com.zimplifica.sitepay.Scenes.SignIn.SignInViewModel
import com.zimplifica.sitepay.mocks.AuthenticationUseCase
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SignInViewModelTest {

    lateinit var  vm : SignInViewModel.ViewModel
    val signInButtonIsEnabled = TestObserver<Boolean>()
    val signInStatus = TestObserver<GenericResponse<SignInResult, SignInError>>()
    val signInErrorMessage = TestObserver<String>()



    private  fun setup(){
        val useCase = AuthenticationUseCase()
        vm = SignInViewModel.ViewModel(useCase)
        vm.outputs.signInButtonIsEnabled().subscribe(this.signInButtonIsEnabled)
        vm.outputs.signInStatus().subscribe(this.signInStatus)
        vm.outputs.errorMessage().subscribe(this.signInErrorMessage)
    }


    @Test
    fun testSignInButtonEnabled(){

        this.setup()

        this.vm.inputs.username("jtoru@gmail.com")
        this.vm.inputs.password("jose")
        this.signInButtonIsEnabled.assertValues(true)
        this.vm.inputs.password("")
        this.signInButtonIsEnabled.assertValues(true ,false)
        this.vm.password("123Jose")
        this.signInButtonIsEnabled.assertValues(true ,false, true)
        this.vm.username("")
        this.signInButtonIsEnabled.assertValues(true ,false, true, false)

    }

    @Test
    fun testSignInStatusSucessful(){
        this.setup()

        this.vm.inputs.username("Jtoru")
        this.vm.inputs.password("123")
        this.vm.inputs.signInPress()


        val response = GenericResponse<SignInResult, SignInError>(null, true, null)

        this.signInStatus.assertValues(response)

    }

    @Test
    fun testSIgnInStatusUnSucessful(){
        this.setup()

        this.vm.inputs.username("Jtoru1")
        this.vm.inputs.password("123")
        this.vm.inputs.signInPress()
        val response = GenericResponse<SignInResult,SignInError>(null, false, SignInError.unknown)
        this.signInStatus.assertValues(response)
        this.vm.outputs.errorMessage().subscribe {
            print(it)
        }
    }



    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
