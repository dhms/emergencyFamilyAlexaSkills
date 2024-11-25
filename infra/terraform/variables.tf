variable "region" {
  default = "us-east-1"
}

variable "s3_bucket" {
  description = "Bucket para armazenamento do código lambda"
  type = string
  default = "family-help-terraform-dev"
}

variable "s3_filename" {
  description = "path do zip"
  type = string
  default = "lambda/emergencia-familiar-1.0-SNAPSHOT.jar"
}


variable "function_name" {
  description = "Nome da função lambda"
  type = string
  default = "EmergencyFamily"
}

variable "role_function" {
  description = "Role da lambda"
  type = string
  default = "SUA ROLE AQUI"
}

variable "function_handler" {
  description = "Handler da lambda"
  type = string
  default = "com.familyhelp.lambda.handler.FamilyHelpStreamHandler"
}

variable "runtime_lambda" {
  description = "Runtime utilizada na lambda"
  type = string
  default = "java11"
}

variable "sns_topic_arn" {
  description = "ARN do tópico SNS"
  type = string
  default = "arn:aws:sns:us-east-1:381492262728:EmergencyTopic"
}
