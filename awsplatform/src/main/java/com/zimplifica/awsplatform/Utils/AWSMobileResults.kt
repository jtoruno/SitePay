package com.zimplifica.awsplatform.Utils

import com.amazonaws.AmazonServiceException
import com.amazonaws.mobile.client.AWSMobileClient
import java.lang.Exception

fun AWSMobileClient.ErrorMappingHelper(errorCode : String, message : String, error : Exception?): AWSMobileClientError{
    when ((errorCode)) {
            "AliasExistsException" -> return AWSMobileClientError.aliasExists(message)
            "CodeDeliveryFailureException" -> return AWSMobileClientError.codeDeliveryFailure(message)
            "CodeMismatchException" -> return AWSMobileClientError.codeMismatch(message)
            "ExpiredCodeException" -> return AWSMobileClientError.expiredCode(message)
            "GroupExistsException" -> return AWSMobileClientError.groupExists(message)
            "InternalErrorException" -> return AWSMobileClientError.internalError(message)
            "InvalidLambdaResponseException" -> return AWSMobileClientError.invalidLambdaResponse(message)
            "InvalidOAuthFlowException" -> return AWSMobileClientError.invalidOAuthFlow(message)
            "InvalidParameterException" -> return AWSMobileClientError.invalidParameter(message)
            "InvalidPasswordException" -> return AWSMobileClientError.invalidPassword(message)
            "InvalidUserPoolConfigurationException" -> AWSMobileClientError.invalidUserPoolConfiguration(message)
            "LimitExceededException" -> return AWSMobileClientError.limitExceeded(message)
            "MFAMethodNotFoundException" -> return AWSMobileClientError.mfaMethodNotFound(message)
            "NotAuthorizedException" -> return AWSMobileClientError.notAuthorized(message)
            "PasswordResetRequiredException" -> return AWSMobileClientError.passwordResetRequired(message)
            "ResourceNotFoundException" -> return AWSMobileClientError.resourceNotFound(message)
            "ScopeDoesNotExistException" -> return AWSMobileClientError.scopeDoesNotExist(message)
            "SoftwareTokenMFANotFoundException" -> return AWSMobileClientError.softwareTokenMFANotFound(message)
            "TooManyFailedAttemptsException" -> return AWSMobileClientError.tooManyFailedAttempts(message)
            "TooManyRequestsException" -> return AWSMobileClientError.tooManyRequests(message)
            "UnexpectedLambdaException" -> return AWSMobileClientError.unexpectedLambda(message)
            "UserLambdaValidationException" -> return AWSMobileClientError.userLambdaValidation(message)
            "UserNotConfirmedException" -> return AWSMobileClientError.userNotConfirmed(message)
            "UserNotFoundException" -> return AWSMobileClientError.userNotFound(message)
            "UsernameExistsException" -> return AWSMobileClientError.usernameExists(message)
    }

    return if (error != null && error is AmazonServiceException) {
        AWSMobileClientError.unknown(message = "${(error.errorType)}: $message")
    } else {
        AWSMobileClientError.unknown(message = message)
    }
}


sealed class AWSMobileClientError : Exception() {
    data class aliasExists(override val message: String) : AWSMobileClientError()
    data class codeDeliveryFailure(override val message: String) : AWSMobileClientError()
    data class codeMismatch(override val message: String) : AWSMobileClientError()
    data class expiredCode(override val message: String) : AWSMobileClientError()
    data class groupExists(override val message: String) : AWSMobileClientError()
    data class internalError(override val message: String) : AWSMobileClientError()
    data class invalidLambdaResponse(override val message: String) : AWSMobileClientError()
    data class invalidOAuthFlow(override val message: String) : AWSMobileClientError()
    data class invalidParameter(override val message: String) : AWSMobileClientError()
    data class invalidPassword(override val message: String) : AWSMobileClientError()
    data class invalidUserPoolConfiguration(override val message: String) : AWSMobileClientError()
    data class limitExceeded(override val message: String) : AWSMobileClientError()
    data class mfaMethodNotFound(override val message: String) : AWSMobileClientError()
    data class notAuthorized(override val message: String) : AWSMobileClientError()
    data class passwordResetRequired(override val message: String) : AWSMobileClientError()
    data class resourceNotFound(override val message: String) : AWSMobileClientError()
    data class scopeDoesNotExist(override val message: String) : AWSMobileClientError()
    data class softwareTokenMFANotFound(override val message: String) : AWSMobileClientError()
    data class tooManyFailedAttempts(override val message: String) : AWSMobileClientError()
    data class tooManyRequests(override val message: String) : AWSMobileClientError()
    data class unexpectedLambda(override val message: String) : AWSMobileClientError()
    data class userLambdaValidation(override val message: String) : AWSMobileClientError()
    data class userNotConfirmed(override val message: String) : AWSMobileClientError()
    data class userNotFound(override val message: String) : AWSMobileClientError()
    data class usernameExists(override val message: String) : AWSMobileClientError()
    data class unknown(override val message: String) : AWSMobileClientError()
    data class notSignedIn(override val message: String) : AWSMobileClientError()
    data class identityIdUnavailable(override val message: String) : AWSMobileClientError()
    data class guestAccessNotAllowed(override val message: String) : AWSMobileClientError()
    data class federationProviderExists(override val message: String) : AWSMobileClientError()
    data class cognitoIdentityPoolNotConfigured(override val message: String) : AWSMobileClientError()
    data class unableToSignIn(override val message: String) : AWSMobileClientError()
    data class invalidState(override val message: String) : AWSMobileClientError()
    data class userPoolNotConfigured(override val message: String) : AWSMobileClientError()
    data class userCancelledSignIn(override val message: String) : AWSMobileClientError()
}
