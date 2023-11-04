pipeline {
    agent any

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
            steps {
                script {
                    sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=password1P@"
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
