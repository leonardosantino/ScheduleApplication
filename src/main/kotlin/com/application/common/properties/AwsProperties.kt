package com.application.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "aws")
class AwsProperties(
    var cognito: AwsCognitoProperties = AwsCognitoProperties(),
)
