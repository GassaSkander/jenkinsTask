
pipeline {
  agent any
  stages {
    stage('Dev') {
      steps {
        Date date = new Date()
        String datePart = date.format("dd/MM/yyyy")
        String timePart = date.format("HH:mm:ss")
        println "datePart : " + datePart + "\ttimePart : " + timePart
      }
    }

  }
}