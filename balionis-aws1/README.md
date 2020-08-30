# Why?
This spike is to remind me how to do aws lambda.

https://docs.aws.amazon.com/lambda/latest/dg/lambda-java.html

# Lambda: Setup

1. Create execution role 'my-lambda-role'

2. Create lambda 'my-lambda-function' (and upload jar)

3. Create MyLambdaTest

# Lambda: Build
```
gradle clean test jar
```

# Lambda: Test
```
$ aws lambda get-function --function-name my-lambda-function
{
    "Configuration": {
        "FunctionName": "my-lambda-function",
        "FunctionArn": "arn:aws:lambda:eu-west-2:613877803204:function:my-lambda-function",
        "Runtime": "java8",
        "Role": "arn:aws:iam::613877803204:role/my-lambda-role",
        "Handler": "com.balionis.aws1.Greeter::handleRequest",
...
        "Version": "$LATEST",
        "TracingConfig": {
            "Mode": "PassThrough"
        },
        "RevisionId": "1b94995d-1463-4968-88a6-e8c602359c38",
        "State": "Active",
        "LastUpdateStatus": "Successful"
    },
    "Code": {
        "RepositoryType": "S3",
        "Location": "https://awslambda-eu-west-2-tasks.s3.eu-west-2.amazonaws.com/snapshots/613877803204/my-lambda-function-..."
    }
}

$ aws lambda invoke --function-name my-lambda-function --cli-binary-format raw-in-base64-out --payload '"World"' build/response.json ; cat build/response.json
{
    "StatusCode": 200,
    "ExecutedVersion": "$LATEST"
}
"Hello, World"

```

# API: Setup

https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-create-api-as-simple-proxy-for-lambda.html

## Build a "Hello, World!" API

1. Sign in to the API Gateway console at https://console.aws.amazon.com/apigateway.

2. If this is your first time using API Gateway, you see a page that introduces you to the features of the service. Under __REST API__, choose __Build__. When the Create Example API popup appears, choose __OK__.

   If this is not your first time using API Gateway, choose __Create API__. Under __REST API__, choose __Build__.

3. Create an empty API as follows:

   a. Under __Create new API__, choose __New API__.

   b. Under __Settings__:

   * For API name, enter __my-lambda-api__.

   * If desired, enter a description in the Description field; otherwise, leave it empty.

   * Leave Endpoint Type set to __Regional__.

   c. Choose __Create API__.

4. Create the _greeter_ resource as follows:

   a. Choose the root resource (/) in the __Resources__ tree.

   b. Choose __Create Resource__ from the __Actions__ dropdown menu.

   c. Leave __Configure as proxy__ resource unchecked.

   d. For __Resource Name__, enter __greeter__.

   e. Leave __Resource Path__ set to __/greeter__.

   f. Leave __Enable API Gateway CORS__ unchecked.

   g. Choose __Create Resource__.

5. In a proxy integration, the entire request is sent to the backend Lambda function as-is, via a catch-all ANY method that represents any HTTP method. The actual HTTP method is specified by the client at run time. The ANY method allows you to use a single API method setup for all of the supported HTTP methods: DELETE, GET, HEAD, OPTIONS, PATCH, POST, and PUT.

   To set up the POST method, do the following:

   a. In the __Resources__ list, choose __/greeter__.

   b. In the __Actions__ menu, choose __Create method__.

   c. Choose __POST__ from the dropdown menu, and choose the checkmark icon

   d. Leave the __Integration type__ set to __Lambda Function__.

   e. Choose __Use Lambda Proxy integration__.

   f. From the __Lambda Region__ dropdown menu, choose the region where you created the __my-lambda-function__ Lambda function.

   g. In the __Lambda Function__ field, type any character and choose __my-lambda-function__ from the dropdown menu.

   h. Leave __Use Default Timeout__ checked.

   i. Choose __Save__.

   j. Choose __OK__ when prompted with __Add Permission to Lambda Function__.

## Deploy the API in the API Gateway console

1. Choose __Deploy API__ from the __Actions__ dropdown menu.

2. For __Deployment stage__, choose __[new stage]__.

3. For __Stage__ name, enter __dev__.

4. If desired, enter a Stage description.

5. If desired, enter a Deployment description.

6. Choose __Deploy__.

7. Note the API's __Invoke URL__.

# API: Test 
```
$ curl -s -X POST 'https://wcggkkk12d.execute-api.eu-west-2.amazonaws.com/dev/greeter' -d '"World"'
"Hello, World"
```

# CLI: Setup

## Configure 'Administrator' account

https://docs.aws.amazon.com/IAM/latest/UserGuide/getting-started_create-admin-group.html

```
https://613877803204.signin.aws.amazon.com/console
```

## Configure 'aws cli' with 'Administrator' account

1. Setup Access Key
https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html 

2. Setup CLI
```
aws configure
```

## Test

```
$ aws iam list-users
{
    "Users": [
        {
            "Path": "/",
            "UserName": "Administrator",
            "UserId": "AIDAY53PX5DCPEIKB2O4C",
            "Arn": "arn:aws:iam::613877803204:user/Administrator",
            "CreateDate": "2020-08-30T10:55:37+00:00"
        }
    ]
}
```
