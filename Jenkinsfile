pipeline {
 agent any
 tools {
    gradle "gradle"
 }
 stages {
    stage("configuration") {
         steps {
             bat 'echo configuration'
         }
    }
    stage("build") {
         steps {
             bat 'echo building'
         }
    }
    stage("test") {
         steps {
             bat 'echo testing'
         }
     }
     stage("deploy") {
         steps {
             echo "deploying..."
         }
     }
 }
}
