# Why?
This spike is to remind me how to do aws lambda.

https://docs.aws.amazon.com/lambda/latest/dg/lambda-java.html

# Setup

1. Create execution role 'my-lambda-role'

2. Create lambda 'my-lambda-function' (and upload jar)

3. Create MyLambdaTest

# Build
```
gradle clean test jar
```

# Test
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

# Deploy

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

## Test CLI

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
