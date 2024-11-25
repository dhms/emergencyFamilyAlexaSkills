provider "aws" {
  region = var.region
}

resource "aws_lambda_function" "emergency_handler" {
  function_name = var.function_name
  role          = var.role_function
  handler = var.function_handler
  runtime = var.runtime_lambda
  timeout = 180
  memory_size = 512

  s3_bucket = var.s3_bucket
  s3_key = var.s3_filename

  environment {
    variables = {
      TOPIC_ARN = var.sns_topic_arn
    }
  }

  lifecycle {
    ignore_changes = [s3_key]
  }
}