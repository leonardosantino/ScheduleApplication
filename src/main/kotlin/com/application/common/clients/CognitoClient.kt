package com.application.common.clients

import com.application.common.properties.AwsProperties
import com.application.controller.dto.request.UserRequest
import com.application.controller.dto.response.UserCreateResponse
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDisableUserRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminEnableUserRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType
import java.util.UUID

@Service
class CognitoClient(
    private val cognitoClient: CognitoIdentityProviderClient,
    private val awsProperties: AwsProperties,
) {
    fun createUser(request: UserRequest): UserCreateResponse {
        val username = request.email
        val pass = generateTemporaryPassword()

        val response =
            cognitoClient.adminCreateUser(
                AdminCreateUserRequest
                    .builder()
                    .userPoolId(awsProperties.cognito.userPoolId)
                    .username(username)
                    .temporaryPassword(pass)
                    .build(),
            )
        val id =
            response
                .user()
                .attributes()
                .first { it.name() == "sub" }
                .value()

        return UserCreateResponse(
            id = id,
            username = username,
            password = pass,
        )
    }

    fun enableUser(username: String) {
        cognitoClient.adminEnableUser(
            AdminEnableUserRequest
                .builder()
                .userPoolId(awsProperties.cognito.userPoolId)
                .username(username)
                .build(),
        )
    }

    fun updatePhone(
        username: String,
        phone: String,
    ) {
        cognitoClient.adminUpdateUserAttributes(
            AdminUpdateUserAttributesRequest
                .builder()
                .userPoolId(awsProperties.cognito.userPoolId)
                .username(username)
                .userAttributes(
                    AttributeType
                        .builder()
                        .name("phone_number")
                        .value(phone)
                        .build(),
                ).build(),
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

    private fun generateTemporaryPassword() =
        "T-${UUID
            .randomUUID()
            .toString()
            .take(8)}"
}
