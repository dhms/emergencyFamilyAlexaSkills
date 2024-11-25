output "lambda_function_arn" {
  description = "ARN do lambda"
  value = aws_lambda_function.emergency_handler.arn
}