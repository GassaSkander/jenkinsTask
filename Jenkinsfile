pipeline {
    agent any
    stages {
        stage('Dev') {
            steps {
                script {
                    // def date = new Date()
                    // def datePart = date.format("dd/MM/yyyy")
                    // def timePart = date.format("HH:mm:ss")
                    // echo "datePart : ${datePart}\ttimePart : ${timePart}"
                    sh 'mvn clean install'
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
