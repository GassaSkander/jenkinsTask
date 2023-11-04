pipeline {
    agent any
    environment {
    }
    stages {
        stage('Dev') {
            steps {
                script {
                    def date = new Date()
                    def datePart = date.format("dd/MM/yyyy")
                    def timePart = date.format("HH:mm:ss")
                    echo "datePart : ${datePart}\ttimePart : ${timePart}"
                   
                }
            }
        }
        stage('GIT'){
            steps {
                echo "Getting Project from Git";
            }
        }
        stage('MVN CLEAN'){
            steps {
                sh 'mvn clean'
            }
        }
        stage('MVN COMPILE'){
            steps {
                sh 'mvn compile'
            }
        }
        stage('SonarQube Analysis') {
            environment {
                SONARQUBE_USERNAME = credentials('admin')
                SONARQUBE_PASSWORD = credentials('admin')
            }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'sonarqube-credentials', usernameVariable: 'SONARQUBE_USERNAME', passwordVariable: 'SONARQUBE_PASSWORD')]) {
                        sh "mvn sonar:sonar -Dsonar.login=${env.SONARQUBE_USERNAME} -Dsonar.password=${env.SONARQUBE_PASSWORD}"
                    }
                }
            }
        }
    }
    post {
        success {
            echo 'The pipeline has succeeded!'
        }
        failure {
            echo 'The pipeline has failed!'
        }
    }
}
