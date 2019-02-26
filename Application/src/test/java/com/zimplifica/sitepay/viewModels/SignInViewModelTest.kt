package com.zimplifica.sitepay.viewModels

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


    private  fun setup(){
        val useCase = AuthenticationUseCase()
        vm = SignInViewModel.ViewModel(useCase)
        vm.outputs.signInButtonIsEnabled().subscribe(this.signInButtonIsEnabled)
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
    fun testSignInStatus(){
        this.setup()

        this.vm.inputs.username("Jtoru")
        this.vm.inputs.password("123")
        this.vm.inputs.signInPress()

    }


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
