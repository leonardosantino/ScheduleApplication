package com.application.common.clients

import com.application.common.properties.AwsProperties
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDisableUserRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminEnableUserRequest

@Service
class CognitoClient(
    private val cognitoClient: CognitoIdentityProviderClient,
    private val awsProperties: AwsProperties,
) {
    fun enableUser(username: String) {
        cognitoClient.adminEnableUser(
            AdminEnableUserRequest
                .builder()
                .userPoolId(awsProperties.cognito.userPoolId)
                .username(username)
                .build(),
        )
    }

    fun disableUser(username: String) {
        cognitoClient.adminDisableUser(
            AdminDisableUserRequest
                .builder()
                .userPoolId(awsProperties.cognito.userPoolId)
                .username(username)
                .build(),
        )
    }
}
