pipeline {
    agent any

    environment {
        IMAGE_NAME = "hello-k8s"
        IMAGE_TAG = "v${BUILD_NUMBER}"
    }
    
     tools {
        maven 'Maven3'
        jdk 'jdk21'
    }

    stages {

		       stage('Build Jar') {
		    steps {
		        bat 'mvn clean package'
		    }
		}
		
		stage('Build Docker Image') {
		    steps {
		        bat "docker build -t %IMAGE_NAME%:%IMAGE_TAG% ."
		    }
		}
		
		stage('Debug Minikube') {
			    steps {
			        bat 'whoami'
			        bat 'echo USERPROFILE=%USERPROFILE%'
			        bat 'echo HOME=%HOME%'
			        bat 'where minikube'
			        bat 'minikube profile list'
			        bat 'minikube status'
			        bat 'kubectl config current-context'
			    }
			}
		
		stage('Load Image into Minikube') {
		    steps {
		        bat "minikube image load %IMAGE_NAME%:%IMAGE_TAG%"
		    }
		}
		
		stage('Deploy to Kubernetes') {
		    steps {
		        bat 'kubectl apply -f deployment.yaml'
		        bat 'kubectl apply -f service.yaml'
		    }
		}
		
		stage('Verify Deployment') {
		    steps {
				bat 'kubectl rollout status deployment/hello-deployment'
				 bat "kubectl set image deployment/hello-deployment hello-deployment=%IMAGE_NAME%:%IMAGE_TAG%"
		        bat 'kubectl get pods'
		        bat 'kubectl get svc'
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