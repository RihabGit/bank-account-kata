pipeline {
    agent {
            docker { image 'jenkins/jnlp-agent-maven' }
        }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}