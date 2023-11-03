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
    }
}
