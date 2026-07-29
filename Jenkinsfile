pipeline {
    agent any

    environment {
        IMAGE_NAME = "hello-k8s"
        IMAGE_TAG = "v1"
    }
    
     tools {
        maven 'Maven3'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
               sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        stage('Load Image into Minikube') {
            steps {
               sh "minikube image load ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f deployment.yaml'
                sh 'kubectl apply -f service.yaml'
            }
        }

        stage('Verify Deployment') {
            steps {
                sh 'kubectl get deployments'
                sh 'kubectl get pods'
                sh 'kubectl get svc'
            }
        }
    }

    post {

        success {
            echo 'Deployment Successful'
        }

        failure {
            echo 'Deployment Failed'
        }
    }
}