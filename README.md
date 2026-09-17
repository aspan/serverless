# Serverless API
The serverless project, created with [`aws-serverless-java-container`](https://github.com/aws/serverless-java-container) and spring cloud function
[`spring-cloud-function`](https://docs.spring.io/spring-cloud-function/reference/)

The starter project defines a simple `/hello` resource that can accept `GET` requests with its tests.

The project folder also includes a `template.yml` file. You can use this [SAM](https://github.com/awslabs/serverless-application-model) file to deploy the project to AWS Lambda and Amazon API Gateway or test in local with the [SAM CLI](https://github.com/awslabs/aws-sam-cli). 

## Pre-requisites
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)

## To build the sample on macOS (Apple silicon arm64)

You first need to build the function, then you will deploy it to AWS Lambda.

### Build the native image

Run the script `./build.sh` to build the docker image and package the native image or run the steps manually below. 

1. Run the following to build a Docker container image which will be used to create the Lambda function zip file.
   ```shell
   docker build -t al2023-graalvm25:native src/main/docker
   ```
2. Build the native image in the docker container
   ```shell
   docker run -it -v `pwd`:`pwd` -w `pwd` -v ~/.m2:/root/.m2 al2023-graalvm25:native mvn clean package -Pnative

   ```

## Testing locally with the SAM CLI

From the project root folder - where the `template.yml` file is located - start the API with the SAM CLI.

```bash
$ sam local start-api

...
Mounting ServerlessWebNativeFunction at http://127.0.0.1:3000$default [X-AMAZON-APIGATEWAY-ANY-METHOD] 
...
```

Using a new shell, you can send a test ping request to your API:

```bash
$ curl -s http://127.0.0.1:3000/hello?name=Olle | python3 -m json.tool

{
    "message": "Hello World Olle!"
}
``` 

## Deploying to AWS
To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

```
$ sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use `curl` or a web browser to make a call to the URL

```
...
-------------------------------------------------------------------------------------------------------------
OutputKey-Description                        OutputValue
-------------------------------------------------------------------------------------------------------------
ServerlessApi - URL for application            https://xxxxxxxxx.execute-api.eu-north-1.amazonaws.com/hello
-------------------------------------------------------------------------------------------------------------
```

Copy the `OutputValue` into a browser or use curl to test your first request:

```bash
curl -s https://xxxxxxxxx.execute-api.eu-north-1.amazonaws.com/hello?name=Kalle | python3 -m json.tool
{
    "message": "Hello World Kalle!"
}
```
