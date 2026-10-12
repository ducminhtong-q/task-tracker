pipeline {
    agent {
        label 'docker'
    }

    environment {
        IMAGE_NAME = 'task-tracker-app'
        MVN_REPO   = '-Dmaven.repo.local=/home/jenkins/agent/.m2/repository'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            agent {
                docker {
                    image 'maven:3.9-eclipse-temurin-17'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn -B $MVN_REPO -DskipTests clean package'
            }
        }

        stage('Test') {
            agent {
                docker {
                    image 'maven:3.9-eclipse-temurin-17'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn -B $MVN_REPO test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Docker build') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$BUILD_NUMBER -t $IMAGE_NAME:latest .'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose -p task-tracker up -d --no-deps --force-recreate app'
            }
        }
    }
}