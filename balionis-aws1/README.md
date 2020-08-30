# Why?
This spike is to remind me how to do aws lambda.

# Build
```
gradle clean test jar
```

# Test
TODO

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
