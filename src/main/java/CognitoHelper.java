/*
Purpose: 
This is a simple implementation of  my research at retrieving AWS cognito userpool datapoint "Estimated number of users", from API
and displaying it. If all things go smoothly, this functionality will be implemented in the Bright Eye community discord bot.
Created: 
09/10/2023
Last updated: 
08/12/2023
 */

import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.DescribeUserPoolRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.DescribeUserPoolResponse;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;



public class CognitoHelper {
    public static int getEstimatedUserCount() {
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(AwsBasicCredentials.create("accessKeyId", "secretAccessKey"));

        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(credentialsProvider)
            .build();

        DescribeUserPoolRequest describeUserPoolRequest = DescribeUserPoolRequest.builder()
            .userPoolId("userpool region")
            .build();

        DescribeUserPoolResponse response = cognitoClient.describeUserPool(describeUserPoolRequest);
        int estimatedNumberOfUsers = response.userPool().estimatedNumberOfUsers();

        cognitoClient.close();

        return estimatedNumberOfUsers;
    }

    public static void main(String[] args) {
        System.out.println("Estimated number of users: " + getEstimatedUserCount());
    }
}
