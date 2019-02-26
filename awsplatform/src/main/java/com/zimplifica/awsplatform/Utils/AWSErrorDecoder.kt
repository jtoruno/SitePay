package com.zimplifica.awsplatform.Utils

import com.zimplifica.domain.entities.SignInError

class AWSErrorDecoder {
    companion object {

        fun decodeSignInErrorError(exc: Exception?) : SignInError{
            var signInError: SignInError = SignInError.unknown


            (exc as? AWSMobileClientError)?.let {
                when ((it)) {
                    is AWSMobileClientError.invalidPassword -> signInError = SignInError.invalidCredentials
                    is AWSMobileClientError.userNotFound -> signInError = SignInError.invalidCredentials
                    is AWSMobileClientError.userNotConfirmed -> signInError = SignInError.userNotConfirmed
                    is AWSMobileClientError.tooManyFailedAttempts -> signInError = SignInError.tooManyFailedAttemps
                    is AWSMobileClientError.aliasExists -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.codeDeliveryFailure -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.codeMismatch -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.expiredCode -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.groupExists -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.internalError -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.invalidLambdaResponse -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.invalidOAuthFlow -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.invalidParameter -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.invalidUserPoolConfiguration -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.limitExceeded -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.mfaMethodNotFound -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.notAuthorized -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.passwordResetRequired -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.resourceNotFound -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.scopeDoesNotExist -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.softwareTokenMFANotFound -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.tooManyRequests -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.unexpectedLambda -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.userLambdaValidation -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.usernameExists -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.unknown -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.notSignedIn -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.identityIdUnavailable -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.guestAccessNotAllowed -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.federationProviderExists -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.cognitoIdentityPoolNotConfigured -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.unableToSignIn -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.invalidState -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.userPoolNotConfigured -> signInError = SignInError.internalError(message = it.message)
                    is AWSMobileClientError.userCancelledSignIn -> signInError = SignInError.internalError(message = it.message)

                }

            }
            return signInError
        }
    }
}